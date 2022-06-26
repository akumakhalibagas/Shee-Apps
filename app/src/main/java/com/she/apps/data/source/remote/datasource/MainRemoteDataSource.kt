package com.she.apps.data.source.remote.datasource

import com.she.apps.data.source.remote.network.AppApiResponse
import com.she.apps.data.source.remote.network.AppApiService
import com.she.apps.data.source.remote.request.*
import com.she.apps.data.source.remote.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRemoteDataSource @Inject constructor(val apiService: AppApiService) {

    fun getGreen(
        startdate: String,
        enddate: String,
        shift: String,
        status: String
    ): Flow<AppApiResponse<List<GreenItem>>> =
        flow {
            try {
                val response = apiService.getGreen(startdate, enddate, shift, status)
                if (response.isNotEmpty()) {
                    emit(AppApiResponse.Success(response))
                } else {
                    emit(AppApiResponse.Error("Data Kosong"))
                }
            } catch (e: Exception) {
                emit(AppApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    fun delGreen(id: String): Flow<AppApiResponse<SheeResponse>> =
        flow {
            try {
                val response = apiService.delGreen(id)
                if (response.status.equals("success")) {
                    emit(AppApiResponse.Success(response))
                } else {
                    emit(AppApiResponse.Error("error"))
                }
            } catch (e: Exception) {
                emit(AppApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    fun addGreen(green: GreenReq): Flow<AppApiResponse<SheeResponse>> =
        flow {
            try {
                val response = apiService.addGreen(green)
                if (response.status.equals("success")) {
                    emit(AppApiResponse.Success(response))
                } else {
                    emit(AppApiResponse.Error("error"))
                }
            } catch (e: Exception) {
                emit(AppApiResponse.Error("error"))
            }
        }.flowOn(Dispatchers.IO)

    fun editGreen(edit: EditGreenReq): Flow<AppApiResponse<SheeResponse>> =
        flow {
            try {
                val response = apiService.editGreen(edit)
                if (response.status.equals("success")) {
                    emit(AppApiResponse.Success(response))
                } else {
                    emit(AppApiResponse.Error("error"))
                }
            } catch (e: Exception) {
                emit(AppApiResponse.Error("error"))
            }
        }.flowOn(Dispatchers.IO)

    fun getIbpr(
        startdate: String,
        enddate: String,
        shift: String,
        status: String
    ): Flow<AppApiResponse<List<IbprItem>>> =
        flow {
            try {
                val response = apiService.getIbpr(startdate, enddate, shift, status)
                if (response.isNotEmpty()) {
                    emit(AppApiResponse.Success(response))
                } else {
                    emit(AppApiResponse.Error("Data Kosong"))
                }
            } catch (e: Exception) {
                emit(AppApiResponse.Error("error"))
            }
        }.flowOn(Dispatchers.IO)

    fun delIbpr(id: String): Flow<AppApiResponse<SheeResponse>> =
        flow {
            try {
                val response = apiService.delIbpr(id)
                if (response.status.equals("success")) {
                    emit(AppApiResponse.Success(response))
                } else {
                    emit(AppApiResponse.Error("error"))
                }
            } catch (e: Exception) {
                emit(AppApiResponse.Error("error"))
            }
        }.flowOn(Dispatchers.IO)

    fun addIbpr(ibpr: IbprReq): Flow<AppApiResponse<SheeResponse>> =
        flow {
            try {
                val response = apiService.addIbpr(ibpr)
                if (response.status.equals("success")) {
                    emit(AppApiResponse.Success(response))
                } else {
                    emit(AppApiResponse.Error("error"))
                }
            } catch (e: Exception) {
                emit(AppApiResponse.Error("error"))
            }
        }.flowOn(Dispatchers.IO)

    fun editIbpr(edit: EditIbprReq): Flow<AppApiResponse<SheeResponse>> =
        flow {
            try {
                val response = apiService.editIbpr(edit)
                if (response.status.equals("success")) {
                    emit(AppApiResponse.Success(response))
                } else {
                    emit(AppApiResponse.Error("error"))
                }
            } catch (e: Exception) {
                emit(AppApiResponse.Error("error"))
            }
        }.flowOn(Dispatchers.IO)

    fun getJsa(
        startdate: String,
        enddate: String
    ): Flow<AppApiResponse<List<JsaItem>>> =
        flow {
            try {
                val response = apiService.getJsa(startdate, enddate)
                if (response.isNotEmpty()) {
                    emit(AppApiResponse.Success(response))
                } else {
                    emit(AppApiResponse.Error("Data Kosong"))
                }
            } catch (e: Exception) {
                emit(AppApiResponse.Error("error"))
            }
        }.flowOn(Dispatchers.IO)

    fun delJsa(id: String): Flow<AppApiResponse<SheeResponse>> =
        flow {
            try {
                val response = apiService.delJsa(id)
                if (response.status.equals("success")) {
                    emit(AppApiResponse.Success(response))
                } else {
                    emit(AppApiResponse.Error("error"))
                }
            } catch (e: Exception) {
                emit(AppApiResponse.Error("error"))
            }
        }.flowOn(Dispatchers.IO)

    fun addJsa(jsa: JsaReq): Flow<AppApiResponse<SheeResponse>> =
        flow {
            try {
                val response = apiService.addJsa(jsa)
                if (response.status.equals("success")) {
                    emit(AppApiResponse.Success(response))
                } else {
                    emit(AppApiResponse.Error("error"))
                }
            } catch (e: Exception) {
                emit(AppApiResponse.Error("error"))
            }
        }.flowOn(Dispatchers.IO)

    fun editJsa(edit: EditJsaReq): Flow<AppApiResponse<SheeResponse>> =
        flow {
            try {
                val response = apiService.editJsa(edit)
                if (response.status.equals("success")) {
                    emit(AppApiResponse.Success(response))
                } else {
                    emit(AppApiResponse.Error("error"))
                }
            } catch (e: Exception) {
                emit(AppApiResponse.Error("error"))
            }
        }.flowOn(Dispatchers.IO)

    fun getMonGreen(): Flow<AppApiResponse<MonitoringItem>> =
        flow {
            try {
                val response = apiService.getMonGreen()
                if (response != null) {
                    emit(AppApiResponse.Success(response))
                } else {
                    emit(AppApiResponse.Error("Data Kosong"))
                }
            } catch (e: Exception) {
                emit(AppApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)

    fun getMonIbpr(): Flow<AppApiResponse<MonitoringItem>> =
        flow {
            try {
                val response = apiService.getMonIbpr()
                if (response != null) {
                    emit(AppApiResponse.Success(response))
                } else {
                    emit(AppApiResponse.Error("Data Kosong"))
                }
            } catch (e: Exception) {
                emit(AppApiResponse.Error(e.message.toString()))
            }
        }.flowOn(Dispatchers.IO)
}