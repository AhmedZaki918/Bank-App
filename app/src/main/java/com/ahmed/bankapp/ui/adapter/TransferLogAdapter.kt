package com.ahmed.bankapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.bankapp.data.log.LogTransfer
import com.ahmed.bankapp.databinding.ListItemTransferLogBinding

class TransferLogAdapter(
    val data: List<LogTransfer>,
) :
    RecyclerView.Adapter<TransferLogAdapter.TransferLogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransferLogViewHolder {
        return TransferLogViewHolder(
            ListItemTransferLogBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: TransferLogViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class TransferLogViewHolder(
        val binding: ListItemTransferLogBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: LogTransfer) {
            binding.apply {
                tvDateTime.text = currentItem.dateAndTime
                tvSourceAcc.text = currentItem.sourceAccount
                tvDestinationAcc.text = currentItem.destinationAccount
                tvAmount.text = currentItem.amount.toString()
                tvSourceBalance.text = currentItem.sourceBalance.toString()
                tvDestinationBalance.text = currentItem.destinationBalance.toString()
                tvUser.text = currentItem.username
            }
        }
    }
}