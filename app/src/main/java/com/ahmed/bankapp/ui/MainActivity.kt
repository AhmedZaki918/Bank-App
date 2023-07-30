package com.ahmed.bankapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.data.BankUser
import com.ahmed.bankapp.data.Constants.loginData
import com.ahmed.bankapp.data.Permissions
import com.ahmed.bankapp.databinding.ActivityMainBinding
import com.ahmed.bankapp.ui.home.CurrencyExchangeActivity
import com.ahmed.bankapp.ui.home.LoginLogActivity
import com.ahmed.bankapp.ui.home.ManageClientsActivity
import com.ahmed.bankapp.ui.home.ManageUsersActivity
import com.ahmed.bankapp.ui.home.TransactionsActivity
import com.ahmed.bankapp.util.click
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash
import com.ahmed.bankapp.util.openActivity
import com.ahmed.bankapp.util.toast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.header.drawScreenHeader("Home")
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.apply {
            cvManageClients.click {
                openActivity(ManageClientsActivity::class.java)
            }

            cvManageUsers.click {
                val permissionStatus =
                    loginData.checkPermission(Permissions.MANAGE_USERS.value)
                if (permissionStatus) openActivity(ManageUsersActivity::class.java)
                else toast("Access Denied")
            }

            cvTransactions.click {
                val permissionStatus =
                    loginData.checkPermission(Permissions.TRANSACTIONS.value)
                if (permissionStatus) openActivity(TransactionsActivity::class.java)
                else toast("Access Denied")
            }

            cvLoginHistory.click {
                val permissionStatus =
                    loginData.checkPermission(Permissions.REGISTER_LOGIN.value)
                if (permissionStatus) openActivity(LoginLogActivity::class.java)
                else toast("Access Denied")
            }

            cvCurrencyExchange.click {
                openActivity(CurrencyExchangeActivity::class.java)
            }

            cvLogout.click {
                loginData = BankUser.findUser("", "")
                openActivity(LoginActivity::class.java)
                finish()
            }
        }
    }
}