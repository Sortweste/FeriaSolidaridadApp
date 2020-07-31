package com.sort.feriaapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sort.feriaapp.data.Article
import com.sort.feriaapp.databinding.CardViewArticlesBinding
import com.sort.feriaapp.helpers.BindAdapter
import com.sort.feriaapp.helpers.RecyclerViewClickListener

class RecyclerViewAdapter(private val listener: RecyclerViewClickListener<Article>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(), BindAdapter<Article> {

    private var items: List<Article> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: CardViewArticlesBinding = DataBindingUtil.inflate(inflater, viewType, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: CardViewArticlesBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Article){
            binding.article = item
            binding.executePendingBindings()
            binding.root.setOnClickListener { listener.onCardViewClick(it, item) }
        }
    }

    override fun setData(items: List<Article>?) {
        if(!items.isNullOrEmpty()) {
            this.items = items
            notifyDataSetChanged()
        }
    }

}