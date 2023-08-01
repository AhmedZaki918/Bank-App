package com.ahmed.bankapp.ui.transactions

import android.os.Bundle
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.R
import com.ahmed.bankapp.data.BankClient
import com.ahmed.bankapp.data.Status
import com.ahmed.bankapp.databinding.ActivityTransferBinding
import com.ahmed.bankapp.util.click
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash
import com.ahmed.bankapp.util.toast

class TransferActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransferBinding
    private var fromClient: BankClient? = null
    private var toClient: BankClient? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            header.drawScreenHeader(getString(R.string.transfer), this@TransferActivity)
            btnSubmit.click { getUserInput() }
            btnTransfer.click { transfer() }
        }
    }


    private fun getUserInput() {
        binding.apply {
            val fromAccount = etFromAccount.text.toString().trim()
            val toAccount = etToAccount.text.toString().trim()
            fromClient = BankClient.findClientByAccountNumber(fromAccount)
            toClient = BankClient.findClientByAccountNumber(toAccount)

            if (fromClient!!.isClientExist() && toClient!!.isClientExist()) {
                showScreen()
                displayClientsData(fromClient!!, toClient!!)
            } else toast(getString(R.string.client_not_found))
        }
    }


    private fun displayClientsData(fromClient: BankClient, toClient: BankClient) {
        binding.apply {
            val fromFullName = fromClient.firstName + " " + fromClient.lastName
            tvFullname.text = fromFullName
            tvAccNumber.text = fromClient.accountNumber
            tvBalance.text = fromClient.accountBalance.toString()

            val toFullName = toClient.firstName + " " + toClient.lastName
            toTvFullname.text = toFullName
            toTvAccNumber.text = toClient.accountNumber
            toTvBalance.text = toClient.accountBalance.toString()
        }
    }


    private fun transfer() {
        val amount = binding.etTransferAmount.text.toString().trim()
        val transferResult = fromClient!!.transferMoney(
            amount.toDouble(),
            toClient!!
        )
        if (transferResult == Status.SUCCESS.value) {
            toast(getString(R.string.transfer_completed))
            finish()
        } else toast(getString(R.string.transfer_failed))
    }


    private fun showScreen() {
        binding.apply {
            fullnameLabel.visibility = VISIBLE
            tvFullname.visibility = VISIBLE
            accNumberLabel.visibility = VISIBLE
            tvAccNumber.visibility = VISIBLE
            balanceLabel.visibility = VISIBLE
            tvBalance.visibility = VISIBLE
            tvFrom.visibility = VISIBLE
            view.visibility = VISIBLE

            toFullnameLabel.visibility = VISIBLE
            toTvFullname.visibility = VISIBLE
            toAccNumberLabel.visibility = VISIBLE
            toTvAccNumber.visibility = VISIBLE
            toBalanceLabel.visibility = VISIBLE
            toTvBalance.visibility = VISIBLE
            tvTo.visibility = VISIBLE
            etTransferAmount.visibility = VISIBLE
            btnTransfer.visibility = VISIBLE

            imageView18.visibility = VISIBLE
            imageView19.visibility = VISIBLE
            imageView20.visibility = VISIBLE
            imageView21.visibility = VISIBLE
            imageView22.visibility = VISIBLE
            imageView23.visibility = VISIBLE
        }
    }
}