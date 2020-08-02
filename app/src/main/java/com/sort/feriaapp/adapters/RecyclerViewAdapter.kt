package com.sort.feriaapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sort.feriaapp.data.Institution
import com.sort.feriaapp.helpers.BindAdapter
import com.sort.feriaapp.helpers.RecyclerViewClickListener

import com.sort.feriaapp.R
import com.sort.feriaapp.databinding.CardViewInstitutionsBinding

class RecyclerViewAdapter(private val listener: RecyclerViewClickListener<Institution>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(), BindAdapter<Institution> {

    private var items: List<Institution> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding: CardViewInstitutionsBinding = DataBindingUtil.inflate(view, R.layout.card_view_institutions,parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: CardViewInstitutionsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Institution){
            binding.article = item
            binding.executePendingBindings()
            binding.root.setOnClickListener { listener.onCardViewClick(it, item) }
        }
    }

    override fun setData(items: List<Institution>?) {
        if(!items.isNullOrEmpty()) {
            this.items = items
            notifyDataSetChanged()
        }
    }

}