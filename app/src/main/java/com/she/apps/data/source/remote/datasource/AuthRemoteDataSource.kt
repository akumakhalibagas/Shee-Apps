package com.she.apps.data.source.remote.datasource

import com.she.apps.data.source.remote.network.AppApiResponse
import com.she.apps.data.source.remote.network.AppApiService
import com.she.apps.data.source.remote.request.LoginReq
import com.she.apps.data.source.remote.request.RegisterReq
import com.she.apps.data.source.remote.response.SheeResponse
import com.she.apps.data.source.remote.response.Users
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