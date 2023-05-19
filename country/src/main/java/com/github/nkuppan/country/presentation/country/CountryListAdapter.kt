package com.github.nkuppan.country.presentation.country

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.nkuppan.country.domain.model.Country
import com.nkuppan.country.databinding.CountryListItemBinding

class CountryListAdapter(
    private val callback: ((Country) -> Unit)? = null
) : ListAdapter<Country, CountryListViewHolder>(
    object : DiffUtil.ItemCallback<Country>() {

        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.countryCode == newItem.countryCode
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.countryStates?.length == newItem.countryStates?.length
                    && oldItem.name == newItem.name
        }
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryListViewHolder {
        val binding = CountryListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return CountryListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryListViewHolder, position: Int) {

        val item = getItem(position)

        holder.binding.viewModel = item

        holder.binding.root.setOnClickListener {
            callback?.invoke(item)
        }
    }
}


class CountryListViewHolder(val binding: CountryListItemBinding) :
    RecyclerView.ViewHolder(binding.root)