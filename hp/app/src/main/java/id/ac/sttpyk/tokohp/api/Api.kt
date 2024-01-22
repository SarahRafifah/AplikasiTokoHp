package id.ac.sttpyk.tokohp.api

import id.ac.sttpyk.tokohp.models.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
//hp

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String,
    ): Call<LoginModel>

    @FormUrlEncoded
    @POST("hp/simpan")
    fun entrihp(
        @Field("hp") hp: String,
        @Field("harga") harga: String
    ): Call<SimpanModel>

    @FormUrlEncoded
    @POST("hp/hapus")
    fun hapushp(
        @Field("id") id: Int?
    ): Call<SimpanModel>

    @FormUrlEncoded
    @POST("hp/edit")
    fun edithp(
        @Field("id") id: Int?,
        @Field("hp") hp: String,
        @Field("harga") harga: String
    ): Call<SimpanModel>

    @GET("hp/tampil")
    fun tampilhp(): Call<HpModel>


    companion object {
        fun create (): Api {
            val httpClient = OkHttpClient.Builder()
            var logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)

            val retrofit : Retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://10.0.2.2:8000/api/")
                //.baseUrl("http://192.168.200.87:8000/api/")
                .client(httpClient.build())
                .build()

            return retrofit.create(Api::class.java)
        }
    }
}