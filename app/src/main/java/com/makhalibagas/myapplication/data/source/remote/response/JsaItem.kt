package com.makhalibagas.myapplication.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class JsaItem(

	@field:SerializedName("bahaya")
	val bahaya: String? = null,

	@field:SerializedName("pengendalian")
	val pengendalian: String? = null,

	@field:SerializedName("perusahaan")
	val perusahaan: String? = null,

	@field:SerializedName("pekerjaan")
	val pekerjaan: String? = null,

	@field:SerializedName("tahap")
	val tahap: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("hse")
	val hse: String? = null,

	@field:SerializedName("tanggung_jawab")
	val tanggungJawab: String? = null,

	@field:SerializedName("pekerja")
	val pekerja: String? = null,

	@field:SerializedName("supervisor")
	val supervisor: String? = null
)
