package com.azmovhudstc.movieapprecycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import com.azmovhudstc.movieapprecycleview.model.Movie
import com.azmovhudstc.movieapprecycleview.utils.MyMovie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_add_movie.*

class AddMovieActivity : AppCompatActivity() {
    private lateinit var list: ArrayList<Movie>
    private lateinit var gson: Gson
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)


        MyMovie.init(this)
        gson = Gson()
        list= ArrayList()
        var type = object : TypeToken<ArrayList<Movie>>() {}.type


        save.setOnClickListener {
            val toString = movieName.text.trim().toString()
            val toString1 = movieDescription.text.trim().toString()
            val toString2 = movie_authors.text.trim().toString()
            val toString3 = movie_date.text.trim().toString()
            if (toString.isEmpty() || toString1.isEmpty() || toString2.isEmpty() || toString3.isEmpty() ) {
                Toast.makeText(this, "Bo`sh maydonlarni to`ldiring", Toast.LENGTH_SHORT).show()

            }

            else{

                var item = Movie(toString,toString3,toString1,toString2)


                if (MyMovie.user!!.isNotEmpty()) {


                    list = gson.fromJson(MyMovie.user, type)
                    list.add(item)
                } else {
                    list = ArrayList()

                    list.add(item)

                }
                val toJson = gson.toJson(list, type)
                MyMovie.user = toJson.toString()

                //  Snackify.success(findViewById(android.R.id.content), "Success message !", Snackify.LENGTH_LONG).show()

                finish()
            }

        }
    }
}