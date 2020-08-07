package com.sort.feriaapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sort.feriaapp.R
import com.sort.feriaapp.databinding.FragmentEventDetailBinding
import com.sort.feriaapp.utils.AlertDialog
import com.sort.feriaapp.utils.InjectorUtils
import com.sort.feriaapp.viewmodels.EventDetailViewModel

class EventDetailFragment : Fragment(){

    private val args: EventDetailFragmentArgs by navArgs()

    private var _binding : FragmentEventDetailBinding? = null
    private val binding get() = _binding!!

    private val eventDetailViewModel: EventDetailViewModel by viewModels {
        InjectorUtils.provideEventDetailViewModelFactory(this.requireActivity(), args.eventId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventDetailBinding.inflate(inflater, container, false)
        binding.viewmodel = eventDetailViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        binding.confirmCheckbox.setOnCheckedChangeListener { _, b ->
            if(b) validateLogin()
            else  Toast.makeText(context, "No asistir", Toast.LENGTH_LONG).show()
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = EventDetailFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun validateLogin(){
        AlertDialog.display(requireContext(), resources.getString(R.string.login), resources.getString(R.string.login_message), resources.getString(R.string.OK))
        //Toast.makeText(context, "Asistencia Confirmada", Toast.LENGTH_LONG).show()
    }

}