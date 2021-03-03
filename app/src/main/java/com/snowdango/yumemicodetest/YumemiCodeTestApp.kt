package com.snowdango.yumemicodetest

import android.app.Application
import com.snowdango.yumemicodetest.domain.usecases.ContributorListCreate
import com.snowdango.yumemicodetest.model.contributors.ContributorListModel
import com.snowdango.yumemicodetest.viewmodel.contributors.ContributorListViewModel
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
        }
    }

    private val contributorsModule = module {
        factory { ContributorListCreate() }
        factory { ContributorListModel(get()) }
        viewModel { ContributorListViewModel(get()) }
    }

}