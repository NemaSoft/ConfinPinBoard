package com.coronapptilus.covidpinboard.splash

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.coronapptilus.covidpinboard.R
import com.coronapptilus.covidpinboard.main.MainActivity
import org.koin.android.ext.android.inject

class SplashActivity: AppCompatActivity(), SplashContract.View {

    private val presenter: SplashContract.Presenter by inject()
    private val gif by lazy { findViewById<ImageView>(R.id.splash_icon) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        presenter.attachView(this)
        presenter.applyDelay()

        Glide.with(applicationContext)
            .asGif()
            .load(R.raw.splash)
            .into(gif)
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