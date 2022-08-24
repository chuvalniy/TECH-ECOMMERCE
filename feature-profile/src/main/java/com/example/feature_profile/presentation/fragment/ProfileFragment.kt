package com.example.feature_profile.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.core.extension.showSnackBar
import com.example.core.ui.BaseFragment
import com.example.feature_profile.R
import com.example.feature_profile.databinding.FragmentProfileBinding
import com.example.feature_profile.presentation.model.ProfileEvent
import com.example.feature_profile.presentation.model.ProfileSideEffect
import com.example.feature_profile.presentation.model.ProfileState
import com.example.feature_profile.presentation.view_model.ProfileViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private val viewModel by sharedViewModel<ProfileViewModel>()

    private val glide by inject<RequestManager>()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProfileBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        processUiEvent()

        observeUiState()
        observeUiEffect()
    }

    private fun observeUiEffect() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.sideEffect.collect { effect ->
                when (effect) {
                    is ProfileSideEffect.NavigateBack -> findNavController().popBackStack()
                    is ProfileSideEffect.NavigateToCards -> TODO()
                    is ProfileSideEffect.NavigateToEditProfile -> TODO()
                    is ProfileSideEffect.NavigateToNotifications -> TODO()
                    is ProfileSideEffect.NavigateToOrderHistory -> {
                        findNavController().navigate(R.id.action_profileFragment_to_ordersFragment)
                    }
                    is ProfileSideEffect.NavigateToShoppingAddress -> TODO()
                    is ProfileSideEffect.ShowSnackbar -> {
                        requireContext().showSnackBar(
                            binding.root,
                            effect.message.asString(requireContext())
                        )
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun processUiEvent() {
        binding.cvOrderHistory.setOnClickListener {
            viewModel.onEvent(ProfileEvent.OrderHistoryButtonClicked)
        }

        binding.btnGoBack.setOnClickListener {
            viewModel.onEvent(ProfileEvent.BackButtonClicked)
        }
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                processUiState(state)
            }
        }
    }

    private fun processUiState(state: ProfileState) {
        binding.tvName.text = getString(
            R.string.formatted_name,
            state.data.firstName,
            state.data.lastName
        )

        if (state.data.image.isNotBlank()) glide.load(state.data.image).into(binding.ivUser)
    }
}