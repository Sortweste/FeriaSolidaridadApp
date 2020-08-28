package com.sort.feriaapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sort.feriaapp.R
import com.sort.feriaapp.data.minimals.InstagramMinimal
import com.sort.feriaapp.databinding.CardViewBooksBinding
import com.sort.feriaapp.interfaces.BindAdapter
import com.sort.feriaapp.interfaces.RecyclerViewOnTouchListener

class StaggeredRecyclerViewAdapter(private val listener: RecyclerViewOnTouchListener<InstagramMinimal>): RecyclerView.Adapter<StaggeredRecyclerViewAdapter.ViewHolder>(),
    BindAdapter<InstagramMinimal>{

    private var items: List<InstagramMinimal> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaggeredRecyclerViewAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding: CardViewBooksBinding = DataBindingUtil.inflate(view, R.layout.card_view_books,parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StaggeredRecyclerViewAdapter.ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(private val binding: CardViewBooksBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: InstagramMinimal){
            binding.instagramMinimal = item
            binding.executePendingBindings()
            //binding.root.setOnLongClickListener {  }
            /*binding.root.setOnTouchListener { view, motionEvent ->
                listener.onImageTouch(view.performClick(), item, motionEvent)
                true
            }*/
            binding.root.setOnLongClickListener {
                listener.onImageTouch(it, item)
                true
            }
        }
    }

    override fun setData(items: List<InstagramMinimal>?) {
        if(!items.isNullOrEmpty()) {
            this.items = items
            notifyDataSetChanged()
        }
    }


}