package com.snowdango.yumemicodetest.data.repository

import com.snowdango.yumemicodetest.data.netwrok.ApiProvider
import com.snowdango.yumemicodetest.domain.entity.ContributorsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ContributorsRepository {

    suspend fun contributorsRequest(): List<ContributorsResponse>? {
        val response = withContext(Dispatchers.Default) { ApiProvider.contributorsApi.getContributor().execute() }
        return when(response.code()){
            200 -> response.body()
            else -> null
        }
    }

}