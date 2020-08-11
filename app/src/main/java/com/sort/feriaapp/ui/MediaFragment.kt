package com.sort.feriaapp.ui

import android.content.res.Configuration
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.card.MaterialCardView
import com.sort.feriaapp.R
import com.sort.feriaapp.adapters.RecyclerViewEventsAdapter
import com.sort.feriaapp.data.minimals.EventMinimal
import com.sort.feriaapp.databinding.CardViewEventsBinding
import com.sort.feriaapp.databinding.FragmentMediaBinding
import com.sort.feriaapp.helpers.RecyclerViewClickListener
import com.sort.feriaapp.utils.InjectorUtils
import com.sort.feriaapp.viewmodels.EventViewModel
import kotlinx.android.synthetic.main.card_view_events.view.*


class MediaFragment : Fragment(), RecyclerViewClickListener<EventMinimal> {

    private var _binding: FragmentMediaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: EventViewModel by viewModels{
        InjectorUtils.provideEventViewModelFactory(this)
    }

    private lateinit var adapter: RecyclerViewEventsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMediaBinding.inflate(inflater, container, false)
        binding.eventViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = MediaFragment()
    }

    private fun initRecyclerView(){
        adapter = RecyclerViewEventsAdapter(this,true)
        binding.recyclerViewEvent.also {
            it.setHasFixedSize(true)
            if(it.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
                if(isTablet()){
                    it.layoutManager = GridLayoutManager(requireContext(), 3)
                }else{
                    it.layoutManager = GridLayoutManager(requireContext(), 2)
                }

            } else{
                if(isTablet()){
                    it.layoutManager = GridLayoutManager(requireContext(), 4)
                }else{
                    it.layoutManager = GridLayoutManager(requireContext(), 3)
                }
            }
            it.adapter = adapter
        }
    }

    private fun initObservers(){
        lifecycleScope.launchWhenCreated {
            viewModel.events.observe(viewLifecycleOwner, Observer{
                adapter.setData(it)
            })
        }
    }

    override fun onCardViewClick(view: View, obj: EventMinimal) {
        val action = MediaFragmentDirections.actionMediaFragmentToEventDetailFragment(obj.id)
        view.findNavController().navigate(action)
    }

    fun isTablet(): Boolean {
        return ((this.getResources().getConfiguration().screenLayout
                and Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE)
    }
}