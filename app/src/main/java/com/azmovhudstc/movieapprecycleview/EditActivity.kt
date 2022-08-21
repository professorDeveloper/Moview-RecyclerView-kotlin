package com.azmovhudstc.movieapprecycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.azmovhudstc.movieapprecycleview.model.Movie
import com.azmovhudstc.movieapprecycleview.utils.MyMovie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_add_movie.*
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.android.synthetic.main.item.*

class EditActivity : AppCompatActivity() {
   private lateinit var list:ArrayList<Movie>
   private lateinit var gson: Gson
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        val stringExtra = intent.getStringExtra("name")
        val stringExtra1 = intent.getStringExtra("date")
        val stringExtra2 = intent.getStringExtra("description")
        val stringExtra3= intent.getStringExtra("author")
        val position= intent.getStringExtra("position")
        editName.setText(stringExtra)
        edit_description.setText(stringExtra2)
        edit_author.setText(stringExtra3)
        edit_date.setText(stringExtra1)
        MyMovie.init(this)
        gson = Gson()
        list= ArrayList()
        var type = object : TypeToken<ArrayList<Movie>>() {}.type
        edited.setOnClickListener {
            val toString = editName.text.trim().toString()
            val toString1 = edit_description.text.trim().toString()
            val toString2 = edit_author.text.trim().toString()
            val toString3 = edit_date.text.trim().toString()
            if (toString.isEmpty() || toString1.isEmpty() || toString2.isEmpty() || toString3.isEmpty() ) {
                Toast.makeText(this, "Bo`sh maydonlarni to`ldiring", Toast.LENGTH_SHORT).show()
            }

            else{

                var item = Movie(toString,toString3,toString1,toString2)


                if (MyMovie.user!!.isNotEmpty()) {
                    list = gson.fromJson(MyMovie.user, type)
                    list.removeAt(position?.toInt()!!)
                    list.add(item)
                } else {
                    list = ArrayList()

                    list.removeAt(position?.toInt()!!)

                    list.add(item)

                }
                val toJson = gson.toJson(list, type)
                MyMovie.user = toJson.toString()


                finish()
            }

        }

    }
}