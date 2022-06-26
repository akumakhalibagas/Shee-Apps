package com.she.apps.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class RegisterReq(

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("tipe_user")
	val tipe_user: String? = null
)
