package ice_and_fire_api.api.common

import ice_and_fire_api.api.inter.RetrofitService
import ice_and_fire_api.api.retrofit.RetrofitClient

object Common {
    private val BASE_URL = "https://www.anapioficeandfire.com/api/"
    val retrofitService: RetrofitService
    get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}