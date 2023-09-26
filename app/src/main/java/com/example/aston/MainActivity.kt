package com.example.aston

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.example.aston.databinding.ActivityMainBinding
import com.example.aston.view.DrumView
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity(), ImageProvider {
	private lateinit var binding: ActivityMainBinding

	@SuppressLint("ClickableViewAccessibility")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		val view = binding.root
		setContentView(view)

		binding.drumView.setOnTouchListener { view, event ->
			if (event.action == MotionEvent.ACTION_UP) {
				(view as DrumView).animateRect()
			}
			true
		}
		binding.drumView.setOnImageProvider(this)
		binding.button.setOnClickListener {
			binding.drumView.clearText()
			binding.imageView.setImageDrawable(null)
		}
	}

	override fun setImage(url: String?) {
		if (!url.isNullOrEmpty()) {
			Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(binding.imageView)
		} else {
			binding.imageView.setImageDrawable(null)
		}
	}
}