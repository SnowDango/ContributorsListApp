package com.snowdango.yumemicodetest.domain.usecases

import com.snowdango.yumemicodetest.data.repository.ContributorsRepository
import com.snowdango.yumemicodetest.domain.entity.ContributorsResponse

class ContributorListCreate {

    sealed class ContributorListCreateResult {
        data class Success(val listData:List<ContributorsResponse>): ContributorListCreateResult()
        object Failed: ContributorListCreateResult()
    }

    suspend fun getList(): ContributorListCreateResult {
        val result: List<ContributorsResponse>? = ContributorsRepository.contributorsRequest()
        return if(result.isNullOrEmpty()){
            ContributorListCreateResult.Failed
        }else{
            ContributorListCreateResult.Success(result)
        }
    }

}