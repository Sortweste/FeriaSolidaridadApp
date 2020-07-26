package com.sort.feriaapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.sort.feriaapp.R
import com.sort.feriaapp.data.Events

class LVeventosAdaptador(private val context: Context,
                         private val eventos: ArrayList<Events>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getView(position: Int, vista: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.template_listview_eventos, parent, false)
        val TVtitle = rowView.findViewById<TextView>(R.id.TVtitle_event)
        val TVlink = rowView.findViewById<TextView>(R.id.TVlink_event)
        val foto = rowView.findViewById<ImageView>(R.id.IMGevent)

        val evento = getItem(position)

        TVtitle.text = evento.title
        TVlink.text = evento.link
        foto.setImageResource(evento?.foto!!)

        return rowView
    }

    override fun getItem(position: Int): Events {
        return eventos.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return eventos.size
    }
}