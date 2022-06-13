package com.makhalibagas.myapplication.presentation.page.main

import androidx.lifecycle.ViewModel
import com.makhalibagas.myapplication.data.source.Resource
import com.makhalibagas.myapplication.data.source.remote.request.EditReq
import com.makhalibagas.myapplication.data.source.remote.request.GreenReq
import com.makhalibagas.myapplication.data.source.remote.request.IbprReq
import com.makhalibagas.myapplication.data.source.remote.request.JsaReq
import com.makhalibagas.myapplication.data.source.remote.response.GreenItem
import com.makhalibagas.myapplication.data.source.remote.response.IbprItem
import com.makhalibagas.myapplication.data.source.remote.response.JsaItem
import com.makhalibagas.myapplication.data.source.remote.response.SheeResponse
import com.makhalibagas.myapplication.domain.repository.IMainRepository
import com.makhalibagas.myapplication.presentation.state.UiStateWrapper
import com.makhalibagas.myapplication.utils.collectLifecycleFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: IMainRepository) : ViewModel() {

    private val _green = MutableSharedFlow<UiStateWrapper<List<GreenItem>>>()
    val green = _green.asSharedFlow()

    private val _delGreen = MutableSharedFlow<UiStateWrapper<SheeResponse>>()
    val delGreen = _delGreen.asSharedFlow()

    private val _addGreen = MutableSharedFlow<UiStateWrapper<SheeResponse>>()
    val addGreen = _addGreen.asSharedFlow()

    private val _editGreen = MutableSharedFlow<UiStateWrapper<SheeResponse>>()
    val editGreen = _editGreen.asSharedFlow()

    private val _ibpr = MutableSharedFlow<UiStateWrapper<List<IbprItem>>>()
    val ibpr = _ibpr.asSharedFlow()

    private val _delIbpr = MutableSharedFlow<UiStateWrapper<SheeResponse>>()
    val delIbpr = _delIbpr.asSharedFlow()

    private val _addIbpr = MutableSharedFlow<UiStateWrapper<SheeResponse>>()
    val addIbpr = _addIbpr.asSharedFlow()

    private val _editIbpr = MutableSharedFlow<UiStateWrapper<SheeResponse>>()
    val editIbpr = _editIbpr.asSharedFlow()

    private val _jsa = MutableSharedFlow<UiStateWrapper<List<JsaItem>>>()
    val jsa = _jsa.asSharedFlow()

    private val _delJsa = MutableSharedFlow<UiStateWrapper<SheeResponse>>()
    val delJsa = _delJsa.asSharedFlow()

    private val _addJsa = MutableSharedFlow<UiStateWrapper<SheeResponse>>()
    val addJsa = _addJsa.asSharedFlow()

    fun getGreen() {
        collectLifecycleFlow(repository.getGreen()) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _green.emit(UiStateWrapper.Loading(true))
                }
                is Resource.Success -> {
                    _green.emit(UiStateWrapper.Loading(false))
                    _green.emit(UiStateWrapper.Success(resource.data))
                }
                is Resource.Error -> {
                    _green.emit(UiStateWrapper.Loading(false))
                }
            }
        }
    }

    fun delGreen(id: String) {
        collectLifecycleFlow(repository.delGreen(id)) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _delGreen.emit(UiStateWrapper.Loading(true))
                }
                is Resource.Success -> {
                    _delGreen.emit(UiStateWrapper.Loading(false))
                    _delGreen.emit(UiStateWrapper.Success(resource.data))
                }
                is Resource.Error -> {
                    _delGreen.emit(UiStateWrapper.Loading(false))
                }
            }
        }
    }

    fun addGreen(green: GreenReq) {
        collectLifecycleFlow(repository.addGreen(green)) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _addGreen.emit(UiStateWrapper.Loading(true))
                }
                is Resource.Success -> {
                    _addGreen.emit(UiStateWrapper.Loading(false))
                    _addGreen.emit(UiStateWrapper.Success(resource.data))
                }
                is Resource.Error -> {
                    _addGreen.emit(UiStateWrapper.Loading(false))
                }
            }
        }
    }

    fun editGreen(edit: EditReq) {
        collectLifecycleFlow(repository.editGreen(edit)) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _editGreen.emit(UiStateWrapper.Loading(true))
                }
                is Resource.Success -> {
                    _editGreen.emit(UiStateWrapper.Loading(false))
                    _editGreen.emit(UiStateWrapper.Success(resource.data))
                }
                is Resource.Error -> {
                    _editGreen.emit(UiStateWrapper.Loading(false))
                }
            }
        }
    }

    fun getIbpr() {
        collectLifecycleFlow(repository.getIbpr()) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _ibpr.emit(UiStateWrapper.Loading(true))
                }
                is Resource.Success -> {
                    _ibpr.emit(UiStateWrapper.Loading(false))
                    _ibpr.emit(UiStateWrapper.Success(resource.data))
                }
                is Resource.Error -> {
                    _ibpr.emit(UiStateWrapper.Loading(false))
                }
            }
        }
    }

    fun delIbpr(id: String) {
        collectLifecycleFlow(repository.delIbpr(id)) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _delGreen.emit(UiStateWrapper.Loading(true))
                }
                is Resource.Success -> {
                    _delGreen.emit(UiStateWrapper.Loading(false))
                    _delGreen.emit(UiStateWrapper.Success(resource.data))
                }
                is Resource.Error -> {
                    _delGreen.emit(UiStateWrapper.Loading(false))
                }
            }
        }
    }

    fun addIbpr(ibpr: IbprReq) {
        collectLifecycleFlow(repository.addIbpr(ibpr)) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _addIbpr.emit(UiStateWrapper.Loading(true))
                }
                is Resource.Success -> {
                    _addIbpr.emit(UiStateWrapper.Loading(false))
                    _addIbpr.emit(UiStateWrapper.Success(resource.data))
                }
                is Resource.Error -> {
                    _addIbpr.emit(UiStateWrapper.Loading(false))
                }
            }
        }
    }

    fun editIbpr(edit: EditReq) {
        collectLifecycleFlow(repository.editIbpr(edit)) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _editGreen.emit(UiStateWrapper.Loading(true))
                }
                is Resource.Success -> {
                    _editGreen.emit(UiStateWrapper.Loading(false))
                    _editGreen.emit(UiStateWrapper.Success(resource.data))
                }
                is Resource.Error -> {
                    _editGreen.emit(UiStateWrapper.Loading(false))
                }
            }
        }
    }

    fun getJsa() {
        collectLifecycleFlow(repository.getJsa()) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _jsa.emit(UiStateWrapper.Loading(true))
                }
                is Resource.Success -> {
                    _jsa.emit(UiStateWrapper.Loading(false))
                    _jsa.emit(UiStateWrapper.Success(resource.data))
                }
                is Resource.Error -> {
                    _jsa.emit(UiStateWrapper.Loading(false))
                }
            }
        }
    }

    fun delJsa(id: String) {
        collectLifecycleFlow(repository.delJsa(id)) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _delJsa.emit(UiStateWrapper.Loading(true))
                }
                is Resource.Success -> {
                    _delJsa.emit(UiStateWrapper.Loading(false))
                    _delJsa.emit(UiStateWrapper.Success(resource.data))
                }
                is Resource.Error -> {
                    _delJsa.emit(UiStateWrapper.Loading(false))
                }
            }
        }
    }

    fun addJsa(jsa: JsaReq) {
        collectLifecycleFlow(repository.addJsa(jsa)) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _addJsa.emit(UiStateWrapper.Loading(true))
                }
                is Resource.Success -> {
                    _addJsa.emit(UiStateWrapper.Loading(false))
                    _addJsa.emit(UiStateWrapper.Success(resource.data))
                }
                is Resource.Error -> {
                    _addJsa.emit(UiStateWrapper.Loading(false))
                }
            }
        }
    }

}