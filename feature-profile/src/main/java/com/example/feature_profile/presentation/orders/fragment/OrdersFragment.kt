package com.example.feature_profile.presentation.orders.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core.ui.BaseFragment
import com.example.feature_profile.databinding.FragmentOrdersBinding
import com.example.feature_profile.presentation.profile.model.ProfileEvent
import com.example.feature_profile.presentation.profile.model.ProfileSideEffect
import com.example.feature_profile.presentation.profile.view_model.ProfileViewModel
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

    }

    private fun observeUiEffect() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is ProfileSideEffect.NavigateBack -> findNavController().popBackStack()
                is ProfileSideEffect.ShowSnackbar -> TODO()
                else -> Unit
            }
        }
    }

    private fun observeUiState() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.state.collect { state ->
            binding.layoutError.isVisible = state.data.shippingAddresses.isEmpty()
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentOrdersBinding.inflate(inflater, container, false)
}