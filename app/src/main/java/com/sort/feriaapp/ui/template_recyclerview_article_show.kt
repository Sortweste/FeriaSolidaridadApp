package com.sort.feriaapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.sort.feriaapp.R
import com.sort.feriaapp.data.Article
import com.sort.feriaapp.data.Events
import com.sort.feriaapp.data.LVeventosAdaptador

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//val evento = ArrayList<Events>()

/*
private const val ARG_title = "param1"
private const val ARG_description = "param2"
private const val ARG_institution = "param3"
private const val ARG_img = "param4"
private const val ARG_video = "param5"
private const val ARG_meeting = "param6"
//private const val ARG_events = evento
private const val ARG_type = "param8"
private const val ARG_homepage = "param9"
*/

/**
 * A simple [Fragment] subclass.
 * Use the [template_recyclerview_article_show.newInstance] factory method to
 * create an instance of this fragment.
 */
class template_recyclerview_article_show : Fragment() {

    var adapter:LVeventosAdaptador? = null

    /*
    private var title: String? = null
    private var description: String? = null
    private var institution: String? = null
    private var img: Int? = null
    private var video: Int? = null
    private var meeting: String? = null
    //private var LVevents: ArrayList<Events>? = null
    private var type: String? = null
    private var homepage: String? = null
    */
    public var articles:Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            /*
            title = it.getString(ARG_title)
            description = it.getString(ARG_description)
            institution = it.getString(ARG_institution)
            img = it.getInt(ARG_img)
            video = it.getInt(ARG_video)
            meeting = it.getString(ARG_description)
            //LVevents = it.getString(ARG_title)
            description = it.getString(ARG_meeting)
            type = it.getString(ARG_type)
            homepage = it.getString(ARG_homepage)
            */
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate( R.layout.fragment_template_recyclerview_article_show, container, false)

        var TVtitle = view.findViewById<TextView>(R.id.TVtitle_show)
        var TVdescription = view.findViewById<TextView>(R.id.TVdescription_show)
        var TVinstitution = view.findViewById<TextView>(R.id.TVinstitution_show)
        var IMGimg = view.findViewById<ImageView>(R.id.IMGarticle_show)
        var VIDvideo = view.findViewById<ImageView>(R.id.VIDarticle_show)
        var TVmeeting = view.findViewById<TextView>(R.id.TVmeeting_url_show)
        var LVevents = view.findViewById<ListView>(R.id.LVeventos_show)
        var TVtype = view.findViewById<TextView>(R.id.TVtype_show)
        var TVhomepage = view.findViewById<TextView>(R.id.TVhomepage)

        adapter = LVeventosAdaptador(view.context, articles?.events!!)
        LVevents.adapter = adapter

        TVtitle.setText(articles?.title)
        TVdescription.setText(articles?.description)
        TVinstitution.setText(articles?.institution)
        IMGimg.setImageResource(articles?.foto!!)
        VIDvideo.setImageResource(articles?.video!!)
        TVmeeting.setText(articles?.meeting_url)


        TVtype.setText(articles?.type)
        TVhomepage.setText(articles?.homepage)



        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment template_recyclerview_article_show.
         */
        // TODO: Rename and change types and number of parameters
        /*
        @JvmStatic
        fun newInstance(title: String?, description: String?, institution: String?, img: Int, video: Int, meeting: String?, type: String?, homepage: String?) =
            template_recyclerview_article_show().apply {
                arguments = Bundle().apply {

                    putString(ARG_title, title)
                    putString(ARG_description, description)
                    putString(ARG_institution, institution)
                    putInt(ARG_img, img)
                    putInt(ARG_video, video)
                    putString(ARG_meeting, meeting)
                    //putString(ARG_events, param1)
                    putString(ARG_type, type)
                    putString(ARG_homepage, homepage)
                }
            }*/
        @JvmStatic
        fun newInstance(article: Article?) =
            template_recyclerview_article_show().apply {
                arguments = Bundle().apply {
                articles = article
                }
            }


    }
}
