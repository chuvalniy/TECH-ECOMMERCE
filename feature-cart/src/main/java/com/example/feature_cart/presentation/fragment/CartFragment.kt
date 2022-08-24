package com.example.feature_cart.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.EpoxyTouchHelper
import com.bumptech.glide.RequestManager
import com.example.core.extension.showActionSnackBar
import com.example.core.extension.showSnackBar
import com.example.core.ui.BaseFragment
import com.example.feature_cart.R
import com.example.feature_cart.databinding.FragmentCartBinding
import com.example.feature_cart.presentation.epoxy.CartEpoxyController
import com.example.feature_cart.presentation.epoxy.model.ModelCartItem
import com.example.feature_cart.presentation.model.CartEvent
import com.example.feature_cart.presentation.model.CartSideEffect
import com.example.feature_cart.presentation.model.CartState
import com.example.feature_cart.presentation.view_model.CartViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CartFragment : BaseFragment<FragmentCartBinding>() {

    private val viewModel by viewModel<CartViewModel>()
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

        binding.btnClear.setOnClickListener { viewModel.onEvent(CartEvent.ClearCartButtonClicked) }
    }

    private fun setupEpoxyController() {
        epoxyController = CartEpoxyController(glide).also { controller ->
            binding.epoxyRecyclerView.setController(controller)
        }

        EpoxyTouchHelper.initSwiping(binding.epoxyRecyclerView)
            .left()
            .withTarget(ModelCartItem::class.java)
            .andCallbacks(object : EpoxyTouchHelper.SwipeCallbacks<ModelCartItem>() {
                override fun onSwipeCompleted(
                    model: ModelCartItem?,
                    itemView: View?,
                    position: Int,
                    direction: Int
                ) {
                    val swipedItem = model?.item ?: return
                    viewModel.onEvent(CartEvent.ItemSwiped(swipedItem))
                }
            })

    }

    private fun observeUiEffect() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.sideEffect.collect { effect ->
            processUiEffect(effect)
        }
    }

    private fun processUiEffect(effect: CartSideEffect) {
        when (effect) {
            is CartSideEffect.NavigateBack -> findNavController().popBackStack()
            is CartSideEffect.NavigateToCheckout -> {
                findNavController().navigate(R.id.action_cartFragment_to_checkoutFragment)
            }
            is CartSideEffect.ShowSnackbar -> {
                requireContext().showSnackBar(
                    binding.root,
                    effect.message.asString(requireContext())
                )
            }
            is CartSideEffect.ShowUndoSnackbar -> {
                requireContext().showActionSnackBar(
                    requireView(),
                    getString(com.example.ui_component.R.string.item_deleted),
                    getString(com.example.ui_component.R.string.undo),
                    action = { viewModel.onEvent(CartEvent.UndoClicked(effect.data)) }
                )
            }
            else -> Unit
        }
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                processUiState(state)
            }
        }
    }

    private fun processUiState(state: CartState) {
        epoxyController?.setData(state)

        binding.layoutEmptyCart.isVisible = !state.isLoading && state.data.isEmpty()
        binding.cvTotalCost.isVisible = state.data.isNotEmpty()
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCartBinding.inflate(inflater, container, false)
}