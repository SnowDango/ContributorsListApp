package com.snowdango.yumemicodetest.data.repository

import com.snowdango.yumemicodetest.data.netwrok.ApiProvider
import com.snowdango.yumemicodetest.domain.entity.ContributorsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.HTTP
import java.net.HttpURLConnection

object ContributorsRepository {

    suspend fun contributorsRequest(): List<ContributorsResponse>? {
        val response = withContext(Dispatchers.Default) { ApiProvider.contributorsApi.getContributor().execute() }
        return when(response.code()){
            HttpURLConnection.HTTP_OK -> response.body()
            else -> null
        }
    }

}