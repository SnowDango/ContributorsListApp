package com.snowdango.yumemicodetest

import com.snowdango.yumemicodetest.domain.usecases.ContributorListCreate
import com.snowdango.yumemicodetest.model.ContributorListModel
import io.mockk.*
import kotlinx.coroutines.runBlocking

import org.junit.After
import org.junit.Test
import org.junit.Before
import org.junit.runner.RunWith


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ModelUnitTest {

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
    }

    @Test
    fun `get success model`(): Unit = runBlocking {
        val mockListCreate = mockk<ContributorListCreate>(relaxed = true)
        coEvery { mockListCreate.getList() } returns ContributorListCreate.ContributorListCreateResult.Success(listOf())
        val result = ContributorListModel(mockListCreate).getContributorList()
        assert( result is ContributorListModel.Result.Success)
    }

    @Test
    fun `get failed model`(): Unit = runBlocking {
        val mockListCreate = mockk<ContributorListCreate>(relaxed = true)
        coEvery { mockListCreate.getList() } returns ContributorListCreate.ContributorListCreateResult.Failed
        val result = ContributorListModel(mockListCreate).getContributorList()
        assert( result is ContributorListModel.Result.Failed)
    }

    @After
    fun tearDown(){
    }

}