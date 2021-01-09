package com.example.recyclerviewsqlitetest

import android.content.Intent
import android.content.res.Resources
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.InvalidationTracker
import androidx.room.Room
import com.example.recyclerviewsqlitetest.room.AppDatabase
import com.example.recyclerviewsqlitetest.room.Entity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var testDataList = arrayListOf<TestData>()

    val mAdapter = MainAdapter(this)

    companion object{
        lateinit var dbHelper: DBHelper
        lateinit var database: SQLiteDatabase
        var roomDbTest : AppDatabase? = null
    }

    var read = Runnable {
        val entity = roomDbTest?.entityDAO()?.getAll()!!
        entity.forEach{
            val id = String.format("s%03d",it.uid)
            val resId = resources.getIdentifier(id, "drawable", packageName)
            it.resId = resId
        }
        mAdapter.setItem(entity)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this, "testDb.db", null, 1)

        roomDbTest = AppDatabase.getInstance(this)

        setData()

        mAdapter.notifyDataSetChanged()

        recyclerViewTest.adapter = mAdapter
        recyclerViewTest.layoutManager = LinearLayoutManager(this)

        insertItem.setOnClickListener {
            var i = Intent(this, InsertPage::class.java)
            startActivityForResult(i, 0)
        }
    }

    private fun setData(){
        testDataList.clear()
        database = dbHelper.writableDatabase
        val sql = "select * from ${FeedReaderContract.FeedEntry.tableName}"
        val cursor = database.rawQuery(sql, null)

        if(cursor.count != -1)
            while(cursor.moveToNext()) {
                val id = String.format("s%03d",cursor.getInt(0))
                val resId = resources.getIdentifier(id, "drawable", packageName)
                testDataList.add(TestData(cursor.getString(1), cursor.getBlob(2), cursor.getInt(3), resId))
            }

        Thread(read).start()
        mAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        AppDatabase.destroyInstance()
        roomDbTest = null
        dbHelper.close()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            setData()
        }
    }
}