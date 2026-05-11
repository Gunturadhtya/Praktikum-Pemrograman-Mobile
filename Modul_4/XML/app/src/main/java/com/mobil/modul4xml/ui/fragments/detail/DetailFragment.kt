package com.mobil.modul4xml.ui.fragments.detail

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
import com.mobil.modul4xml.data.ProblemRepository
import com.mobil.modul4xml.databinding.FragmentDetailBinding
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels {
        val problemId = arguments?.getString("problemId")
            ?: throw IllegalArgumentException("problemId argument is required")

        val problem = ProblemRepository.getProblemById(problemId)
            ?: throw IllegalArgumentException("Problem with id $problemId not found")

        DetailViewModelFactory(problem, "Modul 4 XML")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        observeViewModel()

        viewModel.loadDetail(requireContext())
    }

    private fun setupToolbar() {
        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    if (state.isNotFound) {
                        binding.tvNotFound.visibility = View.VISIBLE
                        binding.contentScrollView.visibility = View.GONE
                        binding.topAppBar.title = ""
                    } else if (state.titleRes != 0) {
                        binding.tvNotFound.visibility = View.GONE
                        binding.contentScrollView.visibility = View.VISIBLE

                        binding.topAppBar.title = getString(state.titleRes)
                        binding.tvTitle.setText(state.titleRes)
                        binding.tvDescription.setText(state.descRes)
                        binding.tvCode.text = state.code

                        if (state.imgRes != 0) {
                            binding.ivHeader.setImageResource(state.imgRes)
                        }
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