package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Pattern.Articles
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private lateinit var context: Context
    private var size_ = 1

    class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleView: TextView = itemView.findViewById(R.id.title)
        val author: TextView = itemView.findViewById(R.id.author)
        val image: ImageView = itemView.findViewById(R.id.image)
        val date: TextView = itemView.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_news, parent, false)
        context = parent.context
        return NewsViewHolder(v)
    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val activity: MainActivity = holder.itemView.context as MainActivity // доступ к MainActivity
        activity.sendGet{items -> setFields(holder, position, items)}
    }

    @SuppressLint("SimpleDateFormat")
    private fun setFields(holder: NewsViewHolder, position: Int, items: List<Articles>?){
        if (items != null) {
            size_=items.size
            holder.titleView.text = items.get(position).title
        }
        if (items != null) {
            holder.author.text = items.get(position).author
        }
        if (items != null) {
            holder.date.text = SimpleDateFormat("dd.MM HH:mm").format(items.get(position).publishedAt)
        }
        if (items != null) {
            Picasso.get().load(items.get(position).urlToImage).into(holder.image)
        }
        holder.itemView.setOnClickListener{
            val i = Intent(Intent.ACTION_VIEW, Uri.parse(items?.get(position)?.url!!))
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return size_
    }
}