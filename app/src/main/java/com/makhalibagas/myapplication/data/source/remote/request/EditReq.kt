package com.makhalibagas.myapplication.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class EditReq(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)
