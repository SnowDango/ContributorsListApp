package com.snowdango.yumemicodetest.viewmodel

import com.snowdango.yumemicodetest.domain.entity.ContributorsResponse
import com.snowdango.yumemicodetest.model.contributors.ContributorListModel
import com.snowdango.yumemicodetest.viewmodel.contributors.ContributorListViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Test

class ContributorViewModelUnitTest {

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
    }

    @Test
    fun `get success rx observable`() {
        val mockModel = mockk<ContributorListModel>(relaxed = true)
        coEvery { mockModel.getContributorList() } returns ContributorListModel.Result.Success(listOf())
        val viewModel = ContributorListViewModel(mockModel)
        viewModel.orderContributorList().test().await().also {
            it.assertValue { r -> (r is List<ContributorsResponse>) }
            it.assertComplete()
        }
    }

    @Test
    fun `get failed rx observable`() {
        val mockModel = mockk<ContributorListModel>(relaxed = true)
        coEvery { mockModel.getContributorList() } returns ContributorListModel.Result.Failed
        val viewModel = ContributorListViewModel(mockModel)
        viewModel.orderContributorList().test().await().also {
            it.assertError { t -> (t is Throwable) }
            it.assertNotComplete()
        }
    }

    @After
    fun tearDown(){
    }
}