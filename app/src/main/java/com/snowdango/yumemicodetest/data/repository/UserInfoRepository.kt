package com.snowdango.yumemicodetest.data.repository

import com.snowdango.yumemicodetest.data.netwrok.ApiProvider
import com.snowdango.yumemicodetest.domain.entity.UserInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection

object UserInfoRepository {

    suspend fun userInfoRequest(user: String): UserInfoResponse? {
        val response = withContext(Dispatchers.Default) { ApiProvider.userInfoApi.getUserInfo(user).execute() }
        return when(response.code()){
            HttpURLConnection.HTTP_OK -> response.body()
            else -> null
        }
    }

}