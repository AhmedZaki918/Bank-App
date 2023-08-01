package com.ahmed.bankapp.ui.client

import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.R
import com.ahmed.bankapp.data.BankClient
import com.ahmed.bankapp.databinding.ActivityDeleteClientBinding
import com.ahmed.bankapp.util.click
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash
import com.ahmed.bankapp.util.toast

class DeleteClientActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteClientBinding
    private val views = ArrayList<View>()
    private lateinit var client: BankClient
    private var accountNumber = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivityDeleteClientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.header.drawScreenHeader(getString(R.string.delete_client),this)

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
        client = BankClient("", 0, 0.00, "", "")
        clickListeners()
    }


    private fun clickListeners() {
        binding.apply {
            btnSubmit.click {
                accountNumber = etAccountNumber.text.toString().trim()
                client = BankClient.findClientByAccountNumber(accountNumber)
                displayClient(client)
            }
            btnDelete.click { delete(client) }
        }
    }


    private fun delete(client: BankClient) {
        client.deleteClient()
        toast(getString(R.string.client_deleted))
        finish()
    }


    private fun displayClient(client: BankClient) {
        if (client.isClientExist()) {
            // Show all views then display the client
            for (view in views) {
                view.visibility = VISIBLE
            }
            binding.apply {
                btnSubmit.visibility = INVISIBLE
                tvAccNumber.text = client.accountNumber
                tvPincode.text = client.pinCode.toString()

                val fullName = client.firstName + " " + client.lastName
                tvName.text = fullName
                tvAccBalance.text = client.accountBalance.toString()
                btnDelete.visibility = VISIBLE
            }
        } else toast(getString(R.string.client_not_found))
    }
}