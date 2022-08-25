package com.example.feature_profile.presentation.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core_navigation.NavCommand
import com.example.core_navigation.NavCommands
import com.example.core_navigation.navigate
import com.example.core.ui.BaseFragment
import com.example.feature_profile.databinding.FragmentOrdersBinding
import com.example.feature_profile.presentation.model.ProfileEvent
import com.example.feature_profile.presentation.model.ProfileSideEffect
import com.example.feature_profile.presentation.view_model.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class OrdersFragment : BaseFragment<FragmentOrdersBinding>() {

    private val viewModel by sharedViewModel<ProfileViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        processUiEvent()

        observeUiEffect()
        observeUiState()
    }

    private fun processUiEvent() {
        binding.btnGoBack.setOnClickListener { viewModel.onEvent(ProfileEvent.BackButtonClicked) }

        binding.cvStartOrder.setOnClickListener { viewModel.onEvent(ProfileEvent.StartOrderingButtonClicked) }
    }

    private fun observeUiEffect() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.sideEffect.collect { effect ->
                when (effect) {
                    is ProfileSideEffect.NavigateBack -> findNavController().popBackStack()
                    is ProfileSideEffect.ShowSnackbar -> TODO()
                    is ProfileSideEffect.NavigateToSearch -> navigateToSearch()
                    else -> Unit
                }
            }
        }
    }

    private fun navigateToSearch() {
        navigate(
            com.example.core_navigation.NavCommand(
                com.example.core_navigation.NavCommands.DeepLink(
                    url = Uri.parse("myApp://featureSearch"),
                    isModal = true,
                    isSingleTop = true
                )
            )
        )
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                binding.layoutError.isVisible = state.data.shippingAddresses.isEmpty()
            }
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentOrdersBinding.inflate(inflater, container, false)
}