package com.example.routingTest

import com.example.models.*
import com.example.module
import com.example.plugins.configureRouting
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals


class UserRoutingTest {

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

    @Test
    fun testEmail() = testApplication {
        application {
            module(configureRouting())
        }
        val client1 = createClient {
            install(ContentNegotiation){
                json()
            }
        }
        val res =client1.post("/verification/email") {
            setBody(Email(1,"gbh@gmail.com"))
            contentType(ContentType.Application.Json)
        }
        assertEquals(HttpStatusCode.OK, res.status)
    }

    @Test
    fun testMpin() = testApplication {
        application {
            module(configureRouting())
        }
        val client1 = createClient {
            install(ContentNegotiation){
                json()
            }
        }
        val res =client1.post("/verification/setMPIN") {
            setBody(Mpin(1,"124575"))
            contentType(ContentType.Application.Json)
        }
        assertEquals(HttpStatusCode.OK, res.status)
    }

    @Test
    fun testadhar() = testApplication {
        application {
            module(configureRouting())
        }
        val client1 = createClient {
            install(ContentNegotiation){
                json()
            }
        }
        val res =client1.post("/verification/adhar") {
            setBody(AdharNo(1,"457844775847"))
            contentType(ContentType.Application.Json)
        }
        assertEquals(HttpStatusCode.OK, res.status)
    }

    @Test
    fun testPan() = testApplication {
        application {
            module(configureRouting())
        }
        val client1 = createClient {
            install(ContentNegotiation){
                json()
            }
        }
        val res =client1.post("/verification/pan") {
            setBody(PanNo(1,"pqaws4147e"))
            contentType(ContentType.Application.Json)
        }
        assertEquals(HttpStatusCode.OK, res.status)
    }

    @Test
    fun test2FA() = testApplication {
        application {
            module(configureRouting())
        }
        val client1 = createClient {
            install(ContentNegotiation){
                json()
            }
        }
        val res =client1.post("/verification/2FA") {
            setBody(Id(1))
            contentType(ContentType.Application.Json)
        }
        assertEquals(HttpStatusCode.OK, res.status)
    }
}



