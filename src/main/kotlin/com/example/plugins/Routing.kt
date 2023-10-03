package com.example.plugins

import com.example.models.User
import com.example.route.userRouting
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {
        userRouting()
        post("/") {
            val x = call.receive<User>()
            call.respondText(x.toString(), status = HttpStatusCode.Created)
        }

    }
}
