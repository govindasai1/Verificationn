package com.example.reposotories

import com.example.database.TestDatabaseFactory
import com.example.models.Message
import com.example.models.User
import com.example.tables.StageTable
import com.example.tables.StageVerificationTable
import com.example.tables.UserTable
import io.ktor.server.testing.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.sql.Connection
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class UserServiceTest {
    private lateinit var database :Database
    private val user = User("govindasai","govindasai101@gmail.com")
    private val id = 1
    private val mobileNumber = 6309883352
    private val mpin = "268315"
    private val adharNo = "147852306985"
    private val panNo = "psaqs4574r"
    @Before
    fun start(){
        database = TestDatabaseFactory.init()
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_REPEATABLE_READ
        transaction(database){
            SchemaUtils.create(StageTable,StageVerificationTable,UserTable)
        }
    }
    @After
    fun end(){
        transaction(database){
            SchemaUtils.drop(StageTable,StageVerificationTable,UserTable)
        }
    }

    @Test
    fun creatingUserTest() = testApplication {
        val result = UserService.creatingUser(user)
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun mobileVerificationTest() = testApplication {
        val result = UserService.mobileVerification(id,mobileNumber)
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun emailVerificationTest() = testApplication {
        val result = UserService.emailVerification(id,user.email)
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun setMpinTest() = testApplication {
        val result = UserService.setMpin(id,mpin)
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun addAdharTest() = testApplication {
        val result = UserService.addAdhar(id,adharNo)
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun addPanTest() = testApplication {
        val result = UserService.addPan(id,panNo)
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }

    @Test
    fun set2FaTest() = testApplication {
        val result = UserService.set2Fa(id)
        if (result.equals(Message)) assertTrue(true)
        else assertFalse(false)
    }

}