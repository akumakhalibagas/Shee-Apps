package com.makhalibagas.myapplication.domain.repository

import com.makhalibagas.myapplication.data.source.Resource
import com.makhalibagas.myapplication.data.source.remote.network.AppApiResponse
import com.makhalibagas.myapplication.data.source.remote.request.EditReq
import com.makhalibagas.myapplication.data.source.remote.request.GreenReq
import com.makhalibagas.myapplication.data.source.remote.request.IbprReq
import com.makhalibagas.myapplication.data.source.remote.request.JsaReq
import com.makhalibagas.myapplication.data.source.remote.response.*
import kotlinx.coroutines.flow.Flow

interface IMainRepository {

    fun getGreen(): Flow<Resource<List<GreenItem>>>
    fun delGreen(id: String): Flow<Resource<SheeResponse>>
    fun addGreen(green: GreenReq) : Flow<Resource<SheeResponse>>
    fun editGreen(edit: EditReq) : Flow<Resource<SheeResponse>>
    fun getIbpr(): Flow<Resource<List<IbprItem>>>
    fun delIbpr(id: String): Flow<Resource<SheeResponse>>
    fun addIbpr(ibpr: IbprReq) : Flow<Resource<SheeResponse>>
    fun editIbpr(edit: EditReq) : Flow<Resource<SheeResponse>>
    fun getJsa(): Flow<Resource<List<JsaItem>>>
    fun delJsa(id: String): Flow<Resource<SheeResponse>>
    fun addJsa(jsa: JsaReq) : Flow<Resource<SheeResponse>>

}