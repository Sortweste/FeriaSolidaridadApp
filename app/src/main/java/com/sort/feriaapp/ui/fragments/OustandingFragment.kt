package com.sort.feriaapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.sort.feriaapp.R
import com.sort.feriaapp.databinding.FragmentInstitutionDisplayBinding
import com.sort.feriaapp.databinding.FragmentOustandingBinding
import com.sort.feriaapp.viewmodels.OustandingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OustandingFragment : Fragment() {

    private var _binding: FragmentOustandingBinding? = null
    private val binding get() = _binding!!

    private val oustandingViewModel: OustandingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOustandingBinding.inflate(inflater, container, false)
        binding.viewmodel = oustandingViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = OustandingFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}