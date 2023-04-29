package com.example.twelvthpractice.fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.example.twelvthpractice.R
import com.example.twelvthpractice.databinding.FragmentTweenAnimationBinding


class TweenAnimationFragment : Fragment() {

    private lateinit var binding: FragmentTweenAnimationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTweenAnimationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Load the animation from the xml file.
        val animation = AnimationUtils.loadAnimation(context, R.anim.tween_animation)
        val mediaPlayer = MediaPlayer.create(context, R.raw.button_pressed)
        // Set the duration of the animation to the duration of the audio file.
        animation.duration = mediaPlayer.duration.toLong()
        binding.apply {
            animatedButton.setOnClickListener {
                it.startAnimation(animation) // Start the animation.
                mediaPlayer.start() // Play the audio file.
            }
        }
    }
}