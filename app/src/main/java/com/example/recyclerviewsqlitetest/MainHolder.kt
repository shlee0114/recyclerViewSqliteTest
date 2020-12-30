package com.example.recyclerviewsqlitetest

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val imageTest = itemView.findViewById<ImageView>(R.id.imageTest)
    val mainText = itemView.findViewById<TextView>(R.id.mainText)
    val typeText = itemView.findViewById<TextView>(R.id.typeText)

    fun bind(testData: TestData){
        mainText.text = testData.testText
        typeText.text = when(testData.testSelector){
            0 -> "test"
            1 -> "test2"
            2 -> "test3"
            else -> "else"
        }
       // imageTest.setImageBitmap(testData.testImage)
    }
}