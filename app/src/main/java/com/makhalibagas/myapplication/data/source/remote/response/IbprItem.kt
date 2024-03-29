package com.makhalibagas.myapplication.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class IbprItem(

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

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)
