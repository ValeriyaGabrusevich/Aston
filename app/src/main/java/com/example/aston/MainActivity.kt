package com.example.aston

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.aston.databinding.ActivityMainBinding
import com.example.aston.fragments.MyApplication.cicerone
import com.example.aston.fragments.MyApplication.router
import com.github.terrakok.cicerone.androidx.AppNavigator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val navigatorHolder get() = cicerone.getNavigatorHolder()
    val navigator = AppNavigator(this, R.id.fragment_container_view)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        router.navigateTo(Screens.fragmentA())
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator = navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}