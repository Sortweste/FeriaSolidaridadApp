package com.sort.feriaapp.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
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

        if (savedInstanceState != null)
            binding.webViewNews.restoreState(savedInstanceState)
        else
            initWebView()

        initToolBar()

        initListeners()
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewsDetailFragment()
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(){
        binding.webViewNews.settings.javaScriptEnabled = true
        binding.webViewNews.webChromeClient = object : WebChromeClient() {

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
            }

            /*Enter fullscreen*/
            override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
                super.onShowCustomView(view, callback)

                if (view is FrameLayout) {
                    fullscreenView = view
                    binding.fullScreenContainerNews.addView(fullscreenView)
                    binding.fullScreenContainerNews.visibility = View.VISIBLE
                    binding.toolbar.visibility = View.GONE
                }
            }

            /*Exit fullscreen*/
            override fun onHideCustomView() {
                super.onHideCustomView()

                binding.fullScreenContainerNews.removeView(fullscreenView)
                binding.fullScreenContainerNews.visibility = View.GONE
                binding.toolbar.visibility = View.VISIBLE
            }
        }
    }

    /*Hide three dots menu*/
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.about).isVisible = false
    }

    /*Add custom toolbar*/
    private fun initToolBar(){
        (activity as AppCompatActivity).apply {
            this.setSupportActionBar(binding.toolbar)
            this.supportActionBar?.also {
                it.setDisplayHomeAsUpEnabled(true)
                it.setDisplayShowHomeEnabled(true)
            }
        }
        /*Show Back Arrow*/
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onPause() {
        super.onPause()
        binding.webViewNews.onPause()
        binding.webViewNews.pauseTimers()
    }

    override fun onResume() {
        super.onResume()
        binding.webViewNews.resumeTimers()
        binding.webViewNews.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.webViewNews.destroy()
        _binding = null
    }

    private fun prepareIntent(url: String){
        val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(i)
    }

    private fun initListeners(){
        binding.linkView.setOnClickListener(this)
        binding.enlaceView.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v?.id){
            binding.linkView?.id -> { prepareIntent(binding.linkView?.contentDescription.toString()) }
            binding.enlaceView?.id -> { prepareIntent(binding.enlaceView?.contentDescription.toString()) }
        }

    }
}