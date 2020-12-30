package com.example.recyclerviewsqlitetest

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class MainAdapter (val context : Context) : RecyclerView.Adapter<MainHolder>(){
    var data = mutableListOf<TestData>(TestData("test", null,0))
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.test_item, parent, false)

        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
            holder.bind(data[position])
    }

    override fun getItemCount() = data.size

}