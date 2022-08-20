package com.example.feature_cart.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.feature_cart.databinding.FragmentPaymentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PaymentBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentPaymentBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }
}