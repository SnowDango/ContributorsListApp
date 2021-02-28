package com.snowdango.yumemicodetest

import com.snowdango.yumemicodetest.data.repository.ContributorsRepository
import com.snowdango.yumemicodetest.data.repository.UserInfoRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Ignore

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
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

}