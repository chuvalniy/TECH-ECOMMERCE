package com.example.techecommerce

import android.net.Uri
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.example.core.navigation.NavCommand
import com.example.core.navigation.NavCommands
import com.example.core.navigation.NavigationProvider
import com.example.techecommerce.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationProvider {

    private val navController: NavController
        get() = findNavController(R.id.nav_host)

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val window: Window = window
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
    }

    override fun launch(navCommand: NavCommand) {
        when (val target = navCommand.target) {
            is NavCommands.DeepLink -> openDeepLink(
                url = target.url,
                isModal = target.isModal,
                isSingleTop = target.isSingleTop
            )
            is NavCommands.Browser -> Unit
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