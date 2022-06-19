package com.makhalibagas.myapplication.data.source.remote.datasource

import com.makhalibagas.myapplication.data.source.remote.network.AppApiResponse
import com.makhalibagas.myapplication.data.source.remote.network.AppApiService
import com.makhalibagas.myapplication.data.source.remote.request.LoginReq
import com.makhalibagas.myapplication.data.source.remote.request.RegisterReq
import com.makhalibagas.myapplication.data.source.remote.response.GreenItem
import com.makhalibagas.myapplication.data.source.remote.response.SheeResponse
import com.makhalibagas.myapplication.data.source.remote.response.Users
import com.makhalibagas.myapplication.domain.repository.IAuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRemoteDataSource @Inject constructor(val apiService: AppApiService){

    fun register(registerReq: RegisterReq): Flow<AppApiResponse<SheeResponse>> =
        flow {
            try {
                val response = apiService.register(registerReq)
                if (response.status.equals("success")) {
                    emit(AppApiResponse.Success(response))
                } else {
                    emit(AppApiResponse.Success(response))
                }
            } catch (e: Exception) {
                emit(AppApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    fun login(loginReq: LoginReq): Flow<AppApiResponse<Users>> =
        flow {
            try {
                val response = apiService.login(loginReq)
                emit(AppApiResponse.Success(response))
            } catch (e: Exception) {
                emit(AppApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

}