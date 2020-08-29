package com.sort.feriaapp.ui.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
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



    @SuppressLint("ClickableViewAccessibility", "NewApi")
    private fun showDialog(obj: InstagramMinimal){
        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.instagram_dialog)

        val imageView = dialog.findViewById<ImageView>(R.id.dialog_image)
        //Glide.with(imageView).load(Uri.parse(obj.imageURL)).dontAnimate().error(R.drawable.logo_oficial).priority(Priority.HIGH).into(imageView)


        Glide.with(requireContext()).asBitmap().load(Uri.parse(obj.imageURL)).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).centerCrop().placeholder(R.drawable.white).dontAnimate().into(imageView)

        //Glide.with(requireContext()).load(Uri.parse(obj.imageURL)).onlyRetrieveFromCache(true).centerCrop().error(R.drawable.white).into(imageView)

        /*Glide.with(imageView).load(Uri.parse(obj.imageURL)).listener(object : RequestListener<Drawable>{
            override fun onLoadFailed(
                e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                //dialog.findViewById<ProgressBar>(R.id.progress_dialog).visibility = View.GONE
                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                dialog.findViewById<ProgressBar>(R.id.progress_dialog).visibility = View.GONE
                imageView.setImageDrawable(resource)
                return false
            }
        }).into(imageView)*/

        dialog.findViewById<TextView>(R.id.dialog_description).text = obj.description

        dialog.create()
        dialog.window?.attributes?.windowAnimations = R.style.DialogScale
        dialog.show()
    }


    override fun onImageTouch(v: View, obj: InstagramMinimal) {
        showDialog(obj)

    }

}