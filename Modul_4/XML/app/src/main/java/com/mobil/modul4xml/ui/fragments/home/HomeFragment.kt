package com.mobil.modul4xml.ui.fragments.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.mobil.modul4xml.R
import com.mobil.modul4xml.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch
import androidx.core.net.toUri
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.HeroCarouselStrategy
import com.mobil.modul4xml.data.ProblemRepository
import timber.log.Timber

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val repository = ProblemRepository

    private val factory = HomeViewModelFactory(repository, "Modul 4 XML")
    private val viewModel: HomeViewModel by viewModels {factory}

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
                    findNavController().navigate(R.id.action_homeFragment_to_languageFragment)
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
            viewModel.onProblemClicked(problemId)
        }
        binding.carouselRecyclerView.adapter = carouselAdapter

        listAdapter = ProblemListAdapter(
            onExternalClick = { url ->
                viewModel.onExternalUrlClicked(url)
            },
            onDetailClick = { problemId ->
                viewModel.onProblemClicked(problemId)
            }
        )
        binding.listRecyclerView.adapter = listAdapter
    }

    private fun navigateToDetail(problemId: String) {
        val bundle = Bundle().apply{
            putString("problemId", problemId)
        }
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    val problems = state.problems
                    carouselAdapter.submitList(problems)
                    listAdapter.submitList(problems)

                    if (problems.isEmpty()) {
                        binding.carouselRecyclerView.visibility = View.GONE
                        binding.listRecyclerView.visibility = View.GONE
                    } else {
                        binding.carouselRecyclerView.visibility = View.VISIBLE
                        binding.listRecyclerView.visibility = View.VISIBLE
                    }

                    state.navigateToDetailEvent?.let { problemId ->
                        Timber.d("Navigating to detail page for problemId: $problemId")
                        navigateToDetail(problemId)
                        viewModel.onDetailNavigationHandled()
                    }

                    state.openExternalUrlEvent?.let { url ->
                        Timber.d("Opening external URL: $url")
                        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
                        startActivity(intent)
                        viewModel.onExternalUrlHandled()
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