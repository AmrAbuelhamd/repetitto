package com.blogspot.soyamr.repetitto

import com.blogspot.soyamr.repetitto.RetrofitPojo.*
import retrofit2.Call
import retrofit2.http.*


interface RetroFitApi {
    @GET("user/{id}")
    fun getCurrentUser(@Path("id") userid: String,
                       @Header("authorization") token: String?): Call<UserById>

    @POST("/logout")
    fun logOut(@Header("authorization") token: String?): Call<Unit>

    @GET("user")
    fun getUsers(): Call<List<User>>

    @POST("/login")
    fun getLogInData(@Body request: LogInRequest): Call<LogInAnswer>

    @POST("/user")
    fun createUser(@Body request: CreateUser): Call<UserById>

    @GET("user")
    fun getUsersUsingFilter(@Query("onlyTeacher") onlyTeacher: Boolean?
                            , @Query("course") course: Int?
                            , @Query("limit") limit: Int?
                            , @Query("search") search: String?
                            , @Query("faculty") faculty: String?
                            , @Query("subject") subject: Int?
    ): Call<List<User>>

    @GET("user")
    fun getUsersByCourse(@Query("course") course: Int): Call<List<User>>


//    @POST("")
//    @FormUrlEncoded
//    fun filter(@Body someFilter:Filter):Call<Teachers>

}