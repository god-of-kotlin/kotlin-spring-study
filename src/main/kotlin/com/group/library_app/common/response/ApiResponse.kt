package com.group.library_app.common.response

data class ApiResponse<T>(
    val message: String? = null,
    val data: T? = null,
) {

    companion object {
        fun <T> success(message: String = "Success"): ApiResponse<T> {
            return ApiResponse(message = message)
        }

        fun <T> success(data: T, message: String = "Success"): ApiResponse<T> {
            return ApiResponse(message = message, data = data)
        }

        fun <T> error(message: String = "Error"): ApiResponse<T> {
            return ApiResponse(message = message)
        }
    }
}
