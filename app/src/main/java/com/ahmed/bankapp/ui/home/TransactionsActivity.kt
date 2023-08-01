package com.ahmed.bankapp.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.databinding.ActivityTransactionsBinding
import com.ahmed.bankapp.ui.transactions.BalancesActivity
import com.ahmed.bankapp.ui.transactions.DepositActivity
import com.ahmed.bankapp.ui.transactions.TransferActivity
import com.ahmed.bankapp.ui.transactions.TransferLogActivity
import com.ahmed.bankapp.ui.transactions.WithdrawActivity
import com.ahmed.bankapp.util.click
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash
import com.ahmed.bankapp.util.openActivity

class TransactionsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()

        binding = ActivityTransactionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            header.drawScreenHeader(
                "Transactions",
                this@TransactionsActivity
            )
            cvDeposit.click { openActivity(DepositActivity::class.java) }
            cvWithdraw.click { openActivity(WithdrawActivity::class.java) }
            cvTotalBalances.click { openActivity(BalancesActivity::class.java) }
            cvTransfer.click { openActivity(TransferActivity::class.java) }
            cvTransferLog.click { openActivity(TransferLogActivity::class.java) }
        }
    }
}