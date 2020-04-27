package com.upnetix.applicationservice.registration

import android.content.Context
import com.upnetix.applicationservice.base.BaseService
import com.upnetix.applicationservice.base.ResponseWrapper
import com.upnetix.applicationservice.registration.model.PersonalData
import com.upnetix.applicationservice.registration.model.PinRequest
import com.upnetix.applicationservice.registration.model.TokenRefreshRequest
import com.upnetix.applicationservice.registration.model.TokenRequest
import com.upnetix.applicationservice.registration.model.TokenResponse
import com.upnetix.service.sharedprefs.ISharedPrefsService
import javax.inject.Inject

class RegistrationServiceImpl @Inject constructor(
	private val sharedPrefs: ISharedPrefsService,
	private val api: IRegistrationApi,
	ctx: Context
) :
	BaseService(ctx), IRegistrationService {

	override suspend fun requestPin(phoneNumber: String): ResponseWrapper<Unit> =
		executeRetrofitCall {
			api.getPin(PinRequest(phoneNumber))
		}

	override suspend fun verifyPin(
		phoneNumber: String,
		pin: String
	): ResponseWrapper<TokenResponse> {
		val responseWrapper = executeRetrofitCall {
			api.getToken(TokenRequest(phoneNumber, pin))
		}

		if (responseWrapper is ResponseWrapper.Success) {
			val response = responseWrapper.response
			sharedPrefs.writeStringToSharedPrefs(HAS_REGISTRATION_KEY, TRUE_VALUE)
			saveTokens(response)
		}
		return responseWrapper
	}

	override suspend fun refreshToken(): ResponseWrapper<TokenResponse> {
		val token = sharedPrefs.readStringFromSharedPrefs(REFRESH_TOKEN_KEY)
		if (token.isBlank()) {
			return ResponseWrapper.Error(code = INVALID_TOKEN)
		}

		sharedPrefs.clearValue(NEW_ACCESS_TOKEN_KEY)
		val responseWrapper = executeRetrofitCall {
			api.refreshToken(TokenRefreshRequest(token))
		}

		if (responseWrapper is ResponseWrapper.Success) {
			saveTokens(responseWrapper.response)
		}
		return responseWrapper
	}

	override suspend fun getPersonalData(): ResponseWrapper<PersonalData> =
		executeRetrofitCall { api.getPersonalData() }

	override suspend fun sendPersonalData(personalData: PersonalData): ResponseWrapper<Unit> =
		executeRetrofitCall { api.postPersonalData(personalData) }

	private fun saveTokens(response: TokenResponse) = with(sharedPrefs) {
		writeStringToSharedPrefs(NEW_ACCESS_TOKEN_KEY, response.accessToken)
		writeStringToSharedPrefs(REFRESH_TOKEN_KEY, response.refreshToken)
	}

	companion object {
		const val HAS_REGISTRATION_KEY = "com.upnetix.applicationservice.key1"
		const val OLD_TOKEN_KEY = "com.upnetix.applicationservice.key2"
		const val NEW_ACCESS_TOKEN_KEY = "com.upnetix.applicationservice.key2.1"
		const val REFRESH_TOKEN_KEY = "com.upnetix.applicationservice.key2.2"
		const val FINISHED_REGISTRATION_KEY = "com.upnetix.applicationservice.key3"

		private const val TRUE_VALUE = "true"
	}
}
