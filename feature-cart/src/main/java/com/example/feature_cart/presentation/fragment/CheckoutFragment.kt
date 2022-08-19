package com.example.feature_cart.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.core.ui.BaseFragment
import com.example.feature_cart.R
import com.example.feature_cart.databinding.FragmentCheckoutBinding

class CheckoutFragment : BaseFragment<FragmentCheckoutBinding>() {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCheckoutBinding.inflate(inflater, container, false)
}