package com.nkuppan.country.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nkuppan.country.databinding.CountryListItemBinding
import com.nkuppan.country.model.CountryModel

/**
 * Created by ancientinc on 2019-07-06.
 **/


class CountryListAdapter(
    private val callback: ((CountryModel) -> Unit)? = null
) : ListAdapter<CountryModel, CountryListViewHolder>(
    object : DiffUtil.ItemCallback<CountryModel>() {

        override fun areItemsTheSame(oldItem: CountryModel, newItem: CountryModel): Boolean {
            return oldItem.countryCode == newItem.countryCode
        }

        override fun areContentsTheSame(oldItem: CountryModel, newItem: CountryModel): Boolean {
            return oldItem.countryStates?.length == newItem.countryStates?.length
                    && oldItem.countryName == newItem.countryName
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