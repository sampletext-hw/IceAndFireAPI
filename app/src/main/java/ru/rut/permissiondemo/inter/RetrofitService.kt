package ru.rut.permissiondemo.inter

import retrofit2.Call
import retrofit2.http.*
import ru.rut.permissiondemo.model.Hero

interface RetrofitService {
    @GET("marvel")
    fun getHeroList(): Call<MutableList<Hero>>
}