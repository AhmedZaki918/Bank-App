package com.ahmed.bankapp.ui.transactions

import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.data.BankClient
import com.ahmed.bankapp.data.Status
import com.ahmed.bankapp.databinding.ActivityWithdrawBinding
import com.ahmed.bankapp.util.click
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash
import com.ahmed.bankapp.util.toast

class WithdrawActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWithdrawBinding
    private val views = ArrayList<View>()
    private var client: BankClient? = null
    private var accountNumber = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivityWithdrawBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.header.drawScreenHeader("Withdraw", this)

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
            btnWithdraw.click {
                etAccountNumber.visibility = INVISIBLE
                val amount = binding.etAccountBalance.text.toString().toDouble()
                val withdrawResult = client!!.withdraw(amount)
                if (withdrawResult == Status.SUCCESS.value) {
                    toast("Withdraw has been completed")
                    finish()
                } else toast("You don't have enough credit!")
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
                tvAccNumber.text = client.accountNumber
                tvPincode.text = client.pinCode.toString()

                val fullName = client.firstName + " " + client.lastName
                tvName.text = fullName
                tvAccBalance.text = client.accountBalance.toString()
                btnWithdraw.visibility = VISIBLE
                etAccountBalance.visibility = VISIBLE
                btnSubmit.visibility = INVISIBLE
            }
        } else toast("Client with account number Not found!")
    }
}