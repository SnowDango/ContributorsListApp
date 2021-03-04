package com.snowdango.yumemicodetest.model

import com.snowdango.yumemicodetest.domain.entity.UserInfoResponse
import com.snowdango.yumemicodetest.domain.usecases.UserInfoCreate
import com.snowdango.yumemicodetest.model.userinfo.UserInfoModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class UserInfoModelUnitTest {

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
    }

    @Test
    fun `get success user info model`() = runBlocking {
        val mockCreate = mockk<UserInfoCreate>(relaxed = true)
        coEvery { mockCreate.getUserInfo("") } returns UserInfoCreate.UserInfoCreateResult.Success(
            UserInfoResponse("",0,"","","","",
                "","","","","",
                "","","","","",
                "",false,"","","","","",
                "","","",0,0,0,
                0,"","")
        )
        val result = UserInfoModel(mockCreate).getUserInfo("")
        assert( result is UserInfoModel.Result.Success )
    }

    @Test
    fun `get failed user info model`() = runBlocking {
        val mockCreate = mockk<UserInfoCreate>(relaxed = true)
        coEvery { mockCreate.getUserInfo("") } returns UserInfoCreate.UserInfoCreateResult.Failed
        val result = UserInfoModel(mockCreate).getUserInfo("")
        assert( result is UserInfoModel.Result.Failed )
    }

    @After
    fun tearDown(){
    }

}