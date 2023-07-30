package com.ahmed.bankapp.ui.user

import android.os.Bundle
import android.view.View
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.data.BankUser
import com.ahmed.bankapp.databinding.ActivityFindUserBinding
import com.ahmed.bankapp.util.click
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash
import com.ahmed.bankapp.util.toast

class FindUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFindUserBinding
    private val views = ArrayList<View>()
    private var username = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivityFindUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.header.drawScreenHeader("Find User")

        // Create list of textview
        binding.apply {
            views.apply {
                add(ivUsername)
                add(tvUsernameLabel)
                add(tvUsername)
                add(ivPassword)
                add(tvPasswordLabel)
                add(tvPassword)
                add(ivPermissions)
                add(tvPermissionsLabel)
                add(tvPermissions)
            }
        }

        binding.btnSubmit.click {
            username = binding.etUserName.text.toString().trim()
            val user = BankUser.findUser(username)
            displayUser(user)
        }
    }

    private fun displayUser(user: BankUser) {
        if (user.isUserExist()) {
            for (view in views) {
                view.visibility = VISIBLE
            }
            binding.apply {
                tvUsername.text = user.username
                tvPassword.text = user.password
                tvPermissions.text = user.permissions.toString()
            }
        } else toast("User with username Not found!")
    }
}