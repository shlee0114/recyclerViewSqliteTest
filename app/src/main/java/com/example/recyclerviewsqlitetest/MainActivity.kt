package com.example.recyclerviewsqlitetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var testDataList = arrayListOf<TestData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewTest.adapter = MainAdapter(this)
        recyclerViewTest.layoutManager = LinearLayoutManager(this)
    }
}