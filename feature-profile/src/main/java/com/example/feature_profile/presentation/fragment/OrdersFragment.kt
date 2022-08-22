package com.example.feature_profile.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.core.ui.BaseFragment
import com.example.feature_profile.databinding.FragmentOrdersBinding
import com.example.feature_profile.presentation.view_model.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class OrdersFragment : BaseFragment<FragmentOrdersBinding>() {

    private val viewModel by sharedViewModel<ProfileViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentOrdersBinding.inflate(inflater, container, false)
}