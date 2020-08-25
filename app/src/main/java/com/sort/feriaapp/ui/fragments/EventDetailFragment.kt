package com.sort.feriaapp.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sort.feriaapp.R
import com.sort.feriaapp.databinding.FragmentEventDetailBinding
//import com.sort.feriaapp.ui.EventDetailFragmentArgs
import com.sort.feriaapp.utils.AlertDialog
import com.sort.feriaapp.viewmodels.EventDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventDetailFragment : Fragment(), View.OnClickListener{

    //private val args: EventDetailFragmentArgs by navArgs()

    private var _binding : FragmentEventDetailBinding? = null
    private val binding get() = _binding!!

    private val eventDetailViewModel: EventDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.findItem(R.id.about).isVisible = false
        /*menu.findItem(R.id.login_in).isVisible = false
        menu.findItem(R.id.log_out).isVisible = false*/
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventDetailBinding.inflate(inflater, container, false)
        binding.viewmodel = eventDetailViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initToolBar()

        binding.confirmCheckbox.setOnCheckedChangeListener { _, b ->
            if(b) validateLogin()
            else  Toast.makeText(context, "No asistir", Toast.LENGTH_LONG).show()
        }

        initListeners()

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = EventDetailFragment()
    }

    private fun initToolBar(){
        (activity as AppCompatActivity).apply {
            this.setSupportActionBar(binding.toolbar)
            this.supportActionBar?.also {
                it.setDisplayHomeAsUpEnabled(true)
                it.setDisplayShowHomeEnabled(true)
            }
        }
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initListeners(){
        binding.meetupView.setOnClickListener(this)
        binding.emailView.setOnClickListener(this)
        binding.formView.setOnClickListener(this)
    }

    private fun validateLogin(){
        AlertDialog.display(requireContext(), resources.getString(R.string.login), resources.getString(R.string.login_message), resources.getString(R.string.OK))
        //Toast.makeText(context, "Asistencia Confirmada", Toast.LENGTH_LONG).show()
    }

    private fun prepareIntent(url: String){
        val i = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(i)
    }

    private fun prepareIntentEmail(url: String){
        val i = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",url,null))
        startActivity(i)
    }

    override fun onClick(v: View?) {
            when(v?.id){
                binding.meetupView.id -> { prepareIntent(binding.meetupView.contentDescription.toString()) }
                binding.formView.id -> { prepareIntent(binding.formView.contentDescription.toString()) }

                binding.emailView.id -> { prepareIntentEmail(binding.emailView.contentDescription.toString()) }
            }
    }

}