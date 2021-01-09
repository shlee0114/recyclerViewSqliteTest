package com.example.recyclerviewsqlitetest

import android.content.res.Resources
import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    val imageTest = itemView.findViewById<ImageView>(R.id.imageTest)
    val mainText = itemView.findViewById<TextView>(R.id.mainText)
    val typeText = itemView.findViewById<TextView>(R.id.typeText)

    fun bind(testData: TestData, itemClick:(TestData) -> Unit){
        mainText.text = testData.testText
        typeText.text = when(testData.testSelector){
            1 -> "test"
            2 -> "test2"
            3 -> "test3"
            else -> "else"
        }
        if(testData.testImage != null && testData.testImage.size != 1) {
            val bitmap = BitmapFactory.decodeByteArray(testData.testImage, 0, testData.testImage.size)
            imageTest.setImageBitmap(bitmap)
        }else{
            imageTest.setImageResource(testData.imageInt)
        }
        itemView.setOnClickListener { itemClick(testData) }
    }
}