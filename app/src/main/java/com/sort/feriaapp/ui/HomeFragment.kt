package com.sort.feriaapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sort.feriaapp.R
import com.sort.feriaapp.data.*
import com.sort.feriaapp.databinding.FragmentHomeBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    lateinit var rvarticle: template_recyclerview_article_show

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
    var adaptador:RVAdaptadorCustom? = null
    var layoutManager:RecyclerView.LayoutManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val evento = ArrayList<Events>()
        evento.add(Events("UCA",R.drawable.ic_action_image,"www.API.org"))
        evento.add(Events("UCA2",R.drawable.ic_action_image,"www.API2.org"))

        val socialMedia = ArrayList<SocialMedia>()
        socialMedia.add(SocialMedia("prueba facebook","www/someurlforfacebook.com","facebook") )
        socialMedia.add(SocialMedia("prueba twitter","www/someurlfortwitter.com","twitter"))
        socialMedia.add(SocialMedia("prueba instagram","www/someurlforinstagram.com","instagram"))


        val articles = ArrayList<Article>()
        articles.add(Article(1,R.drawable.ic_action_image,"Evento","UCA","Un evento en la uca para personas que estudiantes en la uca",R.drawable.ic_action_video,
            "www.api.org",evento,"fun","www.API.org",socialMedia
        ))
        articles.add(Article(2,R.drawable.ic_action_image,"Evento2","UCA 2","Un evento en la uca 2 para personas que estudiantes en la uca 2",R.drawable.ic_action_video,
            "www.api.org",evento,"fun","www.API.org",socialMedia
        ))
        articles.add(Article(3,R.drawable.ic_action_image,"Evento3","UCA 3","Un evento en la uca 3 para personas que estudiantes en la uca 3",R.drawable.ic_action_video,
            "www.api.org",evento,"fun","www.API.org",socialMedia
        ))
        //Log.d("debug red social", socialMedia.get(1).name);
        lista = view.findViewById(R.id.RVArticle)
        lista?.setHasFixedSize(true)

        layoutManager = GridLayoutManager(view.context,2)
        lista?.layoutManager =layoutManager

        adaptador = RVAdaptadorCustom(articles,object:RVArticlesClickListener{
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