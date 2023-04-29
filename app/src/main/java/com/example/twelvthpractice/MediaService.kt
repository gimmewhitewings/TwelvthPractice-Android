package com.example.twelvthpractice

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MediaService : Service() {

    // MediaPlayer is used to play audio and video files.
    private lateinit var mediaPlayer: MediaPlayer

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        // Create MediaPlayer object, and set the audio file to play.
        mediaPlayer = MediaPlayer.create(this, R.raw.queen)
        // Set looping to true, so that the audio file will be played repeatedly.
        mediaPlayer.isLooping = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Start playing the audio file.
        mediaPlayer.start()
        // Return START_STICKY, so that if the system kills the service, it will be restarted.
        return START_STICKY
    }

    override fun onDestroy() {
        // Stop playing the audio file.
        mediaPlayer.stop()
    }
}