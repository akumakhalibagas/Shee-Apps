package com.makhalibagas.myapplication.data.source.remote.datasource

import com.makhalibagas.myapplication.data.source.remote.network.AppApiResponse
import com.makhalibagas.myapplication.data.source.remote.network.AppApiService
import com.makhalibagas.myapplication.data.source.remote.request.EditReq
import com.makhalibagas.myapplication.data.source.remote.request.GreenReq
import com.makhalibagas.myapplication.data.source.remote.request.IbprReq
import com.makhalibagas.myapplication.data.source.remote.request.JsaReq
import com.makhalibagas.myapplication.data.source.remote.response.GreenItem
import com.makhalibagas.myapplication.data.source.remote.response.IbprItem
import com.makhalibagas.myapplication.data.source.remote.response.JsaItem
import com.makhalibagas.myapplication.data.source.remote.response.SheeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRemoteDataSource @Inject constructor(val apiService: AppApiService) {

    fun getGreen(): Flow<AppApiResponse<List<GreenItem>>> =
        flow {
            try {
                val response = apiService.getGreen()
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

    fun addGreen(green: GreenReq) : Flow<AppApiResponse<SheeResponse>> =
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

    fun editGreen(edit: EditReq) : Flow<AppApiResponse<SheeResponse>> =
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

    fun getIbpr(): Flow<AppApiResponse<List<IbprItem>>> =
        flow {
            try {
                val response = apiService.getIbpr()
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

    fun addIbpr(ibpr: IbprReq) : Flow<AppApiResponse<SheeResponse>> =
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

    fun editIbpr(edit: EditReq) : Flow<AppApiResponse<SheeResponse>> =
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

    fun getJsa(): Flow<AppApiResponse<List<JsaItem>>> =
        flow {
            try {
                val response = apiService.getJsa()
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

    fun addJsa(jsa: JsaReq) : Flow<AppApiResponse<SheeResponse>> =
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
}