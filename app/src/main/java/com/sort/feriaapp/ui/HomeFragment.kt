package com.sort.feriaapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.sort.feriaapp.adapters.RecyclerViewAdapter
import com.sort.feriaapp.data.Article
import com.sort.feriaapp.databinding.FragmentHomeBinding
import com.sort.feriaapp.helpers.RecyclerViewClickListener
import com.sort.feriaapp.utils.InjectorUtils
import com.sort.feriaapp.viewmodels.ArticleViewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment(), RecyclerViewClickListener<Article>{
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArticleViewModel by viewModels{
        InjectorUtils.provideArticleViewModelFactory(this)
    }

    private lateinit var adapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.articleViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()
    }

    private fun initRecyclerView(){
        adapter = RecyclerViewAdapter(this,this)
        binding.recyclerViewArticle.also {
            it.setHasFixedSize(true)
            it.layoutManager = GridLayoutManager(requireContext(), 2)
            it.adapter = adapter
        }
    }

    private fun initObservers(){
        lifecycleScope.launchWhenCreated {
            viewModel.articles.observe(viewLifecycleOwner, Observer{

            })
        }
    }

    private fun startNewArticleFragment(){

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCardViewClick(view: View, obj: Article) {
        startNewArticleFragment()
    }

}