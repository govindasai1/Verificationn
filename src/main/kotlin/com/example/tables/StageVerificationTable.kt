package com.example.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object StageVerificationTable: IntIdTable("user_Id") {
    val mobileVerification = bool("Mobile_Verification")
    val emailVerification = bool("Email_Verification")
    val setMpin = bool("setMPIN")
    val setAdhar = bool("setADHAR")
    val setPan = bool("setPan")
    val set2FA = bool("set2FA")

}