package com.sort.feriaapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.sort.feriaapp.databinding.FragmentProfileBinding
import com.sort.feriaapp.storage.SharedPreferencesManager
import com.sort.feriaapp.ui.activities.LoginActivity
import com.sort.feriaapp.ui.activities.MainActivity
import com.sort.feriaapp.utils.TOKEN_KEY
import com.sort.feriaapp.viewmodels.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()

    @Inject
    lateinit var mSharedPreferences: SharedPreferencesManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentProfileBinding.inflate(inflater, container, false)
        binding.profileViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.bottomLoginOut.setOnClickListener(this)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun startIntent(){
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
    }

    override fun onClick(v: View?) {
        mSharedPreferences.clearData(TOKEN_KEY)
        startIntent()
    }


}