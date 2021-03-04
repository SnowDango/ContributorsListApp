package com.snowdango.yumemicodetest.viewmodel.contributors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snowdango.yumemicodetest.domain.entity.ContributorsResponse
import com.snowdango.yumemicodetest.model.contributors.ContributorListModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContributorListViewModel(private val contributorListModel: ContributorListModel): ViewModel() {

    fun orderContributorList(): Observable<List<ContributorsResponse>?>{
        return Single.create<List<ContributorsResponse>?> {
            viewModelScope.launch(Dispatchers.Default){
                when(val modelResult = contributorListModel.getContributorList()){
                    is ContributorListModel.Result.Success ->
                        it.onSuccess(modelResult.list)
                    is ContributorListModel.Result.Failed ->
                        it.onError(Throwable())
                }
            }
            return@create
        }.toObservable()
    }

}