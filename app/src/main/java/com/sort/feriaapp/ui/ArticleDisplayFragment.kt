package com.sort.feriaapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.sort.feriaapp.BuildConfig
import com.sort.feriaapp.databinding.FragmentArticleDisplayBinding

class ArticleDisplayFragment : Fragment(), YouTubePlayer.OnInitializedListener {

    private var _binding : FragmentArticleDisplayBinding? = null
    private val binding get() = _binding!!
    private lateinit var youTubePlayerFragment: YouTubePlayerFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArticleDisplayBinding.inflate(inflater, container, false)
        //binding.articleInfo = ArticleWithEventsAndSocialMedia()
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
        if(wasRestored)
            player.play()
        else {
            player.cueVideo("Zpqn8fo8v7k")
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, result: YouTubeInitializationResult?) {
        Toast.makeText(activity, "Error al reproducir el vídeo!", Toast.LENGTH_LONG).show()
    }


}