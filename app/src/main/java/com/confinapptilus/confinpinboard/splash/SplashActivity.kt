package com.confinapptilus.confinpinboard.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.confinapptilus.confinpinboard.R
import com.confinapptilus.confinpinboard.databinding.ActivitySplashBinding
import com.confinapptilus.confinpinboard.main.MainActivity
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity(), SplashContract.View {

    private var viewBinding: ActivitySplashBinding? = null

    private val presenter: SplashContract.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySplashBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        presenter.attachView(this)
        presenter.applyDelay()

        viewBinding?.splashIcon?.let { icon ->
            Glide.with(applicationContext)
                .asGif()
                .load(R.raw.splash)
                .into(icon)
        }
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
