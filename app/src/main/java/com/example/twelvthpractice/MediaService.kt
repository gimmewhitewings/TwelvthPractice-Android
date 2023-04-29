package com.example.twelvthpractice

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MediaService : Service() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        mediaPlayer = MediaPlayer.create(this, R.raw.queen)
        mediaPlayer.isLooping = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mediaPlayer.start()
        return START_STICKY
    }

    override fun onDestroy() {
        mediaPlayer.stop()
    }
}