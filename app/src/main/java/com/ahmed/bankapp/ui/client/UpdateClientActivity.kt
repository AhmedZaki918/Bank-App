package com.ahmed.bankapp.ui.client

import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.data.BankClient
import com.ahmed.bankapp.databinding.ActivityUpdateClientBinding
import com.ahmed.bankapp.util.changeToDefaultColor
import com.ahmed.bankapp.util.click
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash
import com.ahmed.bankapp.util.toast

class UpdateClientActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateClientBinding
    private var accountNumber = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivityUpdateClientBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickListeners()
        binding.header.drawScreenHeader("Update Client",this)
    }


    private fun clickListeners() {
        binding.apply {
            btnSubmit.click {
                accountNumber = binding.etAccountNumber.text.toString().trim()
                val client = BankClient.findClientByAccountNumber(accountNumber)
                displayClient(client)
            }
            btnUpdate.click { updateClientByAccountNumber(accountNumber) }
        }
    }


    private fun displayClient(client: BankClient) {
        if (client.isClientExist()) {
            // Show all views then display the client
            switchVisibility()
            binding.apply {
                etPinCode.setText(client.pinCode.toString())
                etFirstName.setText(client.firstName)
                etLastName.setText(client.lastName)
                etAccountBalance.setText(client.accountBalance.toString())

                etPinCode.changeToDefaultColor(applicationContext)
                etFirstName.changeToDefaultColor(applicationContext)
                etLastName.changeToDefaultColor(applicationContext)
                etAccountBalance.changeToDefaultColor(applicationContext)
            }
        } else toast("Client with account number Not found!")
    }


    private fun updateClientByAccountNumber(accountNumber: String) {
        binding.apply {
            toast(
                BankClient.updateClient(
                    accountNumber,
                    etPinCode.text.toString().trim(),
                    etFirstName.text.toString().trim(),
                    etLastName.text.toString().trim(),
                    etAccountBalance.text.toString().trim()
                )
            )
            finish()
        }
    }


    private fun switchVisibility() {
        binding.apply {
            etAccountNumber.visibility = INVISIBLE
            btnSubmit.visibility = INVISIBLE
            etPinCode.visibility = VISIBLE
            etFirstName.visibility = VISIBLE
            etLastName.visibility = VISIBLE
            etAccountBalance.visibility = VISIBLE
            btnUpdate.visibility = VISIBLE
        }
    }
}