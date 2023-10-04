package com.example.routingTest

import com.example.endPoints.*
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
    private val user = User("govindasai","govindasai101@gmail.com")
    private val mobileNumber = MobileNo(1,6309883522)
    private val emailId = Email(1,"gbh@gmail.com")
    private val mPin = Mpin(1,"124575")
    private val adharNo = AdharNo(1,"457844775847")
    private val panNo = PanNo(1,"PQSPS3121R")
    private val id = Id(1)

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
        val res =client1.post(USER_PATH) {
            setBody(user)
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
        val res =client1.post(BASE_PATH + MOBILE_VERIFICATION) {
            setBody(mobileNumber)
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
        val res =client1.post(BASE_PATH + EMAIL_VERIFICATION) {
            setBody(emailId)
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
        val res =client1.post(BASE_PATH + MPIN_VERIFICATION) {
            setBody(mPin)
            contentType(ContentType.Application.Json)
        }
        assertEquals(HttpStatusCode.OK, res.status)
    }


    @Test
    fun testAdhar() = testApplication {
        application {
            module(configureRouting())
        }
        val client1 = createClient {
            install(ContentNegotiation){
                json()
            }
        }
        val res =client1.post(BASE_PATH + ADHAR_VERIFICATION) {
            setBody(adharNo)
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
        val res =client1.post(BASE_PATH + PAN_VERIFICATION) {
            setBody(panNo)
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
        val res =client1.post(BASE_PATH + SET2FA_VERIFICATION) {
            setBody(id)
            contentType(ContentType.Application.Json)
        }
        assertEquals(HttpStatusCode.OK, res.status)
    }
}



