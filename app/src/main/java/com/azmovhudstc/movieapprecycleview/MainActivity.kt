package com.azmovhudstc.movieapprecycleview

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.azmovhudstc.movieapprecycleview.adapter.MovieAdpater
import com.azmovhudstc.movieapprecycleview.model.Movie
import com.azmovhudstc.movieapprecycleview.utils.MyMovie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private  lateinit var gson:Gson
    private  lateinit var list:ArrayList<Movie>
    lateinit var recycleViewAdapter: MovieAdpater
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadData()
        movie_rv.layoutManager=LinearLayoutManager(this)
        recycleViewAdapter= MovieAdpater(loadData(),applicationContext.applicationContext)
        movie_rv.adapter=recycleViewAdapter
        recycleViewAdapter.notifyDataSetChanged()
        recycleViewAdapter.notifyItemChanged(loadData().size)
        movie_rv.setHasFixedSize(true)
        add.setOnClickListener{
            var intent=Intent(this,AddMovieActivity::class.java)
            startActivity(intent)

        }

    }
    private fun loadData():ArrayList<Movie>{

        MyMovie.init(this)
        list= ArrayList()
        gson = Gson()

        var userstr = MyMovie.user
        var type = object : TypeToken<ArrayList<Movie>>() {}.type


        if (userstr?.isNotEmpty()==true){
            list = gson.fromJson(userstr, type)
        }
        for (i in 0 until list.size) {

        }
        return  list
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        list.clear()
        var arrayList=ArrayList<Movie>()
        MyMovie.init(this)
        gson = Gson()

        var userstr = MyMovie.user
        var type = object : TypeToken<ArrayList<Movie>>() {}.type


        if (userstr?.isNotEmpty()==true){
            arrayList = gson.fromJson(userstr, type)
        }

        list.addAll(arrayList)
        recycleViewAdapter= MovieAdpater(list,this)
        movie_rv.adapter=recycleViewAdapter
        recycleViewAdapter.notifyDataSetChanged()
    }
}