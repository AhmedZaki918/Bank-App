package com.ahmed.bankapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ahmed.bankapp.data.Currency
import com.ahmed.bankapp.databinding.ListItemCurrencyBinding

class CurrenciesAdapter(
    val data: List<Currency>,
) :
    RecyclerView.Adapter<CurrenciesAdapter.CurrenciesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesViewHolder {
        return CurrenciesViewHolder(
            ListItemCurrencyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class CurrenciesViewHolder(
        val binding: ListItemCurrencyBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentItem: Currency) {
            binding.apply {
                tvCountry.text = currentItem.countryName()
                tvCode.text = currentItem.currencyCode()
                tvCurrency.text = currentItem.currencyName()
                tvExchangeRate.text = currentItem.rate.toString()
            }
        }
    }
}