package com.example.methods

import com.example.database.DatabaseFactory
import com.example.tables.StageTable
import com.example.tables.StageVerificationTable
import com.example.tables.UserTable
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update


suspend fun checkingPresence(id:Int):Boolean{
    val user = DatabaseFactory.dbQuery { UserTable.slice(UserTable.name).select(UserTable.id.eq(id)) .map { it[UserTable.name] }.singleOrNull()}
    return user!=null
}
suspend fun updatingTables(id:Int,current_stage:String,next_state:String,state:String){
    DatabaseFactory.dbQuery {
        UserTable.update({ UserTable.id.eq(id) }) {
            it[current_state] = current_stage
            it[next_stage] = next_state
        }
    }
    DatabaseFactory.dbQuery {
        StageTable.insert {
            it[stage_name] = state
        }
    }
}
suspend fun updateTable(id: Int,current_stage: String,next_state: String){
    DatabaseFactory.dbQuery {
        UserTable.update({ UserTable.id.eq(id) }) {
            it[current_state] = current_stage
            it[next_stage] = next_state
            it[is_verified] = true
        }
    }
}

suspend fun updatingState(id: Int) {
    DatabaseFactory.dbQuery { StageVerificationTable.update ({ StageVerificationTable.id.eq(id) }){
        it[mobileVerification] = false
        it[emailVerification] = false
        it[setMpin] = false
        it[setAdhar] = false
        it[setPan] = false
        it[set2FA] = false
    } }
}

fun intable(mpin:String):Boolean{
    return try {
        mpin.toInt()
        true
    }
    catch (_:NumberFormatException){
        false
    }
}
fun longAble(adharNo: String):Boolean{
    return try {
        adharNo.toLong()
        true
    }
    catch (_:NumberFormatException){
        false
    }
}
fun panVerification(panNo:String):Boolean{
    val regex = Regex("(([A-Za-z]{5})([0-9]{4})([a-zA-Z]))")
    return panNo.contains(regex)
}
