package com.she.apps.domain.repository

import com.she.apps.data.source.Resource
import com.she.apps.data.source.remote.request.LoginReq
import com.she.apps.data.source.remote.request.RegisterReq
import com.she.apps.data.source.remote.response.SheeResponse
import com.she.apps.data.source.remote.response.Users
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    fun register(registerReq: RegisterReq) : Flow<Resource<SheeResponse>>
    fun login(loginReq: LoginReq) : Flow<Resource<Users>>
}