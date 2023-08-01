package com.ahmed.bankapp.ui.client

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.data.BankClient
import com.ahmed.bankapp.databinding.ActivityClientsBinding
import com.ahmed.bankapp.ui.adapter.ClientsAdapter
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash

class ClientsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityClientsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivityClientsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            header.drawScreenHeader(
                "Clients (${BankClient.displayClients().size})",
                this@ClientsActivity
            )
            rvClients.adapter = ClientsAdapter(BankClient.displayClients())
        }
    }
}