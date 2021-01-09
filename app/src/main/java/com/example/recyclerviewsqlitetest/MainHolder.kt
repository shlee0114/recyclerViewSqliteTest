package com.example.recyclerviewsqlitetest

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewsqlitetest.room.Entity

class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val imageTest = itemView.findViewById<ImageView>(R.id.imageTest)
    val mainText = itemView.findViewById<TextView>(R.id.mainText)
    val typeText = itemView.findViewById<TextView>(R.id.typeText)

    fun bind(testData: Entity, itemClick:(Entity) -> Unit){
        mainText.text = testData.text
        typeText.text = when(testData.num){
            1 -> "test"
            2 -> "test2"
            3 -> "test3"
            else -> "else"
        }
            imageTest.setImageResource(testData.resId)
        itemView.setOnClickListener { itemClick(testData) }
    }
}