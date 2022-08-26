package com.example.feature_cart.presentation.checkout.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core.extension.showSnackBar
import com.example.core.ui.BaseFragment
import com.example.feature_cart.R
import com.example.feature_cart.databinding.FragmentCheckoutBinding
import com.example.feature_cart.presentation.cart.model.CartEvent
import com.example.feature_cart.presentation.cart.model.CartSideEffect
import com.example.feature_cart.presentation.cart.view_model.CartViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class CheckoutFragment : BaseFragment<FragmentCheckoutBinding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCheckoutBinding.inflate(inflater, container, false)
}