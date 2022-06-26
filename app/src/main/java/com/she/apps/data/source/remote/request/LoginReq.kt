package com.she.apps.data.source.remote.request

import com.google.gson.annotations.SerializedName

data class LoginReq(

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("password")
	val password: String? = null,
)
