package com.example.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object UserTable:IntIdTable() {
    val name = varchar("UserName",50)
    val email = varchar("Email",50)
    val current_state = varchar("Current_State",50)
    val next_stage = varchar("Next_Stage",50)
    val is_verified = bool("Is_Verified")
}