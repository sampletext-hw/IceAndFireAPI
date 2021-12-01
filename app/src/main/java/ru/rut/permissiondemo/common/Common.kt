package ru.rut.permissiondemo.common

import ru.rut.permissiondemo.inter.RetrofitService
import ru.rut.permissiondemo.retrofit.RetrofitClient

object Common {
    private val BASE_URL = "https://www.simplifiedcoding.net/demos/"
    val retrofitService: RetrofitService
    get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}