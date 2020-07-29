package com.sort.feriaapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sort.feriaapp.R
import com.sort.feriaapp.data.Article
import com.sort.feriaapp.helpers.RecyclerViewClickListener

class RVAdaptadorCustom(items:ArrayList<Article>, var listener: RecyclerViewClickListener):RecyclerView.Adapter<RVAdaptadorCustom.ViewHolder>() {

    var items: ArrayList<Article>? = null
    init {
        this.items = items
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista = LayoutInflater.from(parent?.context).inflate(R.layout.card_view_articles,parent,false)
        val viewHolder = ViewHolder(
            vista,
            listener
        )

        return viewHolder
    }

    override fun getItemCount(): Int {
        return items?.count()!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.foto?.setImageResource(item?.foto!!)
        holder.title?.text = item?.title
        holder.institution?.text = item?.institution
        holder.description?.text = item?.description
    }

    class ViewHolder(vista:View, listener: RecyclerViewClickListener): RecyclerView.ViewHolder(vista), View.OnClickListener{
        var vista = vista
        var foto:ImageView? = null
        var title:TextView? = null
        var institution:TextView? = null
        var description:TextView?= null
        var listener: RecyclerViewClickListener? = null

        init {
            foto = vista.findViewById(R.id.IMGarticle)
            title= vista.findViewById(R.id.TVtitle_show)
            institution= vista.findViewById(R.id.TVinstitution_show)
            description= vista.findViewById(R.id.TVdescription)
            this.listener = listener
            vista.setOnClickListener(this)
        }

        override fun onClick(vi: View?) {
            this.listener?.OnClick(vi!!,adapterPosition)
        }
    }
}