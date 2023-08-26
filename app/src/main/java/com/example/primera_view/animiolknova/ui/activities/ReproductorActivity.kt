package com.example.primera_view.animiolknova.ui.activities

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.primera_view.R
import com.example.primera_view.databinding.ActivityReproductorBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer.Builder

class ReproductorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReproductorBinding
    lateinit var player: ExoPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReproductorBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onStart() {
        super.onStart()
        extraerDatos()
    }

    fun extraerDatos() {
        var item = ""
        intent.extras.let {
            item = it?.getString("url").toString()
            if (item != "") {
                cargarVideo(item)
            }
        }
    }

    private fun cargarVideo(item: String) {
        player = Builder(this@ReproductorActivity).build()
        binding.pvVideo.player = player
        val videoUri = Uri.parse(item)
        val mediaItem: MediaItem = MediaItem.fromUri(videoUri)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }

    override fun onPause() {
        super.onPause()
        player.pause()
        finish() // Destruye la Activity cuando se pausa
    }
}