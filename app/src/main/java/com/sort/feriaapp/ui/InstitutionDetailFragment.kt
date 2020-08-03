package com.sort.feriaapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.sort.feriaapp.BuildConfig
import com.sort.feriaapp.databinding.FragmentInstitutionDisplayBinding
import com.sort.feriaapp.utils.InjectorUtils
import com.sort.feriaapp.viewmodels.InstitutionDetailViewModel


class InstitutionDetailFragment : Fragment(), YouTubePlayer.OnInitializedListener {

    private var _binding : FragmentInstitutionDisplayBinding? = null
    private val binding get() = _binding!!
    private lateinit var youTubePlayerFragment: YouTubePlayerFragment

    private val institutionDetailViewModel: InstitutionDetailViewModel by viewModels {
        InjectorUtils.provideInstitutionDetailVieModelFactory(this.requireActivity(), args.institutionId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInstitutionDisplayBinding.inflate(inflater, container, false)
        binding.institutionInfo = institutionDetailViewModel.institutionInfo
        youTubePlayerFragment = binding.youtubeFragment
        youTubePlayerFragment.initialize(BuildConfig.YOUTUBE_API_KEY, this)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {
        if(player == null) return
        if(wasRestored) player.play()
        else {
            player.cueVideo(binding.institutionInfo.institution.videoURL)
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, result: YouTubeInitializationResult?) {
        Toast.makeText(activity, "Error al reproducir el vídeo!", Toast.LENGTH_LONG).show()
    }


}