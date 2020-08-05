package com.sort.feriaapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.sort.feriaapp.adapters.RecyclerViewEventsAdapter
import com.sort.feriaapp.data.Event
import com.sort.feriaapp.data.Institution
import com.sort.feriaapp.data.minimals.EventMinimal
import com.sort.feriaapp.databinding.FragmentInstitutionDisplayBinding
import com.sort.feriaapp.helpers.RecyclerViewClickListener
import com.sort.feriaapp.utils.InjectorUtils
import com.sort.feriaapp.viewmodels.InstitutionDetailViewModel


class InstitutionDetailFragment : Fragment(), RecyclerViewClickListener<EventMinimal>{

    private val args: InstitutionDetailFragmentArgs by navArgs()

    private var _binding: FragmentInstitutionDisplayBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: RecyclerViewEventsAdapter

    private val institutionDetailViewModel: InstitutionDetailViewModel by viewModels {
        InjectorUtils.provideInstitutionDetailVieModelFactory(
            this.requireActivity(),
            args.institutionId
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInstitutionDisplayBinding.inflate(inflater, container, false)
        binding.viewmodel = institutionDetailViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
            }
        }
        initToolBar()
        //requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressed = )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()
    }

    private fun initToolBar(){
        (activity as AppCompatActivity).apply {
            this.setSupportActionBar(binding.toolbar)
            this.supportActionBar?.also {
                it.setDisplayHomeAsUpEnabled(true)
                it.setDisplayShowHomeEnabled(true)
            }
        }
    }

    private fun initRecyclerView() {
        adapter = RecyclerViewEventsAdapter(this)
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
            })
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCardViewClick(view: View, obj: EventMinimal) {
        val action = InstitutionDetailFragmentDirections.actionInstitutionDetailFragmentToEventDetailFragment(obj.id)
        view.findNavController().navigate(action)
    }

}