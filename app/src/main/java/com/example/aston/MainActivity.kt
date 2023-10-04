package com.example.aston

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aston.databinding.ActivityMainBinding
import com.example.aston.fragments.FragmentA

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(binding.fragmentContainerView.id, FragmentA())
            .commit()


    }
}