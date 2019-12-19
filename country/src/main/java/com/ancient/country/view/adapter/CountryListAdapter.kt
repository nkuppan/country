package com.ancient.country.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.ancient.country.databinding.CountryListItemBinding
import com.ancient.country.model.CountryModel
import com.ancient.sportscorer.common.view.adapter.DataBoundListAdapter

/**
 * Created by ancientinc on 2019-07-06.
 **/


class CountryListAdapter(
        private val aListener: ((CountryModel) -> Unit)? = null
) : DataBoundListAdapter<CountryModel, CountryListItemBinding>(
        diffCallback = object : DiffUtil.ItemCallback<CountryModel>() {

            override fun areItemsTheSame(oldItem: CountryModel, newItem: CountryModel): Boolean {
                return oldItem.countryCode == newItem.countryCode
            }

            override fun areContentsTheSame(oldItem: CountryModel, newItem: CountryModel): Boolean {
                return oldItem.countryStates?.length == newItem.countryStates?.length
                        && oldItem.countryName == newItem.countryName
            }
        }
) {
    override fun createBinding(parent: ViewGroup): CountryListItemBinding {
        val dataBinding = CountryListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
        )

        dataBinding.root.setOnClickListener {
            dataBinding.viewModel?.let {
                aListener?.invoke(it)
            }
        }

        return dataBinding
    }

    override fun bind(binding: CountryListItemBinding, item: CountryModel) {
        binding.viewModel = item
    }
}