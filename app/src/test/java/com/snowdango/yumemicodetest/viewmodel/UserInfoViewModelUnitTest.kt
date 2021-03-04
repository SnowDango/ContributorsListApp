package com.snowdango.yumemicodetest.viewmodel

import com.snowdango.yumemicodetest.domain.entity.UserInfoResponse
import com.snowdango.yumemicodetest.model.userinfo.UserInfoModel
import com.snowdango.yumemicodetest.viewmodel.userinfo.UserInfoViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import io.reactivex.rxjava3.subscribers.TestSubscriber
import okhttp3.internal.wait
import org.junit.After
import org.junit.Before
import org.junit.Test

class UserInfoViewModelUnitTest {

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
    }

    @Test
    fun `get success view model`(){
        val mockModel = mockk<UserInfoModel>(relaxed = true)
        coEvery { mockModel.getUserInfo("") } returns UserInfoModel.Result.Success(
            UserInfoResponse("",0,"","","","",
                "","","","","",
                "","","","","",
                "",false,"","","","","",
                "","","",0,0,0,
                0,"","")
        )
        val viewModel = UserInfoViewModel(mockModel)
        viewModel.orderUserInfo("").test().await().also {
            it.assertValue { r -> (r is UserInfoResponse) }
            it.assertComplete()
        }
    }

    @Test
    fun `get failed view model`(){
        val mockModel = mockk<UserInfoModel>(relaxed = false)
        coEvery { mockModel.getUserInfo("") } returns UserInfoModel.Result.Failed
        val viewModel = UserInfoViewModel(mockModel)
        viewModel.orderUserInfo("").test().await().also {
            it.assertNotComplete()
            it.assertError { e -> (e is Throwable) }
        }
    }

    @After
    fun tearDown(){
    }
}