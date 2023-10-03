package com.example.service

import com.example.dao.UserDao
import com.example.database.DatabaseFactory
import com.example.methods.*
import com.example.models.Message
import com.example.models.User
import com.example.tables.StageTable
import com.example.tables.StageVerificationTable
import com.example.tables.UserTable
import com.example.timeCalculator.time
import com.example.timeCalculator.timeTaken
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update
var time = ""
var time1 =""
object UserService:UserDao {
    override suspend fun creatingUser(user: User): String {
        DatabaseFactory.dbQuery {
            UserTable.insert {
                it[name] = user.name
                it[email] = user.email
                it[current_state] = "1"
                it[next_stage] = "2"
                it[is_verified] = false
            }.resultedValues?.singleOrNull()
        }
        DatabaseFactory.dbQuery {
            StageTable.insert {
                it[stage_name] = "mobile_Verification"
            }.resultedValues?.singleOrNull()
        }
        DatabaseFactory.dbQuery {
            StageVerificationTable.insert {
                it[mobileVerification] = false
                it[emailVerification] = false
                it[setMpin] = false
                it[setAdhar] = false
                it[setPan] = false
                it[set2FA] = false
            }
        }

        return "USER CREATED SUCCESSFULLY"
    }

    override suspend fun mobileVerification(id: Int, mobileNumber: Long): Message {
        time1 = time()
        return if(checkingPresence(id)) {
            if (mobileNumber in 6000000001..99999999998) {
                updatingTables(id, "2", "3", "Email_Verification")
                DatabaseFactory.dbQuery {
                    StageVerificationTable.update({ StageVerificationTable.id.eq(id) }) {
                        it[mobileVerification] = true
                    }
                }

                Message("mobile_Verified")
            } else {
                updatingTables(id, "1", "2", "setMPIN")
                updatingState(id)
                Message("not verified")
            }
        } else Message("USER ID NOT FOUND")
    }

    override suspend fun emailVerification(id: Int, email: String): Message {
        time = time()
        val diffTime = timeTaken(time1, time)
        return if(checkingPresence(id)) {
            val dataBaseEmail = DatabaseFactory.dbQuery {
                UserTable.slice(UserTable.email).select(UserTable.id.eq(id)).map { it[UserTable.email] }.singleOrNull()
            }
            if (dataBaseEmail.equals(email) && email.contains("@gmail.com")  && diffTime< 0.15) {
                updatingTables(id, "2", "3", "setMPIN")
                DatabaseFactory.dbQuery {
                    StageVerificationTable.update({ StageVerificationTable.id.eq(id) }) {
                        it[emailVerification] = true
                    }
                }
                Message("email verified")
            } else {
                updatingTables(id, "1", "2", "setMPIN")
                updatingState(id)
                Message("email not verified")
            }
        }else Message("USER ID NOT FOUND")

    }

    override suspend fun setMpin(id: Int, mpin: String): Message {
        time1 = time()
        val diffTime = timeTaken(time, time1)
        return if(checkingPresence(id) && diffTime<0.15 ) {
            if (intable(mpin) && mpin.length==6) {
                updatingTables(id, "3", "4", "addAdhar")
                DatabaseFactory.dbQuery {
                    StageVerificationTable.update({ StageVerificationTable.id.eq(id) }) {
                        it[setMpin] = true
                    }
                }
                Message("mpin setted")
            } else {
                updatingTables(id, "1", "2", "setMPIN")
                updatingState(id)
                Message("mpin not set")
            }
        }else Message("USER ID NOT FOUND")

    }

    override suspend fun addAdhar(id:Int,adharNo:String): Message {
        time = time()
        val diffTime = timeTaken(time1, time)
        return if(checkingPresence(id) && diffTime<0.15) {
        if(longAble(adharNo) && adharNo.length==12){
            updatingTables(id,"4","5","add adhar")
            DatabaseFactory.dbQuery {
                StageVerificationTable.update({ StageVerificationTable.id.eq(id) }) {
                    it[setAdhar] = true
                }
            }
            Message("Adhar updated")
        } else{
            updatingTables(id,"1","2","setMPIN")
            updatingState(id)
            Message("adhar not added")
        }
        }else Message("USER ID NOT FOUND")
    }

    override suspend fun addPan(id: Int, panNo: String): Message {
        time1 = time()
        val diffTime = timeTaken(time, time1)
        return if(checkingPresence(id) && diffTime<0.15) {
        return if(panNo.length == 10 && panVerification(panNo)){
            updatingTables(id,"5","6","set2Fa")
            DatabaseFactory.dbQuery {
                StageVerificationTable.update({ StageVerificationTable.id.eq(id) }) {
                    it[setPan] = true
                }
            }
            Message("Pan updated")
        } else{
            updatingTables(id,"1","2","setMPIN")
            updatingState(id)
            Message("pan not added")
        }
    }else Message("USER ID NOT FOUND")
    }

    override suspend fun set2Fa(id: Int): Message {
        time = time()
        val diffTime = timeTaken(time1, time)
        return if(checkingPresence(id) && diffTime<0.15) {
        updateTable(id,"6","none")
        DatabaseFactory.dbQuery {
            StageVerificationTable.update({ StageVerificationTable.id.eq(id) }) {
                it[set2FA] = true
            }
        }
         Message("2FA setted User verified successfully")
        }else Message("USER ID NOT FOUND")
    }


}



