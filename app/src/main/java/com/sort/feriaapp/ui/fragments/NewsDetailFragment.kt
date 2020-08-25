package com.sort.feriaapp.ui.fragments

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
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.sort.feriaapp.R
import com.sort.feriaapp.databinding.FragmentNewsDetailBinding
import com.sort.feriaapp.viewmodels.NewsDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment : Fragment(), View.OnClickListener {

    private var _binding : FragmentNewsDetailBinding? = null
    private val binding get() = _binding!!

    private val newsDetailViewModel: NewsDetailViewModel by viewModels()

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
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
            }
        }
        initToolBar()

        binding.linkView.setOnClickListener(this)

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewsDetailFragment()
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
}