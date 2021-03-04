package com.snowdango.yumemicodetest.data.netwrok.githubapi

import com.snowdango.yumemicodetest.BuildConfig
import com.snowdango.yumemicodetest.domain.entity.UserInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserInfoApi {

    @GET(BuildConfig.API_GITHUB_USER+"{user}")
    fun getUserInfo(
        @Path("user") user: String
    ):Call<UserInfoResponse>
}