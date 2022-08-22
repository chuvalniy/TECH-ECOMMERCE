package com.example.feature_login.presentation.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core.extension.showSnackBar
import com.example.core.navigation.NavCommand
import com.example.core.navigation.NavCommands
import com.example.core.navigation.navigate
import com.example.core.ui.BaseFragment
import com.example.feature_login.R
import com.example.feature_login.databinding.FragmentLoginBinding
import com.example.feature_login.presentation.model.LoginEvent
import com.example.feature_login.presentation.model.LoginSideEffect
import com.example.feature_login.presentation.model.LoginState
import com.example.feature_login.presentation.view_model.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    private val viewModel by sharedViewModel<LoginViewModel>()

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        processUiEvent()

        observeUiEffect()
        observeUiState()
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
//                processUiState(state)
            }
        }
    }

    private fun processUiState(state: LoginState) {

    }

    private fun processUiEvent() {
        binding.cvLogin.setOnClickListener {
            viewModel.onEvent(LoginEvent.LoginButtonClicked)
        }

        binding.btnCreateAccount.setOnClickListener {
            viewModel.onEvent(LoginEvent.CreateNewAccountButtonClicked)
        }

        binding.etEmail.addTextChangedListener {
            viewModel.onEvent(LoginEvent.EmailChanged(it.toString()))
        }

        binding.etPassword.addTextChangedListener {
            viewModel.onEvent(LoginEvent.PasswordChanged(it.toString()))
        }
    }

    private fun observeUiEffect() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.sideEffect.collect { effect ->
                when (effect) {
                    is LoginSideEffect.NavigateToHome -> navigateToHome()
                    is LoginSideEffect.NavigateToRegister -> {
                        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
                    }
                    is LoginSideEffect.ShowSnackbar -> {
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

    private fun navigateToHome() {
        navigate(
            NavCommand(
                NavCommands.DeepLink(
                    url = Uri.parse("myApp://featureMain"),
                    isModal = true,
                    isSingleTop = true
                )
            )
        )
    }
}