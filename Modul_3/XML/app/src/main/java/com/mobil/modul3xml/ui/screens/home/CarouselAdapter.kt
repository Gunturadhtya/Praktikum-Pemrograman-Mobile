package com.mobil.modul3xml.ui.screens.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobil.modul3xml.data.CodeforcesProblem
import com.mobil.modul3xml.databinding.ItemProblemCarouselBinding

class CarouselAdapter(
    private val onItemClick: (String) -> Unit
) : ListAdapter<CodeforcesProblem, CarouselAdapter.CarouselViewHolder>(ProblemDiffCallback) {

    inner class CarouselViewHolder(private val binding: ItemProblemCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(problem: CodeforcesProblem) {
            binding.ivCarouselItem.setImageResource(problem.img)
            binding.root.contentDescription = binding.root.context.getString(problem.title)
            binding.root.setOnClickListener { onItemClick(problem.problemId) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding = ItemProblemCarouselBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}