package com.she.apps.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MonitoringItem(

	@field:SerializedName("all_data")
	val allData: String? = null,

	@field:SerializedName("persen_continue")
	val persenContinue: String? = null,

	@field:SerializedName("persen_close")
	val persenClose: String? = null,

	@field:SerializedName("close_data")
	val closeData: String? = null,

	@field:SerializedName("persen_open")
	val persenOpen: String? = null,

	@field:SerializedName("open_data")
	val openData: String? = null,

	@field:SerializedName("continue_data")
	val continueData: String? = null
)
