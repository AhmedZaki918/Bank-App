package com.ahmed.bankapp.data

import com.ahmed.bankapp.data.log.LogLogin
import com.ahmed.bankapp.util.decryptText
import com.ahmed.bankapp.util.encryptText
import java.text.SimpleDateFormat
import java.util.Date

data class BankUser(
    var username: String,
    var password: String,
    var permissions: Int,
    override var firstName: String,
    override var lastName: String
) : Person(firstName, lastName) {


    companion object {

        private var users = ArrayList<BankUser>()
        private var loginLog = ArrayList<LogLogin>()
        private fun getEmptyUser(): BankUser {
            return BankUser("", "", 0, "", "")
        }

        fun addUser(
            username: String,
            password: String,
            permission: Int,
            firstName: String,
            lastName: String
        ) {
            users.add(
                BankUser(
                    username,
                    encryptText(password),
                    permission,
                    firstName,
                    lastName
                )
            )
        }


        fun findUser(
            username: String
        ): BankUser {
            for (i in users.indices) {
                if (username == users[i].username) {
                    users[i].apply {
                        return BankUser(
                            username,
                            decryptText(password),
                            permissions,
                            firstName,
                            lastName
                        )
                    }
                }
            }
            // No user has been found
            return getEmptyUser()
        }


        fun findUser(
            username: String,
            password: String
        ): BankUser {
            for (i in users.indices) {
                if (username == users[i].username && password == decryptText(users[i].password)) {
                    users[i].apply {
                        return BankUser(
                            username,
                            password,
                            permissions,
                            firstName,
                            lastName
                        )
                    }
                }
            }
            // No user has been found
            return getEmptyUser()
        }


        fun updateUser(
            oldUsername: String,
            oldPassword: String,
            username: String,
            password: String,
            permission: Int,
            firstName: String
        ): BankUser {

            for (i in users.indices) {
                if (oldUsername == users[i].username && oldPassword == decryptText(users[i].password)) {
                    users[i].apply {
                        val item = BankUser(
                            username,
                            encryptText(password),
                            permission,
                            firstName,
                            lastName
                        )
                        users.set(i, item)
                        return item
                    }
                }
            }
            return getEmptyUser()
        }

        fun displayUsers(): ArrayList<BankUser> {
            return users
        }

        fun displayLoginLogs(): ArrayList<LogLogin> {
            return loginLog
        }
    }


    fun isUserExist(): Boolean {
        return username != ""
    }

    fun deleteUser() {
        users.remove(
            BankUser(
                username,
                password,
                permissions,
                firstName,
                lastName
            )
        )
    }

    fun checkPermission(currentPermission: Int): Boolean {
        return (currentPermission and permissions) == currentPermission
    }

    fun registerLoginLog() {
        val sdf = SimpleDateFormat("dd/MM/yyyy - HH:mm:ss")
        loginLog.add(
            LogLogin(
                sdf.format(Date()),
                username,
                encryptText(password),
                permissions
            )
        )
    }
}