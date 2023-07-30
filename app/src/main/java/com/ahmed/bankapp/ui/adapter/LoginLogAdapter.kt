package com.ahmed.bankapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.bankapp.data.log.LogLogin
import com.ahmed.bankapp.databinding.ListItemLoginLogBinding
import com.ahmed.bankapp.util.decryptText

class LoginLogAdapter(
    val data: List<LogLogin>,
) :
    RecyclerView.Adapter<LoginLogAdapter.LoginLogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoginLogViewHolder {
        return LoginLogViewHolder(
            ListItemLoginLogBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: LoginLogViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class LoginLogViewHolder(
        val binding: ListItemLoginLogBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: LogLogin) {
            binding.apply {
                tvDateTime.text = currentItem.dateAndTime
                tvUsername.text = currentItem.username
                tvPassword.text = decryptText(currentItem.password)
                tvPermissions.text = currentItem.permissions.toString()
            }
        }
    }
}