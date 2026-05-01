package com.mobil.modul3xml.ui.screens.home

import androidx.recyclerview.widget.DiffUtil
import com.mobil.modul3xml.data.CodeforcesProblem

object ProblemDiffCallback : DiffUtil.ItemCallback<CodeforcesProblem>() {
    override fun areItemsTheSame(oldItem: CodeforcesProblem, newItem: CodeforcesProblem): Boolean {
        return oldItem.problemId == newItem.problemId
    }

    override fun areContentsTheSame(oldItem: CodeforcesProblem, newItem: CodeforcesProblem): Boolean {
        return oldItem == newItem
    }
}