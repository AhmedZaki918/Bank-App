package com.ahmed.bankapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.bankapp.data.BankUser
import com.ahmed.bankapp.databinding.ListItemUsersBinding
import com.ahmed.bankapp.util.decryptText

class UsersAdapter(
    val data: List<BankUser>,
) :
    RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(
            ListItemUsersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class UsersViewHolder(
        val binding: ListItemUsersBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: BankUser) {
            binding.apply {
                tvPassword.text = decryptText(currentItem.password)
                tvUsername.text = currentItem.username
                tvPermissions.text = currentItem.permissions.toString()
                val fullName = currentItem.firstName + " " + currentItem.lastName
                tvFullname.text = fullName
            }
        }
    }
}