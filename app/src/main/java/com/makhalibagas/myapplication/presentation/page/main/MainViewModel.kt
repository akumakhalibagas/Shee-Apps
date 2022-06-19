package com.makhalibagas.myapplication.presentation.page.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makhalibagas.myapplication.data.source.Resource
import com.makhalibagas.myapplication.data.source.remote.request.*
import com.makhalibagas.myapplication.data.source.remote.response.*
import com.makhalibagas.myapplication.domain.repository.IMainRepository
import com.makhalibagas.myapplication.presentation.state.UiStateWrapper
import com.makhalibagas.myapplication.utils.Shareds
import com.makhalibagas.myapplication.utils.collectLifecycleFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repository: IMainRepository, val shareds: Shareds) : ViewModel() {

    private var queryGreen : Job? = null
    private var _allGreenList = emptyList<GreenItem>()
    private val _greenList = MutableStateFlow<List<GreenItem>>(emptyList())
    val listGreen = _greenList.asStateFlow()

    private var queryIbpr : Job? = null
    private var _allIbprList = emptyList<IbprItem>()
    private val _IbprList = MutableStateFlow<List<IbprItem>>(emptyList())
    val listIbpr = _IbprList.asStateFlow()

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

    private val _editJsa = MutableSharedFlow<UiStateWrapper<SheeResponse>>()
    val editJsa = _editJsa.asSharedFlow()

    private val _greenMon = MutableSharedFlow<UiStateWrapper<MonitoringItem>>()
    val greenMon = _greenMon.asSharedFlow()

    private val _ibprMon = MutableSharedFlow<UiStateWrapper<MonitoringItem>>()
    val ibprMon = _ibprMon.asSharedFlow()

    fun getUsers() : Users? = shareds.users

    fun getMonGreen() {
        collectLifecycleFlow(repository.getMonGreen()) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _greenMon.emit(UiStateWrapper.Loading(true))
                }
                is Resource.Success -> {
                    _greenMon.emit(UiStateWrapper.Loading(false))
                    _greenMon.emit(UiStateWrapper.Success(resource.data))
                }
                is Resource.Error -> {
                    _greenMon.emit(UiStateWrapper.Loading(false))
                }
            }
        }
    }

    fun getMonIbpr() {
        collectLifecycleFlow(repository.getMonIbpr()) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _ibprMon.emit(UiStateWrapper.Loading(true))
                }
                is Resource.Success -> {
                    _ibprMon.emit(UiStateWrapper.Loading(false))
                    _ibprMon.emit(UiStateWrapper.Success(resource.data))
                }
                is Resource.Error -> {
                    _ibprMon.emit(UiStateWrapper.Loading(false))
                }
            }
        }
    }

    fun queryGreenDebounced(query: String) {
        queryGreen?.cancel()
        queryGreen = viewModelScope.launch {
            delay(500)
            queryGreen(query)
        }
    }

    private fun queryGreen(query: String) {
        _greenList.value = _allGreenList
        if (query == "All") {
            _greenList.value = _allGreenList
        }else{
            _greenList.value = listGreen.value.filter { it.shift!!.contains(query, true) || it.status!!.contains(query, true) }
        }
    }

    fun queryIbprDebounced(query: String) {
        queryIbpr?.cancel()
        queryIbpr = viewModelScope.launch {
            delay(500)
            queryIbpr(query)
        }
    }

    private fun queryIbpr(query: String) {
        _IbprList.value = _allIbprList
        if (query == "All"){
            _IbprList.value = _allIbprList
        }else{
            _IbprList.value = listIbpr.value.filter { it.shift!!.contains(query, true) || it.status!!.contains(query, true) }
        }
    }

    fun getGreen(
        startdate: String,
        enddate: String,
        shift: String,
        status: String
    ) {
        collectLifecycleFlow(repository.getGreen(startdate, enddate, shift, status)) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _green.emit(UiStateWrapper.Loading(true))
                }
                is Resource.Success -> {
                    _green.emit(UiStateWrapper.Loading(false))
                    _green.emit(UiStateWrapper.Success(resource.data))
                    _allGreenList = resource.data
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

    fun editGreen(edit: EditGreenReq) {
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

    fun getIbpr(
        startdate: String,
        enddate: String,
        shift: String,
        status: String
    ) {
        collectLifecycleFlow(repository.getIbpr(startdate, enddate, shift, status)) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _ibpr.emit(UiStateWrapper.Loading(true))
                }
                is Resource.Success -> {
                    _ibpr.emit(UiStateWrapper.Loading(false))
                    _ibpr.emit(UiStateWrapper.Success(resource.data))
                    _allIbprList = resource.data
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
                    _delIbpr.emit(UiStateWrapper.Loading(true))
                }
                is Resource.Success -> {
                    _delIbpr.emit(UiStateWrapper.Loading(false))
                    _delIbpr.emit(UiStateWrapper.Success(resource.data))
                }
                is Resource.Error -> {
                    _delIbpr.emit(UiStateWrapper.Loading(false))
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

    fun editIbpr(edit: EditIbprReq) {
        collectLifecycleFlow(repository.editIbpr(edit)) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _editIbpr.emit(UiStateWrapper.Loading(true))
                }
                is Resource.Success -> {
                    _editIbpr.emit(UiStateWrapper.Loading(false))
                    _editIbpr.emit(UiStateWrapper.Success(resource.data))
                }
                is Resource.Error -> {
                    _editIbpr.emit(UiStateWrapper.Loading(false))
                }
            }
        }
    }

    fun getJsa(
        startdate: String,
        enddate: String
    ) {
        collectLifecycleFlow(repository.getJsa(startdate, enddate)) { resource ->
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

    fun editJsa(edit: EditJsaReq) {
        collectLifecycleFlow(repository.editJsa(edit)) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    _editJsa.emit(UiStateWrapper.Loading(true))
                }
                is Resource.Success -> {
                    _editJsa.emit(UiStateWrapper.Loading(false))
                    _editJsa.emit(UiStateWrapper.Success(resource.data))
                }
                is Resource.Error -> {
                    _editJsa.emit(UiStateWrapper.Loading(false))
                }
            }
        }
    }
}