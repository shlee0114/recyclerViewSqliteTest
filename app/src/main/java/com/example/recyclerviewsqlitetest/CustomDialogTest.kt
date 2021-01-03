package com.example.recyclerviewsqlitetest

import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.Window
import android.widget.ImageView
import android.widget.TextView

class CustomDialogTest(context: Context) {
    private val dlg = Dialog(context)
    private lateinit var titleImage:ImageView
    private lateinit var titleText:TextView
    private lateinit var selectedText:TextView
    private lateinit var confirmBtn:TextView

    fun start(content:String, image: ByteArray?, selectType:Int){
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dlg.setContentView(R.layout.custom_dialog)
        dlg.setCancelable(false)

        titleImage = dlg.findViewById(R.id.titleImage)
        titleText = dlg.findViewById(R.id.mainText)
        selectedText = dlg.findViewById(R.id.selectedText)
        confirmBtn = dlg.findViewById(R.id.confirm)

        confirmBtn.setOnClickListener {
            dlg.dismiss()
        }

        titleText.text = content
        if(image != null) {
            val bitmap = BitmapFactory.decodeByteArray(image, 0, image.size)
            titleImage.setImageBitmap(bitmap)
        }
        selectedText.text = when(selectType){
            1 -> "test"
            2 -> "test2"
            3 -> "test3"
            else -> "else"
        }

        dlg.show()
    }
}