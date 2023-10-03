package com.example.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object StageTable:IntIdTable("order_id") {
    val stage_name = varchar("Stage_Name",50)
}