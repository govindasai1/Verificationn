package com.example.plugins

import com.example.models.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
    install(RequestValidation){
        validate<User>{
            if(it.email.contains("@gmail.com")) ValidationResult.Valid
            else ValidationResult.Invalid("ENTER VALID EMAIL ID")
        }
    }

    routing {
        get("/json/kotlinx-serialization") {
                call.respond(mapOf("hello" to "world"))
            }
    }
}
