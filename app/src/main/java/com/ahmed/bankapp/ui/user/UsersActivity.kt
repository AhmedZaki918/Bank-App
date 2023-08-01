package com.ahmed.bankapp.ui.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.R
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
            header.drawScreenHeader(
                "${getString(R.string.users)} (${BankUser.displayUsers().size})",
                this@UsersActivity
            )
            rvUsers.adapter = UsersAdapter(BankUser.displayUsers())
        }
    }
}