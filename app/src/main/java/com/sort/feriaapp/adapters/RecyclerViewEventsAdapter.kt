package com.sort.feriaapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sort.feriaapp.R
import com.sort.feriaapp.data.Event
import com.sort.feriaapp.data.Institution
import com.sort.feriaapp.data.minimals.EventMinimal
import com.sort.feriaapp.databinding.CardViewEventsBinding
import com.sort.feriaapp.helpers.BindAdapter
import com.sort.feriaapp.helpers.RecyclerViewClickListener

class RecyclerViewEventsAdapter(private val listener: RecyclerViewClickListener<EventMinimal>): RecyclerView.Adapter<RecyclerViewEventsAdapter.ViewHolder>(), BindAdapter<EventMinimal> {

    private var items: List<EventMinimal> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewEventsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding: CardViewEventsBinding = DataBindingUtil.inflate(view, R.layout.card_view_events,parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewEventsAdapter.ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: CardViewEventsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: EventMinimal){
            binding.event = item
            binding.executePendingBindings()
            binding.root.setOnClickListener { listener.onCardViewClick(it, item) }
        }
    }

    override fun setData(items: List<EventMinimal>?) {
        if(!items.isNullOrEmpty()) {
            this.items = items
            notifyDataSetChanged()
        }
    }

}