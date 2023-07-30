package com.ahmed.bankapp.ui.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.data.BankUser
import com.ahmed.bankapp.data.Permissions
import com.ahmed.bankapp.databinding.ActivityAddUserBinding
import com.ahmed.bankapp.util.click
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash
import com.ahmed.bankapp.util.toast

class AddUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddUserBinding
    private var userPermissions = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.header.drawScreenHeader("Add User")
        readPermissions()
        binding.btnAddClient.click {
            addUser()
            //clearScreen()
        }
    }

    private fun addUser() {
        binding.apply {
            BankUser.addUser(
                etUsername.text.toString().trim(),
                etPassword.text.toString().trim(),
                userPermissions,
                etUserFirstName.text.toString().trim(),
                etUserLastName.text.toString().trim()
            )
            toast("User has been added")
            finish()
        }
    }


    private fun readPermissions() {
        binding.apply {
            switchShow.click {
                if (switchShow.isChecked) userPermissions += Permissions.GET_CLIENTS.value
                else userPermissions -= Permissions.GET_CLIENTS.value
            }

            switchAdd.click {
                if (switchAdd.isChecked) userPermissions += Permissions.ADD_CLIENT.value
                else userPermissions -= Permissions.ADD_CLIENT.value
            }

            switchDelete.click {
                if (switchDelete.isChecked) userPermissions += Permissions.DELETE_CLIENT.value
                else userPermissions -= Permissions.DELETE_CLIENT.value
            }

            switchUpdate.click {
                if (switchUpdate.isChecked) userPermissions += Permissions.UPDATE_CLIENT.value
                else userPermissions -= Permissions.UPDATE_CLIENT.value
            }

            switchSearch.click {
                if (switchSearch.isChecked) userPermissions += Permissions.FIND_CLIENT.value
                else userPermissions -= Permissions.FIND_CLIENT.value
            }

            switchTransactions.click {
                if (switchTransactions.isChecked) userPermissions += Permissions.TRANSACTIONS.value
                else userPermissions -= Permissions.TRANSACTIONS.value
            }

            switchManageAccounts.click {
                if (switchManageAccounts.isChecked) userPermissions += Permissions.MANAGE_USERS.value
                else userPermissions -= Permissions.MANAGE_USERS.value
            }

            switchLoginLog.click {
                if (switchLoginLog.isChecked) userPermissions += Permissions.REGISTER_LOGIN.value
                else userPermissions -= Permissions.REGISTER_LOGIN.value
            }
        }
    }
}