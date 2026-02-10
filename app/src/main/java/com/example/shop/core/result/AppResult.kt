package com.example.shop.core.result

sealed interface AppResult<out T> {
    data class  Success<T>(val data:T): AppResult<T>
    data class Error<T>(val data:T): AppResult<T>

}