package com.sort.feriaapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sort.feriaapp.R
import com.sort.feriaapp.data.minimals.NewsMinimal
import com.sort.feriaapp.databinding.CardViewNewsBinding
import com.sort.feriaapp.interfaces.BindAdapter
import com.sort.feriaapp.interfaces.RecyclerViewClickListener

class RecyclerViewNewsAdapter(private val listener: RecyclerViewClickListener<NewsMinimal>): RecyclerView.Adapter<RecyclerViewNewsAdapter.ViewHolder>(),
    BindAdapter<NewsMinimal> {

    private var items: List<NewsMinimal> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewNewsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding: CardViewNewsBinding = DataBindingUtil.inflate(view, R.layout.card_view_news,parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewNewsAdapter.ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: CardViewNewsBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: NewsMinimal){
            binding.news = item
            binding.executePendingBindings()
            binding.root.setOnClickListener { listener.onCardViewClick(it, item) }
        }
    }

    override fun setData(items: List<NewsMinimal>?) {
        if(!items.isNullOrEmpty()) {
            this.items = items
            notifyDataSetChanged()
        }
    }


}