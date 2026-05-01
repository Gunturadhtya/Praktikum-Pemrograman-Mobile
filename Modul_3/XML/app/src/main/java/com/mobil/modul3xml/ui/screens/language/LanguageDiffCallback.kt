package com.mobil.modul3xml.ui.screens.language

import androidx.recyclerview.widget.DiffUtil

object LanguageDiffCallback : DiffUtil.ItemCallback<LanguageUiModel>() {
    override fun areItemsTheSame(oldItem: LanguageUiModel, newItem: LanguageUiModel): Boolean {
        return oldItem.tag == newItem.tag
    }

    override fun areContentsTheSame(oldItem: LanguageUiModel, newItem: LanguageUiModel): Boolean {
        return oldItem == newItem
    }
}