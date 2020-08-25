package com.sort.feriaapp.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sort.feriaapp.ui.fragments.CarouselFragment

class CarouselAdapter(fragment: Fragment, private val itemsCount: Int, private val imageList: List<String>): FragmentStateAdapter(fragment) {

    override fun getItemCount() = itemsCount

    override fun createFragment(position: Int): Fragment = CarouselFragment.newInstance(position, imageList[position])

}