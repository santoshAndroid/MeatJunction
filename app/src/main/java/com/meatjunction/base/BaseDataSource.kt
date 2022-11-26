package com.meatjunction.base

import com.meatjunction.network.NetworkCheck
import com.meatjunction.network.Resource
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response


abstract class BaseDataSource : BaseActivity() {
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        if (NetworkCheck().isInternetAvailable()) {
            try {
                val response = call()
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) return Resource.success(body)
                }
                if (response.code() == 422 || response.code() == 400) {
                    return try {
                        val body = response.errorBody()

                        val jObjError = JSONObject(response.errorBody()!!.string())
                        if (jObjError.has("errors") && !jObjError.get("errors").equals(null)
                        ) {
                            when {
                                jObjError.get("errors") is String -> {
                                    error(body, jObjError.getString("errors"))
                                }
                                jObjError.getJSONArray("errors").length() > 0 -> {
                                    error(body, jObjError.getJSONArray("errors").getString(0))
                                }
                                else -> {
                                    error(body, "Something went wrong")
                                }
                            }
                        } else {
                            error(body, jObjError.getString("message"))
                        }

                    } catch (e: java.lang.Exception) {
                        error(null, e.message ?: e.toString())
                    }

                } else if (response.code() == 511) {
                    return error("Session Expired")
                } else if (response.code() == 500) {
                    return error("Internal Error (Service Issue)")
                }
                return error(" ${response.code()} ${response.message()}")
            } catch (e: Exception) {
                return error(e.message ?: e.toString())
            }
        } else {
            return error("Please Check your Network Connection or Try Again Later")
        }
    }

    private fun <T> error(error: ResponseBody?, message: String): Resource<T> {
        return Resource.error(
            data = null,
            message
        )
    }
}