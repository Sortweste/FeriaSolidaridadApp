package com.sort.feriaapp.ui.fragments

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sort.feriaapp.adapters.RecyclerViewAdapter
import com.sort.feriaapp.data.Institution
import com.sort.feriaapp.databinding.FragmentHomeBinding
import com.sort.feriaapp.interfaces.RecyclerViewClickListener
import com.sort.feriaapp.viewmodels.InstitutionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), RecyclerViewClickListener<Institution>{

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: InstitutionViewModel by viewModels()
    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.institutionViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()
    }

    private fun initRecyclerView(){
        adapter = RecyclerViewAdapter(this)
        binding.recyclerViewArticle.also {
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
                    it.layoutManager = GridLayoutManager(requireContext(), 3    )
                }
            }
            it.adapter = adapter
        }
    }

    private fun initObservers(){
        lifecycleScope.launchWhenCreated {
            viewModel.institutions.observe(viewLifecycleOwner, Observer{
                adapter.setData(it)
            })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onCardViewClick(view: View, obj: Institution) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToInstitutionDetailFragment(
                obj.id
            )
        view.findNavController().navigate(action)
    }

    fun isTablet(): Boolean {
        return ((this.getResources().getConfiguration().screenLayout
                and Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE)
    }

}