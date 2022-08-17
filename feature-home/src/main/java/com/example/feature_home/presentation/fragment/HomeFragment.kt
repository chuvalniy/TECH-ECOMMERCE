package com.example.feature_home.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.core.ui.BaseFragment
import com.example.feature_home.databinding.FragmentHomeBinding
import com.example.feature_home.presentation.epoxy.HomeEpoxyController
import com.example.feature_home.presentation.model.UiSideEffect
import com.example.feature_home.presentation.model.UiState
import com.example.feature_home.presentation.view_model.HomeViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel by viewModel<HomeViewModel>()

    private var epoxyController: HomeEpoxyController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupEpoxyController()

        observeUiState()
        observeUiEffect()
    }

    private fun setupEpoxyController() {
        epoxyController = HomeEpoxyController().also { controller ->
            binding.epoxyRecyclerView.setController(controller)
        }
    }

    private fun observeUiEffect() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiEffect.collect { effect ->
                when (effect) {
                    is UiSideEffect.ShowSnackbar -> {

                    }
                    is UiSideEffect.NavigateToSearch -> {

                    }
                }
            }
        }
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state ->
                processUiState(state)
            }
        }
    }

    private fun processUiState(state: UiState) {
        epoxyController?.setData(state)
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

}