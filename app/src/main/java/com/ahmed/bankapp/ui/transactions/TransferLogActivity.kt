package com.ahmed.bankapp.ui.transactions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.R
import com.ahmed.bankapp.data.BankClient
import com.ahmed.bankapp.databinding.ActivityTransferLogBinding
import com.ahmed.bankapp.ui.adapter.TransferLogAdapter
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash

class TransferLogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransferLogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivityTransferLogBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            header.drawScreenHeader(
                "${getString(R.string.transfer_log)} (${BankClient.displayTransferLogs().size})",
                this@TransferLogActivity
            )
            rvTransferLog.adapter = TransferLogAdapter(BankClient.displayTransferLogs())
        }
    }
}