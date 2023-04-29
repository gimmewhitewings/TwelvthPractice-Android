package com.example.twelvthpractice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.twelvthpractice.R
import com.example.twelvthpractice.databinding.FragmentVideoViewBinding


class VideoViewFragment : Fragment() {

    private lateinit var binding: FragmentVideoViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentVideoViewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val packageName = requireContext().packageName // Get the package name of the app.
        binding.apply {
            videoView.setVideoPath("android.resource://${packageName}/${R.raw.treasure_island}")
            playPauseButton.setOnClickListener {
                if (!videoView.isPlaying) {
                    videoView.start()
                    // Change the icon to pause.
                    playPauseButton.setIconResource(R.drawable.baseline_pause_24)
                } else {
                    videoView.pause()
                    // Change the icon to play.
                    playPauseButton.setIconResource(R.drawable.baseline_play_arrow_24)

                }
            }
        }
    }
}