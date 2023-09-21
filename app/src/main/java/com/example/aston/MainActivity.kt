package com.example.aston

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.content.res.AppCompatResources
import com.example.aston.databinding.ActivityMainBinding
import com.example.aston.service.MusicService

class MainActivity : AppCompatActivity() {
	private lateinit var binding: ActivityMainBinding
	private lateinit var musicService: MusicService
	private var isBound: Boolean = false

	private val connection = object : ServiceConnection {

		override fun onServiceConnected(className: ComponentName, service: IBinder) {
			val binder = service as MusicService.LocalBinder
			musicService = binder.getService()
			isBound = true
		}

		override fun onServiceDisconnected(arg0: ComponentName) {
			isBound = false
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		val view = binding.root
		setContentView(view)

		binding.btnNext.setOnClickListener {
			binding.btnPlay.setImageDrawable(
				AppCompatResources.getDrawable(
					this,
					R.drawable.baseline_pause_24
				)
			)
			musicService.nextMusic()
		}

		binding.btnPrev.setOnClickListener {
			musicService.previousMusic()
			binding.btnPlay.setImageDrawable(
				AppCompatResources.getDrawable(
					this,
					R.drawable.baseline_pause_24
				)
			)
		}

		binding.btnPlay.setOnClickListener {
			startOrStopMusic()
		}

	}

	override fun onStart() {
		super.onStart()
		Intent(this, MusicService::class.java).also { intent ->
			bindService(intent, connection, Context.BIND_AUTO_CREATE)
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		unbindService(connection)
		isBound = false
	}

	private fun startOrStopMusic() = with(binding) {
		musicService.isStartedMusic = !musicService.isStartedMusic
		if (musicService.isStartedMusic) {
			musicService.startMusic()
			btnPlay.setImageDrawable(
				AppCompatResources.getDrawable(
					this@MainActivity,
					R.drawable.baseline_pause_24
				)
			)
		} else {
			musicService.stopMusic()
			btnPlay.setImageDrawable(
				AppCompatResources.getDrawable(
					this@MainActivity,
					R.drawable.baseline_play_arrow_24
				)
			)
		}
	}
}