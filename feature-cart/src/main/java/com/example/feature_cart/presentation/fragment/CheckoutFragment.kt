package com.example.feature_cart.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core.ui.BaseFragment
import com.example.feature_cart.R
import com.example.feature_cart.databinding.FragmentCheckoutBinding
import com.example.feature_cart.presentation.model.CartEvent
import com.example.feature_cart.presentation.model.CartSideEffect
import com.example.feature_cart.presentation.view_model.CartViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CheckoutFragment : BaseFragment<FragmentCheckoutBinding>() {

    private val viewModel by sharedViewModel<CartViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        processUiEvent()

        observeUiEffect()
    }

    private fun processUiEvent() {
        binding.cvConfirmPay.setOnClickListener { viewModel.onEvent(CartEvent.ConfirmAndPayButtonClicked) }
    }

    private fun observeUiEffect() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.sideEffect.collect { effect ->
                when (effect) {
                    is CartSideEffect.NavigateToPayment -> {
                        findNavController().navigate(R.id.action_checkoutFragment_to_paymentBottomSheetFragment)
                    }
                    is CartSideEffect.ShowSnackbar -> TODO()
                    else -> Unit
                }
            }
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCheckoutBinding.inflate(inflater, container, false)
}