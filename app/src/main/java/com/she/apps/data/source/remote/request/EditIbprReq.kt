package com.she.apps.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class EditIbprReq(

	@field:SerializedName("id")
	val id: Int? = null,

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

	@field:SerializedName("shift")
	val shift: String? = null,

	@field:SerializedName("site")
	val site: String? = null,

	@field:SerializedName("department")
	val department: String? = null,

	@field:SerializedName("pelapor")
	val pelapor: String? = null,

	@field:SerializedName("bahaya")
	val bahaya: String? = null
)
