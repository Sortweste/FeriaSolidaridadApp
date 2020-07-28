package com.sort.feriaapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sort.feriaapp.R
import com.sort.feriaapp.adapters.RVAdaptadorCustom
import com.sort.feriaapp.data.*
import com.sort.feriaapp.databinding.FragmentHomeBinding
import com.sort.feriaapp.helpers.RVArticlesClickListener
import com.sort.feriaapp.viewmodels.ArticleViewModel
import kotlinx.android.synthetic.main.fragment_home.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    //private val viewModel = ArticleViewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    var lista:RecyclerView? = null
    var adaptador: RVAdaptadorCustom? = null
    var layoutManager:RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val events = ArrayList<Event>()
        val socialMedia = ArrayList<SocialMedia>()
        val articles = ArrayList<Article>()

        lista = view.RVArticle
        lista?.setHasFixedSize(true)

        layoutManager = GridLayoutManager(view.context,2)
        lista?.layoutManager =layoutManager

        adaptador = RVAdaptadorCustom(articles,
            object : RVArticlesClickListener {
                override fun OnClick(Vista: View, index: Int) {
                    //rvarticle = template_recyclerview_article_show.newInstance(articles.get(index).title, articles.get(index).description,articles.get(index).institution,articles.get(index).foto,articles.get(index).video,articles.get(index).meeting_url,articles.get(index).type,articles.get(index).homepage)
                    rvarticle = template_recyclerview_article_show.newInstance(articles.get(index))
                    setCurrentFragment(rvarticle).addToBackStack(null)

                }

            })

        lista?.adapter = adaptador


        return view
    }
    fun setCurrentFragment(fragment: Fragment) =  requireActivity().supportFragmentManager.beginTransaction().apply {
        replace(R.id.fragment, fragment)
        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        commit()
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
}