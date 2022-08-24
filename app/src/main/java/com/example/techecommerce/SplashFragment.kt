package com.example.techecommerce

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.core_navigation.NavCommand
import com.example.core_navigation.NavCommands
import com.example.core_navigation.navigate
import com.example.core.ui.BaseFragment
import com.example.techecommerce.databinding.FragmentSplashBinding


class SplashFragment : BaseFragment<FragmentSplashBinding>() {


    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentSplashBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        processUiEvent()
    }

    private fun processUiEvent() {
        binding.cvGetStarted.setOnClickListener {
            navigate(
                navCommand = com.example.core_navigation.NavCommand(
                    com.example.core_navigation.NavCommands.DeepLink(
                        url = Uri.parse("myApp://featureLogin"),
                        isModal = true,
                        isSingleTop = true
                    )
                )
            )
        }
    }
}