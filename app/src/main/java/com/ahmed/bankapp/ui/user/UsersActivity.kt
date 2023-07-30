package com.ahmed.bankapp.ui.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.data.BankUser
import com.ahmed.bankapp.databinding.ActivityUsersBinding
import com.ahmed.bankapp.ui.adapter.UsersAdapter
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash

class UsersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            header.drawScreenHeader("Users (${BankUser.displayUsers().size})")
            rvUsers.adapter = UsersAdapter(BankUser.displayUsers())
        }
    }
}