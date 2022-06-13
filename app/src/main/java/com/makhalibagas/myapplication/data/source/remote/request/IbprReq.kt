package com.makhalibagas.myapplication.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class IbprReq(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("resiko")
	val resiko: String? = null,

	@field:SerializedName("temuan")
	val temuan: String? = null,

	@field:SerializedName("pengendalian_resiko")
	val pengendalianResiko: String? = null,

	@field:SerializedName("lokasi")
	val lokasi: String? = null,

	@field:SerializedName("kode_bahaya")
	val kodeBahaya: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

//	@field:SerializedName("shift")
//	val shift: String? = null,
//
//	@field:SerializedName("lokasi")
//	val site: String? = null,
//
//	@field:SerializedName("site")
//	val department: String? = null,
)
