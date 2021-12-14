package tn.esprit.dorra.bouchaddekh.frontart.Api

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("/signin")
    fun signin(@Body body: JsonObject): Call<JsonObject>
    @POST("/signup")
    fun signup(@Body body: JsonObject): Call<JsonObject>
    @POST("/reset-password")
    fun resetpassword(@Body body: JsonObject): Call<JsonObject>

    @POST("user/show")
    fun showUser(@Body body: JsonObject): Call<JsonObject>

    @POST("user/update")
    fun updateUser(@Body body: JsonObject): Call<JsonObject>

}