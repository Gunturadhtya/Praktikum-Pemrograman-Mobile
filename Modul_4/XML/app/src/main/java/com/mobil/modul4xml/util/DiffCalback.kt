package com.mobil.modul4xml.util

import androidx.recyclerview.widget.DiffUtil

class DiffCallback<T : Any>(
    private val itemIdentifier: (T) -> Any
) : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return itemIdentifier(oldItem) == itemIdentifier(newItem)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}