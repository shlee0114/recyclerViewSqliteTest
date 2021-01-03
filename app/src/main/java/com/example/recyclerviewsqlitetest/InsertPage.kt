package com.example.recyclerviewsqlitetest

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import kotlinx.android.synthetic.main.insert_data.*
import com.example.recyclerviewsqlitetest.FeedReaderContract.FeedEntry
import com.example.recyclerviewsqlitetest.MainActivity.Companion.database
import java.io.ByteArrayOutputStream

class InsertPage : AppCompatActivity() {

    private val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)

    private var select = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.insert_data)

        imageView.setOnClickListener {
            startActivityForResult(gallery, 1)
        }

        imageViewSnd.setOnClickListener {
            startActivityForResult(gallery, 2)
        }

        imageViewThr.setOnClickListener {
            startActivityForResult(gallery, 3)
        }

        radioGrp.setOnCheckedChangeListener{ radioGroup, checkedId ->
            select = when(checkedId){
                R.id.test1 -> 1
                R.id.test2 -> 2
                R.id.test3 -> 3
                else -> 0
            }
        }

        save.setOnClickListener {
            val bitmap = imageView.drawable.toBitmap()
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos)
            val byte = bos.toByteArray()
            val values = ContentValues().apply {
                put(FeedEntry.col1, mainText.text.toString())
                put(FeedEntry.col2, byte)
                put(FeedEntry.col3, select)
            }
            database?.insert(FeedEntry.tableName, null, values)
            setResult(RESULT_OK)
            finish()
        }

        exit.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            val image = data?.data
            when(requestCode){
                1 -> imageView.setImageURI(image)
                2 -> imageViewSnd.setImageURI(image)
                3 -> imageViewThr.setImageURI(image)
            }
        }
    }
}