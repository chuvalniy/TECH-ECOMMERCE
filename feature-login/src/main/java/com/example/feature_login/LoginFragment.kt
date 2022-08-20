package com.example.feature_login

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.core.navigation.NavCommand
import com.example.core.navigation.NavCommands
import com.example.core.navigation.navigate
import com.example.core.ui.BaseFragment
import com.example.feature_login.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cvLogin.setOnClickListener {
            navigate(NavCommand(
                NavCommands.DeepLink(
                    url = Uri.parse("myApp://featureMain"),
                    isModal = true,
                    isSingleTop = true
                )
            ))
        }
    }
}