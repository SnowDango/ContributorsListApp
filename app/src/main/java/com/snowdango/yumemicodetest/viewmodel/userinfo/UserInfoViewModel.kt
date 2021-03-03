package com.snowdango.yumemicodetest.viewmodel.userinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snowdango.yumemicodetest.domain.entity.UserInfoResponse
import com.snowdango.yumemicodetest.model.userinfo.UserInfoModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserInfoViewModel(private val model: UserInfoModel): ViewModel() {

    fun orderUserInfo(userName: String): Observable<UserInfoResponse>{
        return Single.create<UserInfoResponse>{
            viewModelScope.launch(Dispatchers.Default){
                when(val modelResult = model.getUserInfo(userName)){
                    is UserInfoModel.Result.Success ->
                        it.onSuccess(modelResult.userInfo)
                    is UserInfoModel.Result.Failed ->
                        it.onError(Throwable())
                }
            }
            return@create
        }.toObservable()
    }
}