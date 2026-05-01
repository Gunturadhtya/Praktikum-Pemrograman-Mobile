package com.mobil.modul3xml.ui.screens.language

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobil.modul3xml.databinding.ItemLanguageBinding

class LanguageAdapter(
    private val onLanguageClick: (String) -> Unit
) : ListAdapter<LanguageUiModel, LanguageAdapter.LanguageViewHolder>(LanguageDiffCallback) {

    inner class LanguageViewHolder(private val binding: ItemLanguageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onLanguageClick(getItem(position).tag)
                }
            }
        }

        fun bind(item: LanguageUiModel) {
            binding.tvLanguageName.text = item.name
            binding.ivCheck.visibility = if (item.isSelected) View.VISIBLE else View.INVISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val binding = ItemLanguageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return LanguageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}