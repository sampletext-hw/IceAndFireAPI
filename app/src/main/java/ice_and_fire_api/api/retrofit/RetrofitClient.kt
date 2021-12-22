package ice_and_fire_api.api.retrofit

import retrofit2.Retrofit
import com.google.gson.GsonBuilder
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var retrofit: Retrofit? = null
    fun getClient(baseURL: String): Retrofit {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        return retrofit!!
    }

}