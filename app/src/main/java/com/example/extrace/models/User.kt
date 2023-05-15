package com.example.extrace.models

import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.jackson.jackson
import kotlinx.coroutines.runBlocking

data class User(
    val id: Int,
    val username: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val telephone: String,
)

data class UserBody(
    val username: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val telephone: String,
)

object UserLoader {

    fun getAll(): List<User> = runBlocking {
        val client = HttpClient {
            install(ContentNegotiation) {
                jackson {
                    configure(SerializationFeature.INDENT_OUTPUT, true)
                    setDefaultPrettyPrinter(DefaultPrettyPrinter().apply {
                        indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
                        indentObjectsWith(DefaultIndenter("  ", "\n"))
                    })
                    registerModule(JavaTimeModule())  // support java.time.* types
                }
            }
        }
        val users: List<User> = client.get("http://124.71.1.201/user").body()
        client.close()
        return@runBlocking users
    }

    fun add(userBody: UserBody): Boolean = runBlocking {
        val client = HttpClient {
            install(ContentNegotiation) {
                jackson {
                    configure(SerializationFeature.INDENT_OUTPUT, true)
                    setDefaultPrettyPrinter(DefaultPrettyPrinter().apply {
                        indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
                        indentObjectsWith(DefaultIndenter("  ", "\n"))
                    })
                    registerModule(JavaTimeModule())  // support java.time.* types
                }
            }
        }
        val response: Map<String, Boolean> = client.post("http://124.71.1.201/user/add") {
            contentType(ContentType.Application.Json)
            setBody(userBody)
        }.body()
        client.close()
        return@runBlocking response["OK"] == true
    }

    fun login(username: String, password: String): Boolean = runBlocking {
        val client = HttpClient {
            install(ContentNegotiation) {
                jackson {
                    configure(SerializationFeature.INDENT_OUTPUT, true)
                    setDefaultPrettyPrinter(DefaultPrettyPrinter().apply {
                        indentArraysWith(DefaultPrettyPrinter.FixedSpaceIndenter.instance)
                        indentObjectsWith(DefaultIndenter("  ", "\n"))
                    })
                    registerModule(JavaTimeModule())  // support java.time.* types
                }
            }
        }
        val response: Map<String, Boolean> = client.post("http://124.71.1.201/user/login/${username}/${password}").body()
        client.close()
        return@runBlocking response["OK"] == true
    }
}

