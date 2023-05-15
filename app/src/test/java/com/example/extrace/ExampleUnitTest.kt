package com.example.extrace

import com.example.extrace.models.UserBody
import com.example.extrace.models.UserLoader
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_get_users() {
        val users = UserLoader.getAll()
        println(users)
    }

    @Test
    fun test_add_user() {
        val status = UserLoader.add(
            UserBody(
                "国家一级小丑",
                "005135",
                "承林",
                "蔡",
                "18853187736"
            )
        )
        println(status)
    }

    @Test
    fun test_login() {
        val status = UserLoader.login(
            username = "0Pinky0",
            password = "005135",
        )
        println(status)
    }

    @Test
    fun test_url() {
        val username = "0Pinky0"
        val password = "005135"
        val url = "http://124.71.1.201/user/login/${username}/${password}"
        println(url)
    }

}