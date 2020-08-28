package com.sort.feriaapp.ui.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.sort.feriaapp.R
import com.sort.feriaapp.adapters.StaggeredRecyclerViewAdapter
import com.sort.feriaapp.data.minimals.InstagramMinimal
import com.sort.feriaapp.databinding.FragmentProfileBinding
import com.sort.feriaapp.interfaces.RecyclerViewOnTouchListener
import com.sort.feriaapp.utils.bindImageUrl
import com.sort.feriaapp.viewmodels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(), RecyclerViewOnTouchListener<InstagramMinimal> {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var adapter: StaggeredRecyclerViewAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentProfileBinding.inflate(inflater, container, false)
        binding.profileViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        adapter = StaggeredRecyclerViewAdapter(this)
        val layoutManager: GridLayoutManager = GridLayoutManager(requireContext(), 3)
        /*layoutManager.spanSizeLookup = object: GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int = if(position % 3 == 0) 2 else 1
        }*/

        binding.recyclerViewBook.also {
            it.setHasFixedSize(true)
            it.layoutManager = layoutManager
            it.adapter = adapter
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    @SuppressLint("ClickableViewAccessibility")
    private fun showDialog(obj: InstagramMinimal){
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.instagram_dialog)

        val imageView = dialog.findViewById<ImageView>(R.id.dialog_image)
        Glide.with(imageView).load(Uri.parse(obj.imageURL)).placeholder(R.drawable.slider).error(R.drawable.slider).priority(Priority.HIGH).into(imageView)

        dialog.findViewById<TextView>(R.id.dialog_description).text = obj.description
        /*imageView.setOnTouchListener{_, motionEvent ->
            when(motionEvent.action){
                MotionEvent.ACTION_UP -> { dialog.dismiss() }
            }
            true
        }*/

        dialog.show()
    }


    override fun onImageTouch(v: View, obj: InstagramMinimal) {
        showDialog(obj)
    }

    /*override fun onImageTouch(flag: Boolean, obj: InstagramMinimal, motionEvent: MotionEvent) {
        val dialog = showDialog(obj)
        when(motionEvent.action){
            MotionEvent.ACTION_BUTTON_PRESS -> {dialog.show()}
            MotionEvent.ACTION_BUTTON_RELEASE -> { dialog.dismiss() }
        }
    }*/

}