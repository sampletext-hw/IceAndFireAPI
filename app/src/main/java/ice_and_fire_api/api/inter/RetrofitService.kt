package ice_and_fire_api.api.inter

import retrofit2.Call
import retrofit2.http.*
import ice_and_fire_api.api.model.Hero

interface RetrofitService {
    @GET("characters")
    fun characters(
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
    ): Call<MutableList<Hero>>
}