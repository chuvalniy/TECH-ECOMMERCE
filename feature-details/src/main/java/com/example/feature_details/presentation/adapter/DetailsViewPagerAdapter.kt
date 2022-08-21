package com.example.feature_details.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.feature_details.databinding.ViewPagerItemBinding

class DetailsViewPagerAdapter(
    private val glide: RequestManager
) : ListAdapter<String, DetailsViewPagerAdapter.DetailsViewHolder>(DetailsDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return (DetailsViewHolder(
            ViewPagerItemBinding.inflate(layoutInflater, parent, false),
            glide
        ))
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DetailsViewHolder(
        private val binding: ViewPagerItemBinding,
        private val glide: RequestManager
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: String) {
            glide.load(image).into(binding.ivProduct)
        }
    }

    companion object DetailsDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
}