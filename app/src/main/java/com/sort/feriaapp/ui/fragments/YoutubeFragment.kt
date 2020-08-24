package com.sort.feriaapp.ui.fragments

import android.widget.Toast
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import com.sort.feriaapp.BuildConfig

class YoutubeFragment : YouTubePlayerFragment(), YouTubePlayer.OnInitializedListener  {

    private var videoURL: String? = null

    init {
       // initialize(BuildConfig.YOUTUBE_API_KEY, this)
    }

    fun newInstance(videoURL: String) =
        YoutubeFragment().apply {
            this.videoURL = videoURL
        }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {
        if(player == null) return
        if(wasRestored) player.play()
        else {
            player.cueVideo(videoURL)
            player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?, result: YouTubeInitializationResult?) {
        Toast.makeText(activity, "Error al reproducir el vídeo!", Toast.LENGTH_LONG).show()
    }

}
