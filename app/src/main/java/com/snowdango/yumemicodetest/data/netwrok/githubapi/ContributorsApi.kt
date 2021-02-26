package com.snowdango.yumemicodetest.data.netwrok.githubapi

import com.snowdango.yumemicodetest.BuildConfig
import com.snowdango.yumemicodetest.domain.entity.ContributorsResponse
import retrofit2.Call
import retrofit2.http.GET


interface ContributorsApi {
    @GET(BuildConfig.API_GITHUB_CONTRIBUTOR)
    fun getContributor(): Call<ContributorsResponse>
}