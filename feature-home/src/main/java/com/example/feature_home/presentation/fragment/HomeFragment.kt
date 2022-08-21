package com.example.feature_home.presentation.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.RequestManager
import com.example.core.extension.onTabSelected
import com.example.core.navigation.NavCommand
import com.example.core.navigation.NavCommands
import com.example.core.navigation.navigate
import com.example.core.ui.BaseFragment
import com.example.feature_home.databinding.FragmentHomeBinding
import com.example.feature_home.presentation.epoxy.HomeEpoxyController
import com.example.feature_home.presentation.model.HomeEvent
import com.example.feature_home.presentation.model.HomeSideEffect
import com.example.feature_home.presentation.model.HomeState
import com.example.feature_home.presentation.view_model.HomeViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel by viewModel<HomeViewModel>()
    private val glide by inject<RequestManager>()

    private var epoxyController: HomeEpoxyController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        processUiEvent()

        setupTabLayout()
        setupEpoxyController()

        observeUiState()
        observeUiEffect()
    }

    private fun processUiEvent() {
        binding.tabLayout.onTabSelected { category ->
            viewModel.onEvent(HomeEvent.CategorySelected(category))
        }

        binding.searchViewHome.setOnClickListener {
            viewModel.onEvent(HomeEvent.SearchClicked)
        }
    }

    private fun setupTabLayout() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Wearable"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Phones"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Laptops"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Tablet"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Drones"))
    }

    private fun setupEpoxyController() {
        epoxyController = HomeEpoxyController(
            glide = glide,
            onProductClick = { viewModel.onEvent(HomeEvent.ProductClicked(it)) }
        ).also { controller ->
            binding.epoxyRecyclerView.setController(controller)
        }
    }

    private fun observeUiEffect() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is HomeSideEffect.ShowSnackbar -> {

                }
                is HomeSideEffect.NavigateToSearch -> {
                    navigateToSearch()
                }
                is HomeSideEffect.NavigateToDetails -> {
                    navigateToDetails(effect)
                }
            }
        }
    }

    private fun navigateToDetails(effect: HomeSideEffect.NavigateToDetails) {
        navigate(
            NavCommand(
                NavCommands.DeepLink(
                    url = Uri.parse("myApp://featureDetails/${effect.id}"),
                    isModal = true,
                    isSingleTop = false
                )
            )
        )
    }

    private fun navigateToSearch() {
        navigate(
            NavCommand(
                NavCommands.DeepLink(
                    url = Uri.parse("myApp://featureSearch"),
                    isModal = true,
                    isSingleTop = false
                )
            )
        )
    }


    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                processUiState(state)
            }
        }
    }

    private fun processUiState(state: HomeState) {
        epoxyController?.setData(state)
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

}