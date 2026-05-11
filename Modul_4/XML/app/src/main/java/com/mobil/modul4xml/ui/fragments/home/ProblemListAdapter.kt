package com.mobil.modul4xml.ui.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobil.modul4xml.databinding.ItemProblemCardBinding
import com.mobil.modul4xml.data.CodeforcesProblem
import com.mobil.modul4xml.util.DiffCallback

class ProblemListAdapter(
    private val onExternalClick: (String) -> Unit,
    private val onDetailClick: (String) -> Unit
) : ListAdapter<CodeforcesProblem, ProblemListAdapter.ProblemViewHolder>(
    DiffCallback<CodeforcesProblem> {it.problemId}
) {

    class ProblemViewHolder(
        private val binding: ItemProblemCardBinding,
        private val onExternalClick: (String) -> Unit,
        private val onDetailClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(problem: CodeforcesProblem) {
            binding.ivProblem.setImageResource(problem.img)
            binding.tvTitle.setText(problem.title)
            binding.tvDescription.setText(problem.description)

            // TODO: Log saat tombol Detail dan tombol Explicit Intent ditekan (Timber)
            binding.btnProblem.setOnClickListener { onExternalClick(problem.url) }
            binding.btnDetail.setOnClickListener { onDetailClick(problem.problemId) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemViewHolder {
        val binding = ItemProblemCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProblemViewHolder(binding, onExternalClick, onDetailClick)
    }

    override fun onBindViewHolder(holder: ProblemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}