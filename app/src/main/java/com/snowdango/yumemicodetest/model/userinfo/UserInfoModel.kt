package com.snowdango.yumemicodetest.model.userinfo

import com.snowdango.yumemicodetest.domain.entity.UserInfoResponse
import com.snowdango.yumemicodetest.domain.usecases.UserInfoCreate

class UserInfoModel(
    private val userInfoCreate: UserInfoCreate
) {

    sealed class Result{
        data class Success(val userInfo: UserInfoResponse): Result()
        object Failed: Result()
    }

    suspend fun getUserInfo(userName: String): Result{
        return when(val resultData = userInfoCreate.getUserInfo(userName)){
            is UserInfoCreate.UserInfoCreateResult.Success ->
                Result.Success(resultData.info)
            is UserInfoCreate.UserInfoCreateResult.Failed ->
                Result.Failed
        }
    }
}