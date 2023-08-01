package com.ahmed.bankapp.ui.transactions

import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.R
import com.ahmed.bankapp.data.BankClient
import com.ahmed.bankapp.databinding.ActivityDepositBinding
import com.ahmed.bankapp.util.click
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash
import com.ahmed.bankapp.util.toast

class DepositActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDepositBinding
    private val views = ArrayList<View>()
    private var accountNumber = ""
    private var client: BankClient? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()

        binding = ActivityDepositBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.header.drawScreenHeader(getString(R.string.deposit), this)

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
        clickListeners()
    }


    private fun clickListeners() {
        binding.apply {
            btnSubmit.click {
                accountNumber = binding.etAccountNumber.text.toString().trim()
                client = BankClient.findClientByAccountNumber(accountNumber)
                displayClient(client!!)
            }
            btnDeposit.click {
                val amount = binding.etAccountBalance.text.toString().trim()
                client!!.deposit(amount)
                toast(getString(R.string.deposit_accepted))
                finish()
            }
        }
    }


    private fun displayClient(client: BankClient) {
        if (client.isClientExist()) {
            // Show all views then display the client
            for (view in views) {
                view.visibility = VISIBLE
            }
            binding.apply {
                etAccountNumber.visibility = INVISIBLE
                tvAccNumber.text = client.accountNumber
                tvPincode.text = client.pinCode.toString()

                val fullName = client.firstName + " " + client.lastName
                tvName.text = fullName
                tvAccBalance.text = client.accountBalance.toString()
                btnDeposit.visibility = VISIBLE
                etAccountBalance.visibility = VISIBLE
                btnSubmit.visibility = INVISIBLE
            }
        } else toast(getString(R.string.client_not_found))
    }
}