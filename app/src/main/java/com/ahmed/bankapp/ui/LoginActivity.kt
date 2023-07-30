package com.ahmed.bankapp.ui

import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.data.BankClient
import com.ahmed.bankapp.data.BankUser
import com.ahmed.bankapp.data.Constants.loginData
import com.ahmed.bankapp.data.Currency
import com.ahmed.bankapp.databinding.ActivityLoginBinding
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


    private fun addFakeDataToTest() {
        BankClient.apply {
            addClient("105", 1234, 5000.0, "Ahmed", "Zaki")
            addClient("106", 1233, 3000.0, "Sara", "Zaki")
            addClient("107", 1252, 9000.0, "Adel", "Sabry")
            addClient("108", 4781, 3500.0, "Ehab", "Mohamed")
            addClient("109", 5515, 7500.0, "Waled", "Adham")
            addClient("110", 9844, 1500.0, "Ahmed", "Ali")
        }

        BankUser.apply {
            addUser("admin", "1234", 255, "Admin", "")
            addUser("ahmed", "1234", 3, "Ahmed", "Zaki")
            addUser("mohamed22adel", "1234", 19, "Mohamed", "Adel")
            addUser("ramy51ahmed", "1234", 3, "Ramy", "Ahmed")
        }

        Currency.getCurrencies().apply {
            add(Currency("United States of America", "USD", "US Dollar", 1.000000f))
            add(Currency("Saudi Arabian", "SAR", "Saudi Riyal", 3.75f))
            add(Currency("England", "GBP", "British Pound", 0.77f))
            add(Currency("Egypt", "EGP", "Egyptian Pound", 30.89f))
            add(Currency("Kuwaiti", "KWD", "Kuwaiti Dinar", 0.308f))
            add(Currency("France", "EUR", "Euro", 0.90f))
            add(Currency("United Arab Emirates", "AED", "Emirati Dirhams", 3.67f))
            add(Currency("Chinese", "CNY", "Chinese Yuan Renminbi", 7.14f))
        }
    }
}