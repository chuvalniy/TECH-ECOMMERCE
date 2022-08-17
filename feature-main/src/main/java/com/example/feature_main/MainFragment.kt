package com.example.feature_main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.core.ui.BaseFragment
import com.example.feature_main.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController =
            (childFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment).navController

        binding.bottomnavBar.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomnavBar.isVisible =
                destination.id == com.example.feature_home.R.id.homeFragment
        }
    }

    override fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMainBinding.inflate(inflater, container, false)
}