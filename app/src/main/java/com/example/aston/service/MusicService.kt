package com.example.aston.service

import android.app.Service
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.net.Uri
import android.os.Binder
import android.os.IBinder
import com.example.aston.R

const val RES_PREFIX = "android.resource://com.example.aston/raw/"

class MusicService : Service() {
	private var player: MediaPlayer? = null
	private val binder = LocalBinder()
	private var currentTime = 0
	private var songs: IntArray = intArrayOf(R.raw.song1, R.raw.song2, R.raw.song3)
	private var position = 0

	var isStartedMusic = false

	inner class LocalBinder : Binder() {
		fun getService(): MusicService = this@MusicService
	}

	override fun onBind(intent: Intent): IBinder {
		player = MediaPlayer.create(this, songs[position])
		player?.setOnCompletionListener { nextMusic() }
		return binder
	}

	override fun unbindService(conn: ServiceConnection) {
		super.unbindService(conn)
		player?.release()
		player = null
	}

	fun startMusic() {
		player?.seekTo(currentTime)
		player?.start()
	}

	fun stopMusic() {
		currentTime = player?.currentPosition ?: 0
		player?.pause()
	}

	fun nextMusic() {
		initValuesBeforeSwitches()
		if (position == songs.size - 1) {
			position = 0
		} else {
			position++
		}

		switchSong()
	}

	fun previousMusic() {
		initValuesBeforeSwitches()
		if (position == 0) {
			position = songs.size - 1
		} else {
			position--
		}
		switchSong()
	}

	private fun initValuesBeforeSwitches() {
		isStartedMusic = true
		currentTime = 0
	}

	private fun switchSong() {
		val uri = Uri.parse(RES_PREFIX + songs[position])
		player?.stop()
		player?.reset()
		player?.setDataSource(applicationContext, uri)
		player?.prepare()
		currentTime = player?.currentPosition ?: 0
		startMusic()
	}

}