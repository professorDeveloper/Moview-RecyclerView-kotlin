package com.azmovhudstc.movieapprecycleview

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.azmovhudstc.movieapprecycleview.R
import kotlinx.android.synthetic.main.activity_movie_about.*

class MovieAboutActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_about)
        val stringExtra = intent.getStringExtra("aboutName")
        val stringExtra1 = intent.getStringExtra("aboutAuthor")
        val stringExtra2 = intent.getStringExtra("aboutDescription")
        val stringExtra3 = intent.getStringExtra("aboutDate")
        aboutAuthors.text = "Movie authors : $stringExtra1"
        aboutNameOne.text = "Movie Name : $stringExtra"
        aboutName.text = stringExtra
        aboutDate.text = "Movie Date : $stringExtra3"
        aboutDescription.text = "About Movie : $stringExtra2"
        close.setOnClickListener {
            finish()
        }


    }
}