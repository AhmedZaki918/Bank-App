package com.ahmed.bankapp.ui.client

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.data.BankClient
import com.ahmed.bankapp.databinding.ActivityFindClientBinding
import com.ahmed.bankapp.util.click
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash
import com.ahmed.bankapp.util.toast

class FindClientActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFindClientBinding
    private val views = ArrayList<View>()
    private var accountNumber = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivityFindClientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.header.drawScreenHeader("Find Client",this)

        // Create list of textview
        binding.apply {
            views.apply {
                add(ivFullname)
                add(tvNameLabel)
                add(tvName)
                add(ivPincode)
                add(tvPincodeLabel)
                add(tvPincode)
                add(ivAccNumber)
                add(tvAccNumberLabel)
                add(tvAccNumber)
                add(ivAccBalance)
                add(tvAccBalanceLabel)
                add(tvAccBalance)
            }
        }

        binding.btnSubmit.click {
            accountNumber = binding.etAccountNumber.text.toString().trim()
            val client = BankClient.findClientByAccountNumber(accountNumber)
            displayClient(client)
        }
    }


    private fun displayClient(client: BankClient) {
        if (client.isClientExist()) {
            // Show all views then display the client
            for (view in views) {
                view.visibility = View.VISIBLE
            }
            binding.apply {
                tvAccNumber.text = client.accountNumber
                tvPincode.text = client.pinCode.toString()
                val fullName = client.firstName + " " + client.lastName
                tvName.text = fullName
                tvAccBalance.text = client.accountBalance.toString()
            }
        } else toast("Client with account number Not found!")
    }
}