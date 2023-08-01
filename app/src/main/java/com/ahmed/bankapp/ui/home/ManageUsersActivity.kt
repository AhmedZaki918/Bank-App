package com.ahmed.bankapp.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.databinding.ActivityManageUsersBinding
import com.ahmed.bankapp.ui.user.AddUserActivity
import com.ahmed.bankapp.ui.user.DeleteUserActivity
import com.ahmed.bankapp.ui.user.FindUserActivity
import com.ahmed.bankapp.ui.user.UpdateUserActivity
import com.ahmed.bankapp.ui.user.UsersActivity
import com.ahmed.bankapp.util.click
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash
import com.ahmed.bankapp.util.openActivity

class ManageUsersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageUsersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivityManageUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.header.drawScreenHeader("Manage Users",this)

        binding.apply {
            cvAddUser.click { openActivity(AddUserActivity::class.java) }
            cvShowUsers.click { openActivity(UsersActivity::class.java) }
            cvDeleteUser.click { openActivity(DeleteUserActivity::class.java) }
            cvUpdateClient.click { openActivity(UpdateUserActivity::class.java) }
            cvFindUser.click { openActivity(FindUserActivity::class.java) }
        }
    }
}