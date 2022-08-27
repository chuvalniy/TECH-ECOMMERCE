package com.example.feature_profile.presentation.edit_profile.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core.extension.showSnackBar
import com.example.core.ui.BaseFragment
import com.example.feature_profile.databinding.FragmentEditProfileBinding
import com.example.feature_profile.presentation.edit_profile.model.EditProfileEvent
import com.example.feature_profile.presentation.edit_profile.model.EditProfileSideEffect
import com.example.feature_profile.presentation.edit_profile.view_model.EditProfileViewModel
import org.koin.androidx.viewmodel.ext.android.stateViewModel

class EditProfileFragment : BaseFragment<FragmentEditProfileBinding>() {

    private val viewModel by stateViewModel<EditProfileViewModel>(state = {
        val bundle = Bundle()
        bundle.putParcelable(USER_INFO, arguments?.getParcelable(USER_INFO))
        bundle
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        processUiEvent()

        observeUiEffect()
    }

    private fun observeUiEffect() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is EditProfileSideEffect.NavigateBack -> findNavController().popBackStack()
                is EditProfileSideEffect.ShowSnackbar -> requireContext().showSnackBar(
                    binding.root,
                    effect.message.asString(requireContext())
                )
            }
        }
    }

    private fun processUiEvent() {
        binding.btnSubmit.setOnClickListener { viewModel.onEvent(EditProfileEvent.SubmitButtonClicked) }

        binding.btnGoBack.setOnClickListener { viewModel.onEvent(EditProfileEvent.BackButtonClicked) }


        binding.etFirstName.setText(viewModel.user.firstName)
        binding.etFirstName.addTextChangedListener {
            viewModel.onEvent(EditProfileEvent.FirstNameChanged(it.toString()))
        }

        binding.etSecondName.setText(viewModel.user.lastName)
        binding.etSecondName.addTextChangedListener {
            viewModel.onEvent(EditProfileEvent.SecondNameChanged(it.toString()))
        }

        binding.etPhoneNumber.setText(viewModel.user.phoneNumber)
        binding.etPhoneNumber.addTextChangedListener {
            viewModel.onEvent(EditProfileEvent.PhoneNumberChanged(it.toString()))
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentEditProfileBinding.inflate(inflater, container, false)

    companion object {
        const val USER_INFO = "user_info"
    }
}