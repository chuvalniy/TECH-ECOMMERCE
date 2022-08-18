package com.example.feature_search.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.core.extension.onQueryTextChanged
import com.example.core.ui.BaseFragment
import com.example.feature_search.databinding.FragmentSearchBinding
import com.example.feature_search.presentation.epoxy.SearchEpoxyController
import com.example.feature_search.presentation.model.SearchEvent
import com.example.feature_search.presentation.model.SearchSideEffect
import com.example.feature_search.presentation.model.SearchState
import com.example.feature_search.presentation.view_model.SearchViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel by viewModel<SearchViewModel>()
    private val glide by inject<RequestManager>()

    private var epoxyController: SearchEpoxyController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        processUiEvent()

        setupEpoxyController()

        observeUiState()
        observeUiEffect()
    }

    private fun processUiEvent() {
        binding.searchView.onQueryTextChanged { query ->
            viewModel.onEvent(SearchEvent.QueryChanged(query))
        }

        binding.btnBack.setOnClickListener { viewModel.onEvent(SearchEvent.BackButtonClicked) }
    }

    private fun setupEpoxyController() {
        epoxyController = SearchEpoxyController(glide).also { controller ->
            binding.epoxyRecyclerView.setController(controller)
        }
    }

    private fun observeUiEffect() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.sideEffect.collect { effect ->
                when (effect) {
                    is SearchSideEffect.NavigateBack -> findNavController().popBackStack()
                    is SearchSideEffect.NavigateToDetails -> TODO()
                    is SearchSideEffect.ShowSnackbar -> TODO()
                }
            }
        }
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                processUiState(state)
            }
        }
    }

    private fun processUiState(state: SearchState) {
        epoxyController?.setData(state)

        binding.layoutSearchError.isVisible =
            !state.isLoading && state.data.isEmpty() && state.searchQuery.isNotEmpty()
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSearchBinding.inflate(inflater, container, false)


}