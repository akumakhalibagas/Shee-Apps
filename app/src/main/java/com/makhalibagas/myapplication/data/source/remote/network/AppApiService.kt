package com.makhalibagas.myapplication.data.source.remote.network

import com.makhalibagas.myapplication.data.source.remote.request.*
import com.makhalibagas.myapplication.data.source.remote.response.*
import retrofit2.http.*

interface AppApiService {

    //Login
    @POST("user/login")
    suspend fun login(
        @Body loginReq: LoginReq
    ) : Users

    @POST("user/register")
    suspend fun register(
        @Body registerReq: RegisterReq
    ) : SheeResponse

    //Green

    @GET("green")
    suspend fun getGreen(): List<GreenItem>

    @DELETE("green")
    suspend fun delGreen(
        @Query("id") id: String
    ): SheeResponse

    @POST("green")
    suspend fun addGreen(
        @Body greenReq: GreenReq
    ): SheeResponse

    @PUT("green")
    suspend fun editGreen(
        @Body editReq: EditReq
    ): SheeResponse

    //IBPR

    @GET("ibpr")
    suspend fun getIbpr(): List<IbprItem>

    @DELETE("ibpr")
    suspend fun delIbpr(
        @Query("id") id: String
    ): SheeResponse

    @POST("ibpr")
    suspend fun addIbpr(
        @Body ibprReq: IbprReq
    ): SheeResponse

    @PUT("ibpr")
    suspend fun editIbpr(
        @Body editReq: EditReq
    ): SheeResponse

    //Jsa

    @GET("jsa")
    suspend fun getJsa(): List<JsaItem>

    @DELETE("jsa")
    suspend fun delJsa(
        @Query("id") id: String
    ): SheeResponse

    @POST("jsa")
    suspend fun addJsa(
        @Body jsReq: JsaReq
    ): SheeResponse
}
