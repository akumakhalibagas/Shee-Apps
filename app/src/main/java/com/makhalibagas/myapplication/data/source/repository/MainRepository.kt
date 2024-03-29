package com.makhalibagas.myapplication.data.source.repository

import com.makhalibagas.myapplication.data.source.Resource
import com.makhalibagas.myapplication.data.source.remote.datasource.MainRemoteDataSource
import com.makhalibagas.myapplication.data.source.remote.network.AppApiResponse
import com.makhalibagas.myapplication.data.source.remote.request.EditReq
import com.makhalibagas.myapplication.data.source.remote.request.GreenReq
import com.makhalibagas.myapplication.data.source.remote.request.IbprReq
import com.makhalibagas.myapplication.data.source.remote.request.JsaReq
import com.makhalibagas.myapplication.data.source.remote.response.GreenItem
import com.makhalibagas.myapplication.data.source.remote.response.IbprItem
import com.makhalibagas.myapplication.data.source.remote.response.JsaItem
import com.makhalibagas.myapplication.data.source.remote.response.SheeResponse
import com.makhalibagas.myapplication.domain.repository.IMainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(val remoteDataSource: MainRemoteDataSource) : IMainRepository{

    override fun getGreen(): Flow<Resource<List<GreenItem>>> =
        flow {
            emit(Resource.Loading)
            when (val apiResource =
                remoteDataSource.getGreen().first()) {
                is AppApiResponse.Success -> {
                    emit(Resource.Success(apiResource.data))
                }
                is AppApiResponse.Error -> {
                    emit(Resource.Error(apiResource.msg))
                }
            }
        }

    override fun delGreen(id: String): Flow<Resource<SheeResponse>> =
        flow {
            emit(Resource.Loading)
            when (val apiResource =
                remoteDataSource.delGreen(id).first()) {
                is AppApiResponse.Success -> {
                    emit(Resource.Success(apiResource.data))
                }
                is AppApiResponse.Error -> {
                    emit(Resource.Error(apiResource.msg))
                }
            }
        }

    override fun addGreen(green: GreenReq): Flow<Resource<SheeResponse>> =
        flow {
            emit(Resource.Loading)
            when (val apiResource =
                remoteDataSource.addGreen(green).first()) {
                is AppApiResponse.Success -> {
                    emit(Resource.Success(apiResource.data))
                }
                is AppApiResponse.Error -> {
                    emit(Resource.Error(apiResource.msg))
                }
            }
        }

    override fun editGreen(edit: EditReq): Flow<Resource<SheeResponse>> =
        flow {
            emit(Resource.Loading)
            when (val apiResource =
                remoteDataSource.editGreen(edit).first()) {
                is AppApiResponse.Success -> {
                    emit(Resource.Success(apiResource.data))
                }
                is AppApiResponse.Error -> {
                    emit(Resource.Error(apiResource.msg))
                }
            }
        }

    override fun getIbpr(): Flow<Resource<List<IbprItem>>> =
        flow {
            emit(Resource.Loading)
            when (val apiResource =
                remoteDataSource.getIbpr().first()) {
                is AppApiResponse.Success -> {
                    emit(Resource.Success(apiResource.data))
                }
                is AppApiResponse.Error -> {
                    emit(Resource.Error(apiResource.msg))
                }
            }
        }

    override fun delIbpr(id: String): Flow<Resource<SheeResponse>> =
        flow {
            emit(Resource.Loading)
            when (val apiResource =
                remoteDataSource.delIbpr(id).first()) {
                is AppApiResponse.Success -> {
                    emit(Resource.Success(apiResource.data))
                }
                is AppApiResponse.Error -> {
                    emit(Resource.Error(apiResource.msg))
                }
            }
        }
    override fun addIbpr(ibpr: IbprReq): Flow<Resource<SheeResponse>> =
        flow {
            emit(Resource.Loading)
            when (val apiResource =
                remoteDataSource.addIbpr(ibpr).first()) {
                is AppApiResponse.Success -> {
                    emit(Resource.Success(apiResource.data))
                }
                is AppApiResponse.Error -> {
                    emit(Resource.Error(apiResource.msg))
                }
            }
        }
    override fun editIbpr(edit: EditReq): Flow<Resource<SheeResponse>> =
        flow {
            emit(Resource.Loading)
            when (val apiResource =
                remoteDataSource.editIbpr(edit).first()) {
                is AppApiResponse.Success -> {
                    emit(Resource.Success(apiResource.data))
                }
                is AppApiResponse.Error -> {
                    emit(Resource.Error(apiResource.msg))
                }
            }
        }

    override fun getJsa(): Flow<Resource<List<JsaItem>>> =
        flow {
            emit(Resource.Loading)
            when (val apiResource =
                remoteDataSource.getJsa().first()) {
                is AppApiResponse.Success -> {
                    emit(Resource.Success(apiResource.data))
                }
                is AppApiResponse.Error -> {
                    emit(Resource.Error(apiResource.msg))
                }
            }
        }

    override fun delJsa(id: String): Flow<Resource<SheeResponse>> =
        flow {
            emit(Resource.Loading)
            when (val apiResource =
                remoteDataSource.delJsa(id).first()) {
                is AppApiResponse.Success -> {
                    emit(Resource.Success(apiResource.data))
                }
                is AppApiResponse.Error -> {
                    emit(Resource.Error(apiResource.msg))
                }
            }
        }

    override fun addJsa(jsa: JsaReq): Flow<Resource<SheeResponse>> =
        flow {
            emit(Resource.Loading)
            when (val apiResource =
                remoteDataSource.addJsa(jsa).first()) {
                is AppApiResponse.Success -> {
                    emit(Resource.Success(apiResource.data))
                }
                is AppApiResponse.Error -> {
                    emit(Resource.Error(apiResource.msg))
                }
            }
        }

}