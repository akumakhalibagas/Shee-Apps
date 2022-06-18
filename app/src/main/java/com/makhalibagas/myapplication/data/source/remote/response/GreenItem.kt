package com.makhalibagas.myapplication.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GreenItem(

    @field:SerializedName("date")
    val date: String? = null,

    @field:SerializedName("saran")
    val saran: String? = null,

    @field:SerializedName("kondisi")
    val kondisi: String? = null,

    @field:SerializedName("lokasi")
    val lokasi: String? = null,

    @field:SerializedName("dibicarakan")
    val dibicarakan: String? = null,

    @field:SerializedName("kategori")
    val kategori: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("shift")
    val shift: String? = null,

    @field:SerializedName("site")
    val site: String? = null,

    @field:SerializedName("department")
    val department: String? = null,

    @field:SerializedName("pelapor")
    val pelapor: String? = null

) : Parcelable
