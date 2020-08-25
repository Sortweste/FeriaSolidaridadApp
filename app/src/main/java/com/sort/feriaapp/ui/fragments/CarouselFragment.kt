package com.sort.feriaapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sort.feriaapp.databinding.FragmentCarouselBinding
import com.sort.feriaapp.utils.bindImageUrl

private const val ARG_POSITION = "ARG_POSITION"
private const val ARG_URL = "ARG_URL"

class CarouselFragment : Fragment() {

    private var position: Int? = null
    private var resource: String? = null

    private var _binding: FragmentCarouselBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_POSITION)
            resource = it.getString(ARG_URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCarouselBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindImageUrl(binding.carouselImage, resource)
    }

    companion object {
        @JvmStatic
        fun newInstance(position: Int, resource: String) = CarouselFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_POSITION, position)
                putString(ARG_URL, resource)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}