package com.example.database

import com.example.tables.StageTable
import com.example.tables.StageVerificationTable
import com.example.tables.UserTable
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init(){
        val url = "jdbc:postgresql://localhost:8080/Verification"
        val driver = "org.postgresql.Driver"
        val userName = "postgres"
        val password = "root"
        Database.connect(url, driver,userName,password)

        transaction {
            SchemaUtils.createMissingTablesAndColumns(StageTable,UserTable,StageVerificationTable)
        }
    }
    suspend fun <T> dbQuery(block:()->T):T{
        return newSuspendedTransaction(Dispatchers.IO){
            block()
        }
    }
}