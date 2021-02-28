package com.snowdango.yumemicodetest.data.repository

import com.snowdango.yumemicodetest.data.netwrok.ApiProvider
import com.snowdango.yumemicodetest.domain.entity.ContributorsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object ContributorsRepository {

    suspend fun contributorsRequest(): List<ContributorsResponse>? {
        val result = withContext(Dispatchers.Default) { ApiProvider.contributorsApi.getContributor().execute() }
        return when(result.code()){
            200 -> result.body()
            else -> null
        }
    }

}