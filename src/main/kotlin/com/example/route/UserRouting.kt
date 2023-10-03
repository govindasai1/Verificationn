package com.example.route

import com.example.models.*
import com.example.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.userRouting(){
    route("/user"){
        post {
            val requestBody = call.receive<User>()
            UserService.creatingUser(requestBody)
                .apply {
                    call.respondText(this,status = HttpStatusCode.Created)
                }

        }
    }

    route("/verification"){

        post ("/mobileVer"){
//            val id = call.parameters["id"]?:return@post call.respond(Message("ID CANT BE EMPTY"))
            val mobileNum = call.receive<MobileNo>()
            call.respond( status = HttpStatusCode.OK,UserService.mobileVerification(mobileNum.id,mobileNum.number))
        }


        post ("/email"){
            val email = call.receive<Email>()
            call.respond( status = HttpStatusCode.OK,UserService.emailVerification(email.id,email.email))
        }


        post ("/setMPIN"){
            val mpin = call.receive<Mpin>()
            call.respond( status = HttpStatusCode.OK,UserService.setMpin(mpin.id,mpin.mPIN))
        }


        post("/adhar") {
            val adharNo = call.receive<AdharNo>()
            call.respond( status = HttpStatusCode.OK,UserService.addAdhar(adharNo.id,adharNo.adharNo))
        }


        post("/pan") {
            val pan = call.receive<PanNo>()
            call.respond( status = HttpStatusCode.OK,UserService.addPan(pan.id,pan.panNo))
        }


        post("/2FA/{id?}") {
            val id = call.parameters["id"]?:return@post call.respond(status = HttpStatusCode.NotAcceptable,Message("ID CANT BE EMPTY"))
            call.respond(status = HttpStatusCode.OK,UserService.set2Fa(id.toInt()))
        }


    }
}
