package com.ahmed.bankapp.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.R
import com.ahmed.bankapp.data.Constants.loginData
import com.ahmed.bankapp.data.Permissions
import com.ahmed.bankapp.databinding.ActivityManageClientsBinding
import com.ahmed.bankapp.ui.client.AddClientActivity
import com.ahmed.bankapp.ui.client.ClientsActivity
import com.ahmed.bankapp.ui.client.DeleteClientActivity
import com.ahmed.bankapp.ui.client.FindClientActivity
import com.ahmed.bankapp.ui.client.UpdateClientActivity
import com.ahmed.bankapp.util.click
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash
import com.ahmed.bankapp.util.openActivity
import com.ahmed.bankapp.util.toast

class ManageClientsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageClientsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivityManageClientsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            header.drawScreenHeader(getString(R.string.manage_clients), this@ManageClientsActivity)

            cvAddClient.click {
                val permissionStatus =
                    loginData.checkPermission(Permissions.ADD_CLIENT.value)
                if (permissionStatus) openActivity(AddClientActivity::class.java)
                else toast(getString(R.string.access_denied))
            }

            cvShowClients.click {
                val permissionStatus =
                    loginData.checkPermission(Permissions.GET_CLIENTS.value)
                if (permissionStatus) openActivity(ClientsActivity::class.java)
                else toast(getString(R.string.access_denied))
            }

            cvDeleteClient.click {
                val permissionStatus =
                    loginData.checkPermission(Permissions.DELETE_CLIENT.value)
                if (permissionStatus) openActivity(DeleteClientActivity::class.java)
                else toast(getString(R.string.access_denied))
            }


            cvUpdateClient.click {
                val permissionStatus =
                    loginData.checkPermission(Permissions.UPDATE_CLIENT.value)
                if (permissionStatus) openActivity(UpdateClientActivity::class.java)
                else toast(getString(R.string.access_denied))
            }

            cvFindClient.click {
                val permissionStatus =
                    loginData.checkPermission(Permissions.FIND_CLIENT.value)
                if (permissionStatus) openActivity(FindClientActivity::class.java)
                else toast(getString(R.string.access_denied))
            }
        }
    }
}