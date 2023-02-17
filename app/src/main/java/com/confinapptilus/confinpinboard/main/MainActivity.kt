package com.confinapptilus.confinpinboard.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.confinapptilus.confinpinboard.R
import com.confinapptilus.confinpinboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var viewBinding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        setupViews()
    }

    private fun setupViews() {
        val navController = findNavController(R.id.navHostFragment)
        viewBinding?.bottomNav?.setupWithNavController(navController)
    }
}
