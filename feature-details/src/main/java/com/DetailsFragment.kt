package com

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core.ui.BaseFragment
import com.example.feature_details.databinding.FragmentDetailsBinding


class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDetailsBinding.inflate(inflater, container, false)
}