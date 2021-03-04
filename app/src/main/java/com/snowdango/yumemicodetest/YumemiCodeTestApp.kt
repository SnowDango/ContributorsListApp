package com.snowdango.yumemicodetest

import android.app.Application
import com.snowdango.yumemicodetest.domain.usecases.ContributorListCreate
import com.snowdango.yumemicodetest.domain.usecases.UserInfoCreate
import com.snowdango.yumemicodetest.model.contributors.ContributorListModel
import com.snowdango.yumemicodetest.model.userinfo.UserInfoModel
import com.snowdango.yumemicodetest.viewmodel.contributors.ContributorListViewModel
import com.snowdango.yumemicodetest.viewmodel.userinfo.UserInfoViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class YumemiCodeTestApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(contributorsModule)
            modules(userInfoModule)
        }
    }

    private val contributorsModule = module {
        factory { ContributorListCreate() }
        factory { ContributorListModel(get()) }
        viewModel { ContributorListViewModel(get()) }
    }

    private val userInfoModule = module {
        factory { UserInfoCreate() }
        factory { UserInfoModel(get()) }
        viewModel { UserInfoViewModel(get()) }
    }

}