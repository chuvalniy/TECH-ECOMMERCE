package com.example.feature_cart.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.core.navigation.NavCommand
import com.example.core.navigation.NavCommands
import com.example.core.navigation.navigate
import com.example.core.ui.BaseFragment
import com.example.feature_cart.R
import com.example.feature_cart.databinding.FragmentCartBinding
import com.example.feature_cart.presentation.epoxy.CartEpoxyController
import com.example.feature_cart.presentation.model.CartEvent
import com.example.feature_cart.presentation.model.CartSideEffect
import com.example.feature_cart.presentation.view_model.CartViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CartFragment : BaseFragment<FragmentCartBinding>() {

    private val viewModel by sharedViewModel<CartViewModel>()
    private val glide by inject<RequestManager>()

    private var epoxyController: CartEpoxyController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        processUiEvent()

        setupEpoxyController()

        observeUiState()
        observeUiEffect()
    }

    private fun processUiEvent() {
        binding.btnGoBack.setOnClickListener { viewModel.onEvent(CartEvent.BackButtonClicked) }

        binding.cvCheckout.setOnClickListener { viewModel.onEvent(CartEvent.CheckoutButtonClicked) }
    }

    private fun setupEpoxyController() {
        epoxyController = CartEpoxyController(glide).also { controller ->
            binding.epoxyRecyclerView.setController(controller)
        }
    }

    private fun observeUiEffect() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.sideEffect.collect { effect ->
                when (effect) {
                    is CartSideEffect.NavigateBack -> findNavController().popBackStack()
                    is CartSideEffect.NavigateToCheckout -> {
                        findNavController().navigate(R.id.action_cartFragment_to_checkoutFragment)
                    }
                    is CartSideEffect.ShowSnackbar -> TODO()
                    else -> Unit
                }
            }
        }
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                epoxyController?.setData(state)
            }
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCartBinding.inflate(inflater, container, false)
}