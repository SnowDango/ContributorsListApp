package com.snowdango.yumemicodetest

import com.snowdango.yumemicodetest.model.ContributorListModel
import com.snowdango.yumemicodetest.viewmodel.ContributorListViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Test

class ViewModelUnitTest {

    @Before
    fun setUp(){
        MockKAnnotations.init(this)
    }

    @Test
    fun `get success rx observable`() {
        val mockModel = mockk<ContributorListModel>(relaxed = true)
        coEvery { mockModel.getContributorList() } returns ContributorListModel.Result.Success(listOf())
        val viewModel = ContributorListViewModel(mockModel)
        viewModel.orderContributorList().subscribe({
            assert(true)
        },{
            assert(false)
        })
    }

    @Test
    fun `get failed rx observable`() {
        val mockModel = mockk<ContributorListModel>(relaxed = true)
        coEvery { mockModel.getContributorList() } returns ContributorListModel.Result.Failed
        val viewModel = ContributorListViewModel(mockModel)
        viewModel.orderContributorList().subscribe({
            assert(false)
        },{
            assert(true)
        })
    }

    @After
    fun tearDown(){
    }
}