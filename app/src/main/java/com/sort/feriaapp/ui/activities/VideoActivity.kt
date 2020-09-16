package com.sort.feriaapp.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.activity.viewModels
import com.sort.feriaapp.R
import com.sort.feriaapp.databinding.ActivityMainBinding
import com.sort.feriaapp.databinding.ActivityVideoBinding
import com.sort.feriaapp.viewmodels.VideoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_institution_display.*

/*NOT IMPLEMENTED: MUST BE DELETED*/
@AndroidEntryPoint
class VideoActivity : AppCompatActivity(), View.OnClickListener {

    private var _binding: ActivityVideoBinding? = null
    private val binding get() = _binding!!

    lateinit var fullscreenView: View

    private val videoViewModel: VideoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewmodel = videoViewModel
        binding.nextButton.setOnClickListener(this)
        initWebView()

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(){
            //binding.webViewVideo.settings.loadWithOverviewMode = true
        //binding.webViewVideo.layoutParams = RelativeLayout.LayoutParams(-1, -1)
        binding.webViewVideo.settings.useWideViewPort = true
        binding.webViewVideo.settings.javaScriptEnabled = true
        /*binding.webViewVideo.webChromeClient = object : WebChromeClient() {

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
            }

            override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
                super.onShowCustomView(view, callback)

                if (view is FrameLayout) {
                    fullscreenView = view
                    fullscreenContainer.addView(fullscreenView)
                    fullscreenContainer.visibility = View.VISIBLE
                    binding.nextButton.visibility = View.GONE
                }
            }

            override fun onHideCustomView() {
                super.onHideCustomView()
                fullscreenContainer.removeView(fullscreenView)
                fullscreenContainer.visibility = View.GONE
                binding.nextButton.visibility = View.VISIBLE
            }
        }*/

        binding.fullscreenContainer.removeAllViews()
        fullscreenView = binding.webViewVideo
        binding.fullscreenContainer.addView(fullscreenView)

    }

    override fun onPause() {
        super.onPause()
        binding.webViewVideo.onPause()
        binding.webViewVideo.pauseTimers()
    }

    override fun onResume() {
        super.onResume()
        binding.webViewVideo.resumeTimers()
        binding.webViewVideo.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.webViewVideo.destroy()
        _binding = null
    }

    private fun prepareIntent(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onClick(v: View?) {
        if(v?.id == binding.nextButton.id)
            prepareIntent()
    }

}