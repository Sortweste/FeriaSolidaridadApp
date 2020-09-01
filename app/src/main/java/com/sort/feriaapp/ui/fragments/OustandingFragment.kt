package com.sort.feriaapp.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import com.sort.feriaapp.R
import com.sort.feriaapp.databinding.FragmentInstitutionDisplayBinding
import com.sort.feriaapp.databinding.FragmentOustandingBinding
import com.sort.feriaapp.viewmodels.OustandingViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_institution_display.*

@AndroidEntryPoint
class OustandingFragment : Fragment() {

    private var _binding: FragmentOustandingBinding? = null
    private val binding get() = _binding!!

    lateinit var fullscreenView: View

    private val oustandingViewModel: OustandingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOustandingBinding.inflate(inflater, container, false)
        binding.viewmodel = oustandingViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initWebView()

        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(){
        binding.webViewRector.settings.javaScriptEnabled = true
        binding.webViewRector.webChromeClient = object : WebChromeClient() {

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
            }

            override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
                super.onShowCustomView(view, callback)

                if (view is FrameLayout) {
                    fullscreenView = view
                    binding.fullscreenContainerRector.addView(fullscreenView)
                    binding.fullscreenContainerRector.visibility = View.VISIBLE
                    binding.titleRector.visibility = View.GONE
                }
            }

            override fun onHideCustomView() {
                super.onHideCustomView()

                binding.fullscreenContainerRector.removeView(fullscreenView)
                binding.fullscreenContainerRector.visibility = View.GONE
                binding.titleRector.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = OustandingFragment()
    }

    override fun onPause() {
        super.onPause()
        binding.webViewRector.onPause()
        binding.webViewRector.pauseTimers()
    }

    override fun onResume() {
        super.onResume()
        binding.webViewRector.resumeTimers()
        binding.webViewRector.onResume()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding.webViewRector.destroy()
        _binding = null
    }
}