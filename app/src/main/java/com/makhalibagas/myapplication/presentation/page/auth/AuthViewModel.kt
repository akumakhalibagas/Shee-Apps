package com.makhalibagas.myapplication.presentation.page.auth

import androidx.lifecycle.ViewModel
import com.makhalibagas.myapplication.data.source.Resource
import com.makhalibagas.myapplication.data.source.remote.request.GreenReq
import com.makhalibagas.myapplication.data.source.remote.request.LoginReq
import com.makhalibagas.myapplication.data.source.remote.request.RegisterReq
import com.makhalibagas.myapplication.data.source.remote.response.SheeResponse
import com.makhalibagas.myapplication.data.source.remote.response.Users
import com.makhalibagas.myapplication.domain.repository.IAuthRepository
import com.makhalibagas.myapplication.presentation.state.UiStateWrapper
import com.makhalibagas.myapplication.utils.collectLifecycleFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(val repository: IAuthRepository) : ViewModel(){

    private val _register = MutableSharedFlow<UiStateWrapper<SheeResponse>>()
    val register = _register.asSharedFlow()

    private val _login = MutableSharedFlow<UiStateWrapper<Users>>()
    val login = _login.asSharedFlow()

    fun register(registerReq: RegisterReq) {
        collectLifecycleFlow(repository.register(registerReq)) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _register.emit(UiStateWrapper.Loading(true))
                }
                is Resource.Success -> {
                    _register.emit(UiStateWrapper.Loading(false))
                    _register.emit(UiStateWrapper.Success(resource.data))
                }
                is Resource.Error -> {
                    _register.emit(UiStateWrapper.Loading(false))
                }
            }
        }
    }

    fun login(loginReq: LoginReq) {
        collectLifecycleFlow(repository.login(loginReq)) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _login.emit(UiStateWrapper.Loading(true))
                }
                is Resource.Success -> {
                    _login.emit(UiStateWrapper.Loading(false))
                    _login.emit(UiStateWrapper.Success(resource.data))
                }
                is Resource.Error -> {
                    _login.emit(UiStateWrapper.Loading(false))
                }
            }
        }
    }

}