package com.snowdango.yumemicodetest

import com.snowdango.yumemicodetest.data.repository.ContributorsRepository
import com.snowdango.yumemicodetest.data.repository.UserInfoRepository
import com.snowdango.yumemicodetest.domain.usecases.ContributorListCreate
import com.snowdango.yumemicodetest.domain.usecases.UserInfoCreate
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Ignore


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {

    private val mockWebServer = MockWebServer()

    @Before
    fun setUp(){
        mockWebServer.start(40302)
    }

    @Ignore("実際に叩くため基本的に使用しない")
    @Test
    fun `contributor api called response status code check`() = runBlocking {
        val result = ContributorsRepository.contributorsRequest()
        assertNotNull(result)
    }

    @Ignore("実際に叩くため基本的に使用しない")
    @Test
    fun `user info api called response status code check`() = runBlocking {
        val result = UserInfoRepository.userInfoRequest("SnowDango")
        assertNotNull(result)
    }

    @Test
    fun `get success contributor list create`():Unit = runBlocking {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200).setBody(
                MockResponseFileReader("contributors_success_response.json").content
            )
        )
        val result = ContributorListCreate().getList()
        result.also {
            assert(result is ContributorListCreate.ContributorListCreateResult.Success)
        }
    }

    @Test
    fun `get failed contributor list create`(): Unit = runBlocking {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(404).setBody(
                MockResponseFileReader("contributors_failed_response.json").content
            )
        )
        val result = ContributorListCreate().getList()
        result.also {
            assert( result is ContributorListCreate.ContributorListCreateResult.Failed)
        }
    }

    @Test
    fun `get success user info create`(): Unit = runBlocking {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(200).setBody(
                MockResponseFileReader("user_info_success_response.json").content
            )
        )
        val result = UserInfoCreate().getUserInfo("hiedanene")
        result.also {
            assert( result is UserInfoCreate.UserInfoCreateResult.Success )
        }
    }

    @Test
    fun `get failed user info create`(): Unit = runBlocking {
        mockWebServer.enqueue(
            MockResponse().setResponseCode(404).setBody(
                MockResponseFileReader("user_info_failed_response.json").content
            )
        )
        val result = UserInfoCreate().getUserInfo("hiedanene")
        result.also {
            assert( result is UserInfoCreate.UserInfoCreateResult.Failed )
        }
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }

}