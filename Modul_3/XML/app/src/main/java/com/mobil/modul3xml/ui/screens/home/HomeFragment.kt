package com.mobil.modul3xml.ui.screens.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.mobil.modul3xml.R
import com.mobil.modul3xml.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import androidx.core.net.toUri
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.HeroCarouselStrategy

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var carouselAdapter: CarouselAdapter
    private lateinit var listAdapter: ProblemListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupRecyclerViews()
        observeViewModel()
    }

    private fun setupToolbar() {
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_language -> {
//                    findNavController().navigate(R.id.action_homeFragment_to_languageFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun setupRecyclerViews() {
        val carouselLayoutManager = CarouselLayoutManager(HeroCarouselStrategy())
        carouselLayoutManager.carouselAlignment = CarouselLayoutManager.ALIGNMENT_START
        binding.carouselRecyclerView.layoutManager = carouselLayoutManager

        val snapHelper = CarouselSnapHelper()
        snapHelper.attachToRecyclerView(binding.carouselRecyclerView)

        carouselAdapter = CarouselAdapter { problemId ->
//            navigateToDetail(problemId)
        }
        binding.carouselRecyclerView.adapter = carouselAdapter

        listAdapter = ProblemListAdapter(
            onExternalClick = { url ->
                val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                startActivity(intent)
            },
            onDetailClick = { problemId ->
//                navigateToDetail(problemId)
            }
        )
        binding.listRecyclerView.adapter = listAdapter
    }

//    private fun navigateToDetail(problemId: String) {
//        val bundle = bundleOf("problemId" to problemId)
//        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
//    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    val problems = state.problems
                    carouselAdapter.submitList(problems)
                    listAdapter.submitList(problems)

                    // Handle visibility if empty (optional but good practice)
                    if (problems.isEmpty()) {
                        binding.carouselRecyclerView.visibility = View.GONE
                        binding.listRecyclerView.visibility = View.GONE
                    } else {
                        binding.carouselRecyclerView.visibility = View.VISIBLE
                        binding.listRecyclerView.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}