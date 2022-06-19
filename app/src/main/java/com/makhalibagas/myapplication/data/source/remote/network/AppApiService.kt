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
    suspend fun getGreen(
        @Query("startdate") startdate: String,
        @Query("enddate") enddate: String,
        @Query("shift") shift: String,
        @Query("status") status: String
        ): List<GreenItem>

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
        @Body editReq: EditGreenReq
    ): SheeResponse

    //IBPR

    @GET("ibpr")
    suspend fun getIbpr(
        @Query("startdate") startdate: String,
        @Query("enddate") enddate: String,
        @Query("shift") shift: String,
        @Query("status") status: String
    ): List<IbprItem>

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
        @Body editReq: EditIbprReq
    ): SheeResponse

    //Jsa

    @GET("jsa")
    suspend fun getJsa(
        @Query("startdate") startdate: String,
        @Query("enddate") enddate: String
    ): List<JsaItem>

    @DELETE("jsa")
    suspend fun delJsa(
        @Query("id") id: String
    ): SheeResponse

    @POST("jsa")
    suspend fun addJsa(
        @Body jsReq: JsaReq
    ): SheeResponse

    @PUT("jsa")
    suspend fun editJsa(
        @Body editReq: EditJsaReq
    ): SheeResponse

    @GET("green/monitoring")
    suspend fun getMonGreen(): MonitoringItem

    @GET("ibpr/monitoring")
    suspend fun getMonIbpr(): MonitoringItem
}
