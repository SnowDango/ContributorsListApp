package com.snowdango.yumemicodetest.domain.usecases

import com.snowdango.yumemicodetest.data.repository.UserInfoRepository
import com.snowdango.yumemicodetest.domain.entity.UserInfoResponse

class UserInfoCreate {

    sealed class UserInfoCreateResult{
        data class Success(val info: UserInfoResponse): UserInfoCreateResult()
        object Failed: UserInfoCreateResult()
    }

    suspend fun getUserInfo(userName: String): UserInfoCreateResult{
        val result: UserInfoResponse? = UserInfoRepository.userInfoRequest(userName)
        return if (result != null) {
            UserInfoCreateResult.Success(result)
        }else{
            UserInfoCreateResult.Failed
        }
    }
}