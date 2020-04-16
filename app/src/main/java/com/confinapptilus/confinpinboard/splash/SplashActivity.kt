package com.confinapptilus.confinpinboard.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.confinapptilus.confinpinboard.R
import com.confinapptilus.confinpinboard.main.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity(), SplashContract.View {

    private val presenter: SplashContract.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        presenter.attachView(this)
        presenter.applyDelay()

        Glide.with(applicationContext)
            .asGif()
            .load(R.raw.splash)
            .into(splash_icon)
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
