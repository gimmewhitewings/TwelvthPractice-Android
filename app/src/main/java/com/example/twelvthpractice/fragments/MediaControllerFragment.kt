package com.example.twelvthpractice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.fragment.app.Fragment
import com.example.twelvthpractice.databinding.FragmentMediaControllerBinding


class MediaControllerFragment : Fragment() {

    private lateinit var binding: FragmentMediaControllerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMediaControllerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mediaController = MediaController(context) // Create a media controller
        val videoPath =
            "https://static.videezy.com/system/resources/previews/000/036/605/original/18_010_05.mp4"
        binding.apply {
            videoView.setVideoPath(videoPath)
            videoView.start()
            videoView.setMediaController(mediaController)
            mediaController.setMediaPlayer(videoView)
        }
    }
}