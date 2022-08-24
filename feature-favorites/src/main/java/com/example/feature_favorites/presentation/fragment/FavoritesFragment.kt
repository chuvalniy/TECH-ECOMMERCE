package com.example.feature_favorites.presentation.fragment

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
import com.example.feature_favorites.R
import com.example.feature_favorites.databinding.FragmentFavoritesBinding
import com.example.feature_favorites.presentation.epoxy.FavoritesEpoxyController
import com.example.feature_favorites.presentation.epoxy.model.ModelFavoritesItem
import com.example.feature_favorites.presentation.model.FavoritesEvent
import com.example.feature_favorites.presentation.model.FavoritesSideEffect
import com.example.feature_favorites.presentation.model.FavoritesState
import com.example.feature_favorites.presentation.view_model.FavoritesViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoritesFragment : BaseFragment<FragmentFavoritesBinding>() {

    private val viewModel by viewModel<FavoritesViewModel>()
    private val glide by inject<RequestManager>()

    private var epoxyController: FavoritesEpoxyController? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupController()

        processUiEvent()

        observeUiState()
        observeUiEffect()
    }

    private fun processUiEvent() {
        binding.btnGoBack.setOnClickListener { viewModel.onEvent(FavoritesEvent.BackButtonClicked) }
    }

    private fun setupController() {
        epoxyController = FavoritesEpoxyController(glide).also { controller ->
            binding.epoxyRecyclerView.setController(controller)
        }

        EpoxyTouchHelper.initSwiping(binding.epoxyRecyclerView)
            .left()
            .withTarget(ModelFavoritesItem::class.java)
            .andCallbacks(object : EpoxyTouchHelper.SwipeCallbacks<ModelFavoritesItem>() {
                override fun onSwipeCompleted(
                    model: ModelFavoritesItem?,
                    itemView: View?,
                    position: Int,
                    direction: Int
                ) {
                    val swipedItem = model?.item ?: return
                    viewModel.onEvent(FavoritesEvent.ItemSwiped(swipedItem))
                }
            })

    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                processUiState(state)
            }
        }
    }

    private fun processUiState(state: FavoritesState) {
        epoxyController?.setData(state)

        binding.layoutNoFavorites.isVisible = !state.isLoading && state.data.isEmpty()
    }

    private fun observeUiEffect() = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        viewModel.sideEffect.collect { effect ->
            processUiEffect(effect)
        }
    }

    private fun processUiEffect(effect: FavoritesSideEffect) {
        when (effect) {
            is FavoritesSideEffect.NavigateBack -> findNavController().popBackStack()
            is FavoritesSideEffect.ShowSnackbar -> {
                requireContext().showSnackBar(
                    binding.root,
                    effect.message.asString(requireContext())
                )
            }
            is FavoritesSideEffect.ShowUndoSnackbar -> {
                requireContext().showActionSnackBar(
                    requireView(),
                    getString(R.string.favorites_deleted),
                    getString(com.example.ui_component.R.string.undo),
                    action = { viewModel.onEvent(FavoritesEvent.UndoClicked(effect.data)) }
                )
            }
        }
    }


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentFavoritesBinding.inflate(inflater, container, false)
}