package com.example.aston.data

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.example.aston.R

enum class Color(@ColorRes val colorRes: Int, @StringRes val text: Int?, val image: String?) {
	RED(R.color.red, R.string.red_color, null),
	YELLOW(R.color.yellow, R.string.yellow_color, null),
	BLUE(R.color.blue, null, "https://picsum.photos/id/221/200/300"),
	LIGHT_BLUE(R.color.light_blue, R.string.light_blue_color, null),
	GREEN(R.color.green, null, "https://picsum.photos/id/237/200/300"),
	ORANGE(R.color.orange, null, "https://picsum.photos/id/7/200/300"),
	PURPLE(R.color.purple, R.string.purple_color, null)
}