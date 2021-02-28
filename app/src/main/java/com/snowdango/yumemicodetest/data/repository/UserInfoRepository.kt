package com.snowdango.yumemicodetest.data.repository

import com.snowdango.yumemicodetest.data.netwrok.ApiProvider
import com.snowdango.yumemicodetest.domain.entity.UserInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object UserInfoRepository {

    suspend fun userInfoRequest(user: String): UserInfoResponse? {
        val result = withContext(Dispatchers.Default) { ApiProvider.userInfoApi.getUserInfo(user).execute() }
        return when(result.code()){
            200 -> result.body()
            else -> null
        }
    }

}