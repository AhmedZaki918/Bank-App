package com.ahmed.bankapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.bankapp.data.BankClient
import com.ahmed.bankapp.databinding.ListItemClientsBinding

class ClientsAdapter(
    val data: List<BankClient>,
) :
    RecyclerView.Adapter<ClientsAdapter.ClientsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientsViewHolder {
        return ClientsViewHolder(
            ListItemClientsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ClientsViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class ClientsViewHolder(
        val binding: ListItemClientsBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: BankClient) {
            binding.apply {
                tvAccNumber.text = currentItem.accountNumber
                tvPincode.text = currentItem.pinCode.toString()
                tvFullname.text = currentItem.firstName + " " + currentItem.lastName
                tvAccBalance.text = currentItem.accountBalance.toString()
            }
        }
    }
}