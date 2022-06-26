package com.she.apps.domain.repository

import com.she.apps.data.source.Resource
import com.she.apps.data.source.remote.request.*
import com.she.apps.data.source.remote.response.*
import kotlinx.coroutines.flow.Flow

interface IMainRepository {

    fun getGreen(
        startdate: String,
        enddate: String,
        shift: String,
        status: String
    ): Flow<Resource<List<GreenItem>>>
    fun delGreen(id: String): Flow<Resource<SheeResponse>>
    fun addGreen(green: GreenReq) : Flow<Resource<SheeResponse>>
    fun editGreen(edit: EditGreenReq) : Flow<Resource<SheeResponse>>
    fun getIbpr(
        startdate: String,
        enddate: String,
        shift: String,
        status: String
    ): Flow<Resource<List<IbprItem>>>
    fun delIbpr(id: String): Flow<Resource<SheeResponse>>
    fun addIbpr(ibpr: IbprReq) : Flow<Resource<SheeResponse>>
    fun editIbpr(edit: EditIbprReq) : Flow<Resource<SheeResponse>>
    fun getJsa(
        startdate: String,
        enddate: String
    ): Flow<Resource<List<JsaItem>>>
    fun delJsa(id: String): Flow<Resource<SheeResponse>>
    fun addJsa(jsa: JsaReq) : Flow<Resource<SheeResponse>>
    fun editJsa(edit: EditJsaReq) : Flow<Resource<SheeResponse>>
    fun getMonGreen(): Flow<Resource<MonitoringItem>>
    fun getMonIbpr(): Flow<Resource<MonitoringItem>>

}