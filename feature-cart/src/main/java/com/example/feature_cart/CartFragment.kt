package com.example.feature_cart

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core.ui.BaseFragment
import com.example.feature_cart.databinding.FragmentCartBinding


class CartFragment : BaseFragment<FragmentCartBinding>() {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCartBinding.inflate(inflater, container, false)
}