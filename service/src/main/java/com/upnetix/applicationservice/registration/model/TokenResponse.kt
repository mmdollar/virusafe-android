package com.upnetix.applicationservice.registration.model

import com.google.gson.annotations.SerializedName

data class TokenResponse(
	@SerializedName("accessToken")
	val accessToken: String,
	@SerializedName("refreshToken")
	val refreshToken: String
) {

	override fun toString(): String {
		return ""
	}
}
