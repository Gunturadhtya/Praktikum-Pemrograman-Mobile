package com.mobil.modul3xml.ui.screens.language

import androidx.recyclerview.widget.DiffUtil

object LanguageDiffCallback : DiffUtil.ItemCallback<LanguageModel>() {
    override fun areItemsTheSame(oldItem: LanguageModel, newItem: LanguageModel): Boolean {
        return oldItem.tag == newItem.tag
    }

    override fun areContentsTheSame(oldItem: LanguageModel, newItem: LanguageModel): Boolean {
        return oldItem == newItem
    }
}