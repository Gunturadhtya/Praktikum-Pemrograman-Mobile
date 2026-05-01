package com.mobil.modul3xml.ui.screens.language

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobil.modul3xml.databinding.ItemLanguageBinding
import com.mobil.modul3xml.util.DiffCallback

class LanguageAdapter(
    private val onLanguageClick: (String) -> Unit
) : ListAdapter<LanguageModel, LanguageAdapter.LanguageViewHolder>(
    DiffCallback<LanguageModel> { it.tag }
) {
    class LanguageViewHolder(private val binding: ItemLanguageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LanguageModel, onLanguageClick: (String) -> Unit) {
            binding.tvLanguageName.text = item.name
            binding.ivCheck.visibility = if (item.isSelected) View.VISIBLE else View.INVISIBLE

            binding.root.setOnClickListener {
                onLanguageClick(item.tag)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val binding = ItemLanguageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return LanguageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.bind(getItem(position), onLanguageClick)
    }
}