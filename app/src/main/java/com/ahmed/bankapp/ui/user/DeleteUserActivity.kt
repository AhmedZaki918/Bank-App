package com.ahmed.bankapp.ui.user

import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.data.BankUser
import com.ahmed.bankapp.databinding.ActivityDeleteUserBinding
import com.ahmed.bankapp.util.click
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash
import com.ahmed.bankapp.util.toast

class DeleteUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteUserBinding
    private val views = ArrayList<View>()
    private lateinit var user: BankUser
    private var username = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivityDeleteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.header.drawScreenHeader("Delete User",this)

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

        user = BankUser("", "", 0, "", "")
        clickListeners()
    }


    private fun clickListeners() {
        binding.apply {
            btnSubmit.click {
                username = etUsername.text.toString().trim()
                user = BankUser.findUser(username)
                displayUser(user)
            }
            btnDelete.click { delete(user) }
        }
    }

    private fun delete(user: BankUser) {
        user.deleteUser()
        toast("User has been deleted")
        finish()
    }

    private fun displayUser(user: BankUser) {
        if (user.isUserExist()) {
            // Show all views then display the client
            for (view in views) {
                view.visibility = VISIBLE
            }
            binding.apply {
                btnSubmit.visibility = INVISIBLE
                tvUsername.text = user.username
                tvPassword.text = user.password
                tvPermissions.text = user.permissions.toString()
                btnDelete.visibility = VISIBLE
            }
        } else toast("User with username Not found!")
    }
}