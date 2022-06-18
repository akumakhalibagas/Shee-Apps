package com.makhalibagas.myapplication.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class JsaReq(

    @field:SerializedName("perusahaan")
    val perusahaan: String? = null,

    @field:SerializedName("pekerja")
    val pekerja: String? = null,

    @field:SerializedName("pekerjaan")
    val pekerjaan: String? = null,

    @field:SerializedName("tanggal")
    val tanggal: String? = null,

    @field:SerializedName("supervisor")
    val supervisor: String? = null,


    @field:SerializedName("department")
    val department: String? = null,

    @field:SerializedName("bahaya")
    val bahaya: String? = null,

    @field:SerializedName("pengendalian")
    val pengendalian: String? = null,

    @field:SerializedName("tanggung_jawab")
    val tanggungJawab: String? = null
)
