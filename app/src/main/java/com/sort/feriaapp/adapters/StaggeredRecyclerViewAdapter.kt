package com.sort.feriaapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sort.feriaapp.R
import com.sort.feriaapp.databinding.CardViewBooksBinding
import com.sort.feriaapp.interfaces.BindAdapter

class StaggeredRecyclerViewAdapter(): RecyclerView.Adapter<StaggeredRecyclerViewAdapter.ViewHolder>(),
    BindAdapter<String> {

    private var items: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaggeredRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding: CardViewBooksBinding = DataBindingUtil.inflate(view, R.layout.card_view_books,parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StaggeredRecyclerViewAdapter.ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: CardViewBooksBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: String){
            binding.image = item
            binding.executePendingBindings()
        }
    }

    override fun setData(items: List<String>?) {
        if(!items.isNullOrEmpty()) {
            this.items = items
            notifyDataSetChanged()
        }
    }

}