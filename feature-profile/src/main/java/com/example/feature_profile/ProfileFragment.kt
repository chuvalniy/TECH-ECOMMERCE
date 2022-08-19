package com.example.feature_profile

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.core.ui.BaseFragment
import com.example.feature_profile.databinding.FragmentProfileBinding


class ProfileFragment : BaseFragment<FragmentProfileBinding>() {


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProfileBinding.inflate(inflater, container, false)
}