package com.example.dao

import com.example.models.Message
import com.example.models.User

interface UserDao {
    suspend fun creatingUser(user:User):String
    suspend fun mobileVerification(id:Int,mobileNumber:Long):Message
    suspend fun emailVerification(id:Int,email:String):Message
    suspend fun setMpin(id:Int,mpin:String):Message
    suspend fun addAdhar(id:Int,adharNo:String):Message
    suspend fun addPan(id:Int,panNo:String):Message
    suspend fun set2Fa(id: Int):Message

}