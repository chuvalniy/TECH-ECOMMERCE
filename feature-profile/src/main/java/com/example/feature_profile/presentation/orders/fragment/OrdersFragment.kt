package com.example.feature_profile.presentation.orders.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core.extension.showSnackBar
import com.example.core.ui.BaseFragment
import com.example.core_navigation.NavCommand
import com.example.core_navigation.NavCommands
import com.example.core_navigation.navigate
import com.example.feature_profile.databinding.FragmentOrdersBinding
import com.example.feature_profile.presentation.orders.model.OrdersEvent
import com.example.feature_profile.presentation.orders.model.OrdersSideEffect
import com.example.feature_profile.presentation.orders.view_model.OrdersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class OrdersFragment : BaseFragment<FragmentOrdersBinding>() {

    private val viewModel by viewModel<OrdersViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        processUiEvent()

        observeUiEffect()
        observeUiState()
    }

    private fun processUiEvent() {
        binding.btnGoBack.setOnClickListener { viewModel.onEvent(OrdersEvent.BackButtonClicked) }

        binding.cvStartOrder.setOnClickListener { viewModel.onEvent(OrdersEvent.StartOrderingButtonClicked) }
    }

    private fun observeUiEffect() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is OrdersSideEffect.NavigateBack -> findNavController().popBackStack()
                is OrdersSideEffect.ShowSnackBar -> requireContext().showSnackBar(
                    binding.root,
                    effect.message.asString(requireContext())
                )
                is OrdersSideEffect.NavigateToSearch -> navigateToSearch()
            }
        }
    }

    private fun navigateToSearch() = navigate(
        NavCommand(
            NavCommands.DeepLink(
                url = Uri.parse("myApp://featureSearch"),
                isModal = true,
                isSingleTop = true,
            )
        )
    )

    private fun observeUiState() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.state.collect { state ->
            binding.layoutError.isVisible = state.data.isEmpty()
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentOrdersBinding.inflate(inflater, container, false)
}