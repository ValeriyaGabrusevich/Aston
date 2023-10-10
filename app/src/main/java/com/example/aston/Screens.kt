package com.example.aston

import com.example.aston.fragments.FragmentA
import com.example.aston.fragments.FragmentB
import com.example.aston.fragments.FragmentC
import com.example.aston.fragments.FragmentD
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
        fun fragmentA() = FragmentScreen { FragmentA() }
        fun fragmentB() = FragmentScreen { FragmentB() }
        fun fragmentC(arg: String) = FragmentScreen { FragmentC.newInstance(arg) }
        fun fragmentD() = FragmentScreen { FragmentD() }
}