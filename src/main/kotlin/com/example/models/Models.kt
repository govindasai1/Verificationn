package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class User(val name:String,val email:String)

@Serializable
data class Id(val id:Int)

@Serializable
data class Message(val message:String)

@Serializable
data class Email(val id:Int,val email: String)

@Serializable
data class Mpin(val id:Int,val mPIN: String)

@Serializable
data class AdharNo(val id:Int,val adharNo:String)

@Serializable
data class PanNo(val id:Int,val panNo:String)

@Serializable
data class MobileNo(val id:Int,val number:Long)

