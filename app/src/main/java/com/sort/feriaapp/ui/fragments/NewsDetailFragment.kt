package com.sort.feriaapp.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.sort.feriaapp.R
import com.sort.feriaapp.databinding.FragmentNewsDetailBinding
import com.sort.feriaapp.viewmodels.NewsDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_institution_display.*

@AndroidEntryPoint
class NewsDetailFragment : Fragment(), View.OnClickListener {

    private var _binding : FragmentNewsDetailBinding? = null
    private val binding get() = _binding!!

    private val newsDetailViewModel: NewsDetailViewModel by viewModels()

    lateinit var fullscreenView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        binding.viewmodel = newsDetailViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initWebView()
        if(savedInstanceState!=null) binding.webViewNews.restoreState(savedInstanceState)

        initToolBar()

        binding.linkView.setOnClickListener(this)

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewsDetailFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }


    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.about).isVisible = false
    }

    private fun initToolBar(){
        (activity as AppCompatActivity).apply {
            this.setSupportActionBar(binding.toolbar)
            this.supportActionBar?.also {
                it.setDisplayHomeAsUpEnabled(true)
                it.setDisplayShowHomeEnabled(true)
            }
        }
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun prepareIntent(url: String){
        val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(i)
    }

    override fun onClick(v: View?) {
        prepareIntent(binding.linkView.contentDescription.toString())
    }

    private fun initObservers() {
        lifecycleScope.launchWhenCreated {
            newsDetailViewModel.newsInfo.observe(viewLifecycleOwner, Observer { info ->
                if(!info.videoURL.isNullOrBlank()) loadWebView(info.videoURL)
            })
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.webViewNews.saveState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        binding.webViewNews.restoreState(savedInstanceState)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(){
        binding.webViewNews.webViewClient = WebViewClientCustom()
        binding.webViewNews.webChromeClient = WebChromeClientCustom()
        binding.webViewNews.settings.javaScriptEnabled = true

    }

    private fun loadWebView(videoURL: String){
        val url = "<iframe width=\"300\" height=\"300\" src=\"https://www.youtube.com/embed/$videoURL\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe> "
        binding.webViewNews.loadData(url, "text/html", null)
    }

    class WebViewClientCustom : WebViewClient(){
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
        }
    }

    inner class WebChromeClientCustom: WebChromeClient(){

        private var mCustomView: View? = null
        private var mCustomViewCallback: CustomViewCallback? = null
        private var mFullscreenContainer: FrameLayout? = null
        private var mOriginalOrientation = 0
        private var mOriginalSystemUiVisibility = 0

        override fun onHideCustomView(){

            (activity?.window?.decorView as FrameLayout).removeView(mCustomView)
            this.mCustomView = null;
            (activity?.window?.decorView as FrameLayout).systemUiVisibility = mOriginalSystemUiVisibility
            activity?.requestedOrientation = mOriginalOrientation
            mCustomViewCallback?.onCustomViewHidden()
            mCustomViewCallback = null
        }

        override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {

            if(mCustomView!=null) onHideCustomView()
            else{
                mCustomView = view
                mOriginalSystemUiVisibility = activity?.window?.decorView?.systemUiVisibility!!
                mOriginalOrientation = activity?.requestedOrientation!!
                mCustomViewCallback = callback

                //ViewGroup.LayoutParams.MATCH_PARENT = -1
                (activity?.window?.decorView as FrameLayout).addView(mCustomView, FrameLayout.LayoutParams(-1,-1))
                (activity?.window?.decorView as FrameLayout).systemUiVisibility = 3846
            }
        }

    }


}