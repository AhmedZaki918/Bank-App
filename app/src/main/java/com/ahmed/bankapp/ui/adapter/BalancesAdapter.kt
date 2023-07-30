package com.ahmed.bankapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.bankapp.data.BankClient
import com.ahmed.bankapp.databinding.ListItemBalancesBinding

class BalancesAdapter(
    val data: List<BankClient>,
) :
    RecyclerView.Adapter<BalancesAdapter.BalancesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BalancesViewHolder {
        return BalancesViewHolder(
            ListItemBalancesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BalancesViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class BalancesViewHolder(
        val binding: ListItemBalancesBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: BankClient) {
            binding.apply {
                tvFullname.text = currentItem.firstName + " " + currentItem.lastName
                tvAccNumber.text = currentItem.accountNumber
                tvBalance.text = currentItem.accountBalance.toString()
            }
        }
    }
}