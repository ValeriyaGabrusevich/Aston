package com.example.aston.fragments

import android.app.Application
import com.github.terrakok.cicerone.Cicerone

object MyApplication: Application() {
    val cicerone = Cicerone.create()
    val router = cicerone.router
}