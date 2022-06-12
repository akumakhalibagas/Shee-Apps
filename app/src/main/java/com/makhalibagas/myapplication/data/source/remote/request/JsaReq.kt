package com.makhalibagas.myapplication.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class JsaReq(

	@field:SerializedName("anggota1")
	val anggota1: String? = null,

	@field:SerializedName("anggota2")
	val anggota2: String? = null,

	@field:SerializedName("bahaya")
	val bahaya: String? = null,

	@field:SerializedName("pengendalian")
	val pengendalian: String? = null,

	@field:SerializedName("anggota3")
	val anggota3: String? = null,

	@field:SerializedName("anggota4")
	val anggota4: String? = null,

	@field:SerializedName("perusahaan")
	val perusahaan: String? = null,

	@field:SerializedName("pekerjaan")
	val pekerjaan: String? = null,

	@field:SerializedName("tahap")
	val tahap: String? = null,

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
