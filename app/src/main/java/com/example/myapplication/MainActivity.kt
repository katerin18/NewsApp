package com.example.myapplication

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.Pattern.Articles
import com.example.myapplication.Pattern.GetData
import com.example.myapplication.Pattern.Headline
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>? = null

    val apiService = GetData.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutManager = LinearLayoutManager(this)
        val recyclerView : RecyclerView = findViewById(R.id.recView)
        recyclerView.layoutManager = layoutManager

        adapter = NewsAdapter()
        recyclerView.adapter = adapter
        //recyclerView.adapter?.notifyDataSetChanged()

//        sendGet{resp -> setFields(resp)} // as lambda-function

    }

    private fun setArt(ar: List<Articles>?){
            if (ar != null) {
                var t = ""
                for (i in ar){
                     t+="* "+i.title+" \n"
                }
               // findViewById<TextView>(R.id.text_).text=t
            }
        }

    fun sendGet(resultHandler:(List<Articles>?) -> Unit){   // resultHandler - "ждун"
        val call = apiService.getHeadlines("us", "b0a884713a17494a93c15876d758feca", 100)
        call.enqueue(object : Callback<Headline> {  // "планировщик" запуска
            override fun onResponse(call: Call<Headline>, response: Response<Headline>) {
                if (response.isSuccessful){
                    println("No error")
                    resultHandler(response.body()?.articles)
                }
            }
            override fun onFailure(call: Call<Headline>, t: Throwable) {
                println("I've got a failure in response")
            }
        })
    }
}

