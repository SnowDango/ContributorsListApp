package com.snowdango.yumemicodetest.model.contributors

import com.snowdango.yumemicodetest.domain.entity.ContributorsResponse
import com.snowdango.yumemicodetest.domain.usecases.ContributorListCreate

class ContributorListModel(
    private val contributorListCreate: ContributorListCreate,
){

    sealed class Result{
        data class Success(val list: List<ContributorsResponse>): Result()
        object Failed: Result()
    }

    suspend fun getContributorList(): Result {
        return when(val resultData = contributorListCreate.getList()){
            is ContributorListCreate.ContributorListCreateResult.Success ->
                Result.Success(resultData.listData)
            is ContributorListCreate.ContributorListCreateResult.Failed ->
                Result.Failed
        }
    }
}