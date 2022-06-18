package com.makhalibagas.myapplication.domain.repository

import com.makhalibagas.myapplication.data.source.Resource
import com.makhalibagas.myapplication.data.source.remote.request.LoginReq
import com.makhalibagas.myapplication.data.source.remote.request.RegisterReq
import com.makhalibagas.myapplication.data.source.remote.response.SheeResponse
import com.makhalibagas.myapplication.data.source.remote.response.Users
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    fun register(registerReq: RegisterReq) : Flow<Resource<SheeResponse>>
    fun login(loginReq: LoginReq) : Flow<Resource<Users>>
}