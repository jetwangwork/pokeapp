package com.example.pokeapp

import android.app.Application
import com.example.pokeapp.util.SharedPref

class PokeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPref.init(this)
    }
}