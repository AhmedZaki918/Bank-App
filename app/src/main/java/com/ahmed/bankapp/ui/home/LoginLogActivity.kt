package com.ahmed.bankapp.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.data.BankUser
import com.ahmed.bankapp.databinding.ActivityLoginLogBinding
import com.ahmed.bankapp.ui.adapter.LoginLogAdapter
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash

class LoginLogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginLogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivityLoginLogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            header.drawScreenHeader(
                "Login Log (${BankUser.displayLoginLogs().size})",
                this@LoginLogActivity
            )
            rvLoginLog.adapter = LoginLogAdapter(BankUser.displayLoginLogs())
        }
    }
}