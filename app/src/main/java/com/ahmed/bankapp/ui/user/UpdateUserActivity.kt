package com.ahmed.bankapp.ui.user

import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.data.BankUser
import com.ahmed.bankapp.databinding.ActivityUpdateUserBinding
import com.ahmed.bankapp.util.changeToDefaultColor
import com.ahmed.bankapp.util.click
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash
import com.ahmed.bankapp.util.toast

class UpdateUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateUserBinding
    private var username = ""
    private var password = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivityUpdateUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.header.drawScreenHeader("Update User",this)
        clickListeners()
    }

    private fun clickListeners() {
        binding.apply {
            btnSubmit.click {
                username = etUsername.text.toString().trim()
                password = etPassword.text.toString().trim()
                val user = BankUser.findUser(username, password)
                displayUser(user)
            }
            btnUpdate.click { updateUser(username, password) }
        }
    }


    private fun updateUser(oldUsername: String, oldPassword: String) {
        binding.apply {
            toast(
                BankUser.updateUser(
                    oldUsername,
                    oldPassword,
                    etUpdateUsername.text.toString().trim(),
                    etUpdatePassword.text.toString().trim(),
                    etPermissions.text.toString().toInt(),
                    etFirstName.text.toString().trim()
                )
            )
            finish()
        }
    }

    private fun displayUser(user: BankUser) {
        if (user.isUserExist()) {
            // Show all views then display the client
            switchVisibility()
            binding.apply {
                etUpdateUsername.setText(user.username)
                etUpdatePassword.setText(user.password)
                etPermissions.setText(user.permissions.toString())
                etFirstName.setText(user.firstName)

                etUpdateUsername.changeToDefaultColor(applicationContext)
                etUpdatePassword.changeToDefaultColor(applicationContext)
                etPermissions.changeToDefaultColor(applicationContext)
                etFirstName.changeToDefaultColor(applicationContext)
            }
        } else toast("Username Not found!")
    }


    private fun switchVisibility() {
        binding.apply {
            etUsername.visibility = INVISIBLE
            etPassword.visibility = INVISIBLE
            btnSubmit.visibility = INVISIBLE

            etUpdateUsername.visibility = VISIBLE
            etUpdatePassword.visibility = VISIBLE
            etPermissions.visibility = VISIBLE
            etFirstName.visibility = VISIBLE
            btnUpdate.visibility = VISIBLE
        }
    }
}