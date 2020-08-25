package com.sort.feriaapp.ui.fragments

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sort.feriaapp.R
import com.sort.feriaapp.adapters.RecyclerViewNewsAdapter
import com.sort.feriaapp.data.minimals.NewsMinimal
import com.sort.feriaapp.databinding.FragmentNewsBinding
import com.sort.feriaapp.interfaces.RecyclerViewClickListener
import com.sort.feriaapp.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment(), RecyclerViewClickListener<NewsMinimal> {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!


    private val viewModel: NewsViewModel by viewModels()


    private lateinit var adapter: RecyclerViewNewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        binding.newsViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()
    }

    private fun initRecyclerView(){
        adapter = RecyclerViewNewsAdapter(this)
        binding.recyclerViewNews.also {
            it.setHasFixedSize(true)
            if(it.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
                it.layoutManager = GridLayoutManager(requireContext(), 1)
            } else{
                if(isTablet()){
                    it.layoutManager = GridLayoutManager(requireContext(), 2)
                }else{
                    it.layoutManager = GridLayoutManager(requireContext(), 1)
                }
            }
            it.adapter = adapter
        }
    }

    private fun initObservers(){
        lifecycleScope.launchWhenCreated {
            viewModel.news.observe(viewLifecycleOwner, Observer{
                adapter.setData(it)
            })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            NewsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun isTablet(): Boolean {
        return ((this.getResources().getConfiguration().screenLayout
                and Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE)
    }

    override fun onCardViewClick(view: View, obj: NewsMinimal) {
        val action = NewsFragmentDirections.actionNewsFragmentToNewsDetailFragment(obj.id)
        view.findNavController().navigate(action)
    }
}