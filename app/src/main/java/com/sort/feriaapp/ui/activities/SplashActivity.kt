package com.sort.feriaapp.ui.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.sort.feriaapp.R
import com.sort.feriaapp.storage.SharedPreferencesManager
import com.sort.feriaapp.utils.LOGIN_KEY
import com.sort.feriaapp.utils.TOKEN_KEY
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val handler = Handler()

    @Inject
    lateinit var mSharedPreferences: SharedPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val runnable = Runnable {
        if(!isFinishing){
            if (!mSharedPreferences.getData(LOGIN_KEY).isNullOrEmpty())
                startIntent()
            else {
                mSharedPreferences.putData(LOGIN_KEY, "Logged")
                startVideoIntent()
                finish()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 2000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    private fun startIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startVideoIntent() {
        val intent = Intent(this, VideoActivity::class.java)
        startActivity(intent)
        finish()
    }

}