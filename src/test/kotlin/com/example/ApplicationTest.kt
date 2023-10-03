package com.example

import com.example.models.MobileNo
import com.example.models.User
import com.example.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.*

class ApplicationTest {

    @Test
    fun testPost() = testApplication {
        application {
            module(configureRouting())
        }
        val client1 = createClient {
            install(ContentNegotiation){
                json()
            }
        }
        val res =client1.post("/") {
            setBody(User("sai","gbh@gmail.com"))
            contentType(ContentType.Application.Json)
        }
            assertEquals(HttpStatusCode.Created, res.status)
    }

    @Test
    fun testUser() = testApplication {
        application {
            module(configureRouting())
        }
        val client1 = createClient {
            install(ContentNegotiation){
                json()
            }
        }
        val res =client1.post("/user") {
            setBody(User("sai","gbh@gmail.com"))
            contentType(ContentType.Application.Json)
        }
        assertEquals(HttpStatusCode.Created, res.status)
    }

    @Test
    fun testMobileVer() = testApplication {
        application {
            module(configureRouting())
        }
        val client1 = createClient {
            install(ContentNegotiation){
                json()
            }
        }
        val res =client1.post("/verification/mobileVer") {
            setBody(MobileNo(1,6309883522))
            contentType(ContentType.Application.Json)
        }
        assertEquals(HttpStatusCode.OK, res.status)
    }



}
