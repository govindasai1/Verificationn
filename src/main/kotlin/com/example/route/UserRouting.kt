package com.example.route

import com.example.endPoints.*
import com.example.models.*
import com.example.reposotories.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.userRouting(){
    route(USER_PATH){
        post {
            val requestBody = call.receive<User>()
            val responce = UserService.creatingUser(requestBody)
            call.respond(HttpStatusCode.Created,responce)
        }
    }

    route(BASE_PATH){

        post (MOBILE_VERIFICATION){

            val mobileNum = call.receive<MobileNo>()
            call.respond( status = HttpStatusCode.OK,UserService.mobileVerification(mobileNum.id,mobileNum.number))
        }


        post (EMAIL_VERIFICATION){
            val email = call.receive<Email>()
            call.respond( status = HttpStatusCode.OK,UserService.emailVerification(email.id,email.email))
        }


        post (MPIN_VERIFICATION){
            val mpin = call.receive<Mpin>()
            call.respond( status = HttpStatusCode.OK,UserService.setMpin(mpin.id,mpin.mPIN))
        }


        post(ADHAR_VERIFICATION) {
            val adharNo = call.receive<AdharNo>()
            call.respond( status = HttpStatusCode.OK,UserService.addAdhar(adharNo.id,adharNo.adharNo))
        }


        post(PAN_VERIFICATION) {
            val pan = call.receive<PanNo>()
            call.respond( status = HttpStatusCode.OK,UserService.addPan(pan.id,pan.panNo))
        }


        post(SET2FA_VERIFICATION) {
            val id = call.receive<Id>()
            call.respond(status = HttpStatusCode.OK,UserService.set2Fa(id.id))
        }


    }
}
