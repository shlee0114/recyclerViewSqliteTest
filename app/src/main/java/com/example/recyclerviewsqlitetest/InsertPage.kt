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
import com.example.recyclerviewsqlitetest.MainActivity.Companion.roomDbTest
import com.example.recyclerviewsqlitetest.room.Entity
import java.io.ByteArrayOutputStream

class  InsertPage : AppCompatActivity() {

    private val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)

    private var select = 0

    val addRunnable = Runnable {
        var newEntity = Entity().apply {
            text = mainText.text.toString()
            num = select
        }
        roomDbTest?.entityDAO()?.insertData(newEntity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.insert_data)

        imageView.setOnClickListener {
            startActivityForResult(gallery, 1)
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
            var byte : ByteArray?
            var values : ContentValues
            if(imageView.drawable != null) {
                val bitmap = imageView.drawable.toBitmap()
                val bos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos)
                byte = bos.toByteArray()
                values = ContentValues().apply {
                    put(FeedEntry.col1, mainText.text.toString())
                    put(FeedEntry.col2, byte!!)
                    put(FeedEntry.col3, select)
                }
            }else {
                values = ContentValues().apply {
                    put(FeedEntry.col1, mainText.text.toString())
                    put(FeedEntry.col2, "")
                    put(FeedEntry.col3, select)
                }
            }

            database?.insert(FeedEntry.tableName, null, values!!)

            Thread(addRunnable).start()

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
            }
        }
    }
}