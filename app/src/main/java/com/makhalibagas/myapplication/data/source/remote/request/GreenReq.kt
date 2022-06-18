package com.makhalibagas.myapplication.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class GreenReq(

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

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("shift")
	val shift: String? = null,

	@field:SerializedName("site")
	val site: String? = null,

	@field:SerializedName("department")
	val department: String? = null,

	@field:SerializedName("pelapor")
	val pelapor: String? = null,
)
