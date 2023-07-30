package com.ahmed.bankapp.ui

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.data.BankUser
import com.ahmed.bankapp.data.Constants.loginData
import com.ahmed.bankapp.databinding.ActivityLoginBinding
import com.ahmed.bankapp.util.addFakeDataToTest
import com.ahmed.bankapp.util.click
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash
import com.ahmed.bankapp.util.openActivity
import com.ahmed.bankapp.util.toast

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var user: BankUser? = null
    private var counter = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            header.drawScreenHeader("Login")
            btnLogin.click { login() }
            btnTest.click {
                addFakeDataToTest()
                toast("Test data has been added")
            }
        }
    }

    private fun login() {
        binding.apply {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()
            user = BankUser.findUser(username, password)
            loginData = user as BankUser

            if (user!!.isUserExist()) {
                user!!.registerLoginLog()
                openActivity(MainActivity::class.java)
                finish()
            } else {
                lockTheSystem()
            }
        }
    }




    // Lock the system after 3 fail attempts
    private fun lockTheSystem() {
        if (counter < 2) {
            toast("Invalid data")
        }
        counter++
        if (counter == 3) {
            Toast.makeText(this, "System locked after 3 failed trails", LENGTH_LONG)
                .show()
            finish()
        }
    }
}