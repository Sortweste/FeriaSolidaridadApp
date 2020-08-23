package com.sort.feriaapp.ui.fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.sort.feriaapp.R
import com.sort.feriaapp.adapters.CarouselAdapter
import com.sort.feriaapp.adapters.RecyclerViewEventsAdapter
import com.sort.feriaapp.data.minimals.EventMinimal
import com.sort.feriaapp.databinding.FragmentInstitutionDisplayBinding
import com.sort.feriaapp.interfaces.RecyclerViewClickListener
import com.sort.feriaapp.utils.FACEBOOK_PACKAGE
import com.sort.feriaapp.utils.INSTAGRAM_PACKAGE
import com.sort.feriaapp.utils.TWITTER_PACKAGE
import com.sort.feriaapp.viewmodels.InstitutionDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InstitutionDetailFragment : Fragment(), RecyclerViewClickListener<EventMinimal>, View.OnClickListener {

    private var _binding: FragmentInstitutionDisplayBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: RecyclerViewEventsAdapter

    private val institutionDetailViewModel: InstitutionDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInstitutionDisplayBinding.inflate(inflater, container, false)
        binding.viewmodel = institutionDetailViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initListeners()

        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
            }
        }
        initToolBar()
        return binding.root
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.about).isVisible = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()
    }

    private fun initCarousel(imageSet: List<String>){
        binding.viewPagerCarousel?.adapter = CarouselAdapter(this, imageSet.size, imageSet)
        binding.viewPagerCarousel?.registerOnPageChangeCallback(onBoardingPageChangeCallback)
    }

    private var onBoardingPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            setCurrentIndicator(position)
        }
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


    private fun initRecyclerView() {
        adapter = RecyclerViewEventsAdapter(this, false)
        binding.recyclerViewInstitutionsEvents.also {
            it.setHasFixedSize(true)
            it.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            it.adapter = adapter
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenCreated {
            institutionDetailViewModel.institutionInfo.observe(viewLifecycleOwner, Observer { info ->
                adapter.setData(info.events.map { EventMinimal(it.id, it.imageURL) })
                if(info.institution.imageURL.size > 1)
                    setUpIndicators(info.institution.imageURL.size)
                initCarousel(info.institution.imageURL)
            })
        }
    }

    private fun setUpIndicators(size: Int){
        val layoutParams: LinearLayout.LayoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        val index = arrayOfNulls<ImageView>(size)
        layoutParams.setMargins(8,0,8,0)
        for(i in index.indices){
            index[i] = ImageView(context)
            index[i].apply {
                this?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.inactive_circle))
                this?.layoutParams = layoutParams
            }
            binding.layoutContainer?.addView(index[i])
        }
    }

    private fun setCurrentIndicator(position: Int){
        for(i in 0 until binding.layoutContainer?.childCount!!){
            val imageView  = binding.layoutContainer!![i] as ImageView
            if(i == position)
                imageView.setImageDrawable(requireContext().let { ContextCompat.getDrawable(it, R.drawable.active_circle) })
            else
                imageView.setImageDrawable(requireContext().let { ContextCompat.getDrawable(it, R.drawable.inactive_circle) })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.viewPagerCarousel?.unregisterOnPageChangeCallback(onBoardingPageChangeCallback)
        _binding = null
    }

    override fun onCardViewClick(view: View, obj: EventMinimal) {
        val action =
            InstitutionDetailFragmentDirections.actionInstitutionDetailFragmentToEventDetailFragment(
                obj.id
            )
        view.findNavController().navigate(action)
    }

    private fun initListeners(){
        binding.facebookView.setOnClickListener(this)
        binding.twitterView.setOnClickListener(this)
        binding.instagramView.setOnClickListener(this)
        binding.websiteView.setOnClickListener(this)
        binding.meetupView.setOnClickListener(this)
    }

    private fun prepareIntentSocialMedia(packageName: String, uriApp: String, uriWeb: String){
        val uri = Uri.parse(uriApp)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage(packageName)
        try {
            startActivity(intent)
        } catch(e: ActivityNotFoundException){
            startActivity(Intent( Intent.ACTION_VIEW, Uri.parse(uriWeb)))
        }
    }

    private fun prepareIntent(url: String){
        val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(i)
    }

    override fun onClick(v: View?) {
            when(v?.id){
                binding.meetupView.id -> { prepareIntent(binding.meetupView.text.toString()) }
                binding.websiteView.id -> { prepareIntent(binding.websiteView.contentDescription.toString()) }
                binding.facebookView.id -> { prepareIntentSocialMedia(FACEBOOK_PACKAGE, "fb://facewebmodal/f?href=https://www.facebook.com/${binding.facebookValue.contentDescription}", "https://www.facebook.com/${binding.facebookValue.contentDescription}")}
                binding.instagramView.id -> { prepareIntentSocialMedia(INSTAGRAM_PACKAGE, "http://instagram.com/_u/${binding.instagramValue.contentDescription}", "http://instagram.com/${binding.instagramValue.contentDescription}") }
                binding.twitterView.id -> { prepareIntentSocialMedia(TWITTER_PACKAGE, "twitter://user?screen_name=${binding.twitterValue.contentDescription}", "https://twitter.com/${binding.twitterValue.contentDescription}")}
            }
    }


}