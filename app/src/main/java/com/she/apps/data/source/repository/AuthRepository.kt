package com.she.apps.data.source.repository

import com.she.apps.data.source.Resource
import com.she.apps.data.source.remote.datasource.AuthRemoteDataSource
import com.she.apps.data.source.remote.network.AppApiResponse
import com.she.apps.data.source.remote.request.LoginReq
import com.she.apps.data.source.remote.request.RegisterReq
import com.she.apps.data.source.remote.response.SheeResponse
import com.she.apps.data.source.remote.response.Users
import com.she.apps.domain.repository.IAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(val remoteDataSource: AuthRemoteDataSource) : IAuthRepository{

    override fun register(registerReq: RegisterReq): Flow<Resource<SheeResponse>> =
        flow {
            emit(Resource.Loading)
            when (val apiResource =
                remoteDataSource.register(registerReq).first()) {
                is AppApiResponse.Success -> {
                    emit(Resource.Success(apiResource.data))
                }
                is AppApiResponse.Error -> {
                    emit(Resource.Error(apiResource.msg))
                }
            }
        }

    override fun login(loginReq: LoginReq): Flow<Resource<Users>> =
        flow {
            emit(Resource.Loading)
            when (val apiResource =
                remoteDataSource.login(loginReq).first()) {
                is AppApiResponse.Success -> {
                    emit(Resource.Success(apiResource.data))
                }
                is AppApiResponse.Error -> {
                    emit(Resource.Error(apiResource.msg))
                }
            }
        }


}