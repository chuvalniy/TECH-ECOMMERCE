package com.example.techecommerce

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.core_navigation.NavCommand
import com.example.core_navigation.NavCommands
import com.example.core_navigation.NavigationProvider
import com.example.techecommerce.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), com.example.core_navigation.NavigationProvider {

    private val navController: NavController
        get() = findNavController(R.id.nav_host)

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun launch(navCommand: com.example.core_navigation.NavCommand) {
        when (val target = navCommand.target) {
            is com.example.core_navigation.NavCommands.DeepLink -> openDeepLink(
                url = target.url,
                isModal = target.isModal,
                isSingleTop = target.isSingleTop
            )
            is com.example.core_navigation.NavCommands.Browser -> Unit
        }
    }

    private fun openDeepLink(url: Uri, isModal: Boolean, isSingleTop: Boolean) {
        val navOptions = if (isModal) {
            NavOptions.Builder()
                .setLaunchSingleTop(isSingleTop)
                .setPopUpTo(if (isSingleTop) R.id.app_nav_graph else -1, inclusive = isSingleTop)
                .build()
        } else {
            NavOptions.Builder()
                .setLaunchSingleTop(isSingleTop)
                .setPopUpTo(if (isSingleTop) R.id.app_nav_graph else -1, inclusive = isSingleTop)
                .build()
        }

        navController.navigate(url, navOptions)
    }
}