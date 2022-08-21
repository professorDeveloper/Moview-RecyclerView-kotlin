package com.azmovhudstc.movieapprecycleview.adapter

import android.app.Application
import com.azmovhudstc.movieapprecycleview.MovieAboutActivity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.azmovhudstc.movieapprecycleview.EditActivity
import com.azmovhudstc.movieapprecycleview.R
import com.azmovhudstc.movieapprecycleview.model.Movie
import com.azmovhudstc.movieapprecycleview.utils.MyMovie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.item.view.*

class MovieAdpater(var list: ArrayList<Movie> ,var context:Context):RecyclerView.Adapter<MovieAdpater.MyViewHolder>() {

    lateinit var gson:Gson

    inner class MyViewHolder(var itemView:View):RecyclerView.ViewHolder(itemView){
        fun onBind(movie: Movie,position: Int){
            itemView.item_author.text=movie.movieAuthor
            itemView.item_name.text=movie.movieName
            itemView.item_date.text=movie.movieDate
            itemView.setOnClickListener {
                var intent=Intent(context,MovieAboutActivity::class.java)
                intent.putExtra("aboutName",movie.movieName)
                intent.putExtra("aboutDescription",movie.movieDescription)
                intent.putExtra("aboutAuthor",movie.movieAuthor)
                intent.putExtra("aboutDate",movie.movieDate)
                intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
            itemView.edit.setOnClickListener {
                var intent=Intent(context,EditActivity::class.java)
                intent.putExtra("name",movie.movieName)
                intent.putExtra("description",movie.movieDescription)
                intent.putExtra("author",movie.movieAuthor)
                intent.putExtra("date",movie.movieDate)
                intent.putExtra("position",position.toString())
                intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
            itemView.delete.setOnClickListener {

                MyMovie.init(context)

                gson = Gson()

                var userstr = MyMovie.user
                var type = object : TypeToken<ArrayList<Movie>>() {}.type


                if (userstr?.isNotEmpty()==true){
                    list = gson.fromJson(userstr, type)

                }

                list.removeAt(position)
                val toJson = gson.toJson(list, type)
                MyMovie.user = toJson.toString()
                notifyItemRemoved(position)
                notifyItemRangeChanged(position,list.size)

            }

            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currentItem=list[position]

    holder.onBind(currentItem,position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}