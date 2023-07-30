package com.ahmed.bankapp.ui.transactions

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ahmed.bankapp.data.BankClient
import com.ahmed.bankapp.databinding.ActivityBalancesBinding
import com.ahmed.bankapp.ui.adapter.BalancesAdapter
import com.ahmed.bankapp.util.drawScreenHeader
import com.ahmed.bankapp.util.installSplash

class BalancesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBalancesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplash()
        binding = ActivityBalancesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            header.drawScreenHeader("Balances")
            rvBalances.adapter = BalancesAdapter(BankClient.displayClients())
            val balances = "${BankClient.getTotalBalances()} EGP"
            tvTotalBalances.text = balances
        }
    }
}