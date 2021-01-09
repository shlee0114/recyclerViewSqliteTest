package com.example.recyclerviewsqlitetest

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewsqlitetest.room.Entity

class MainAdapter (val context : Context) : RecyclerView.Adapter<MainHolder>(){
    var data = listOf<Entity>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.test_item, parent, false)

        return MainHolder(view)
    }



    override fun onBindViewHolder(holder: MainHolder, position: Int) {
            holder.bind(data[position]){
                CustomDialogTest(context).start(it.text?:"empty", it.resId?:R.drawable.s001, it.num?:0)
            }
    }

    override fun getItemCount() = data.size

    fun setItem(entity: List<Entity>){
        data = entity

        notifyDataSetChanged()
    }

}