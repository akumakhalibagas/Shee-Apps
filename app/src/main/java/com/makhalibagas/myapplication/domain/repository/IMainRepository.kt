package com.makhalibagas.myapplication.domain.repository

import com.makhalibagas.myapplication.data.source.Resource
import com.makhalibagas.myapplication.data.source.remote.request.*
import com.makhalibagas.myapplication.data.source.remote.response.*
import kotlinx.coroutines.flow.Flow

interface IMainRepository {

    fun getGreen(): Flow<Resource<List<GreenItem>>>
    fun delGreen(id: String): Flow<Resource<SheeResponse>>
    fun addGreen(green: GreenReq) : Flow<Resource<SheeResponse>>
    fun editGreen(edit: EditGreenReq) : Flow<Resource<SheeResponse>>
    fun getIbpr(): Flow<Resource<List<IbprItem>>>
    fun delIbpr(id: String): Flow<Resource<SheeResponse>>
    fun addIbpr(ibpr: IbprReq) : Flow<Resource<SheeResponse>>
    fun editIbpr(edit: EditIbprReq) : Flow<Resource<SheeResponse>>
    fun getJsa(): Flow<Resource<List<JsaItem>>>
    fun delJsa(id: String): Flow<Resource<SheeResponse>>
    fun addJsa(jsa: JsaReq) : Flow<Resource<SheeResponse>>
    fun editJsa(edit: EditJsaReq) : Flow<Resource<SheeResponse>>
    fun getMonGreen(): Flow<Resource<MonitoringItem>>
    fun getMonIbpr(): Flow<Resource<MonitoringItem>>

}