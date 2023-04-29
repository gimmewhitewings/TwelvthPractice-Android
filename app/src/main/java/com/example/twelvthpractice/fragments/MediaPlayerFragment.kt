package com.example.twelvthpractice.fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.example.twelvthpractice.R
import com.example.twelvthpractice.databinding.FragmentMediaPlayerBinding


class MediaPlayerFragment : Fragment() {


    private lateinit var binding: FragmentMediaPlayerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMediaPlayerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mediaPlayer = MediaPlayer.create(context, R.raw.queen)
        binding.apply {

            // SeekBar configuration
            // Set the max value of the seek bar to the duration of the audio file.
            audioSeekBar.max = mediaPlayer.duration
            audioSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (fromUser) mediaPlayer.seekTo(progress)
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })

            // Play or pause
            playPauseAudioButton.setOnClickListener {
                if (!mediaPlayer.isPlaying) {
                    mediaPlayer.start()
                    playPauseAudioButton.setIconResource(R.drawable.baseline_pause_24)
                } else {
                    mediaPlayer.pause()
                    playPauseAudioButton.setIconResource(R.drawable.baseline_play_arrow_24)
                }
            }

            // Rewind button
            rewindButton.setOnClickListener {
                // Rewind the audio by 5 seconds.
                val newPosition = mediaPlayer.currentPosition - 5000
                mediaPlayer.seekTo(newPosition)
                audioSeekBar.progress = newPosition
            }

            // Froward button
            forwardButton.setOnClickListener {
                // Forward the audio by 5 seconds.
                val newPosition = mediaPlayer.currentPosition + 5000
                mediaPlayer.seekTo(newPosition)
                audioSeekBar.progress = newPosition
            }
        }
    }


}