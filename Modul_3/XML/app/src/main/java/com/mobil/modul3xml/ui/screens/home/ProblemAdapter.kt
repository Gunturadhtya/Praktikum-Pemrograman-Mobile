package com.mobil.modul3xml.ui.screens.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobil.modul3xml.databinding.ItemProblemCardBinding
import com.mobil.modul3xml.databinding.ItemProblemCarouselBinding
import com.mobil.modul3xml.data.CodeforcesProblem

class ProblemListAdapter(
    private val onExternalClick: (String) -> Unit,
    private val onDetailClick: (String) -> Unit
) : ListAdapter<CodeforcesProblem, ProblemListAdapter.ProblemViewHolder>(ProblemDiffCallback) {

    inner class ProblemViewHolder(private val binding: ItemProblemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(problem: CodeforcesProblem) {
            binding.ivProblem.setImageResource(problem.img)
            binding.tvTitle.setText(problem.title)
            binding.tvDescription.setText(problem.description)

            binding.btnProblem.setOnClickListener { onExternalClick(problem.url) }
            binding.btnDetail.setOnClickListener { onDetailClick(problem.problemId) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemViewHolder {
        val binding = ItemProblemCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProblemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProblemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}