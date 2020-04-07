package com.coronapptilus.covidpinboard.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.main.MainActivity
import org.koin.android.ext.android.inject

class SplashActivity: AppCompatActivity(), SplashContract.View {

    private val presenter: SplashContract.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        presenter.attachView(this)
        presenter.applyDelay()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        finish()
        startActivity(intent)
    }
}