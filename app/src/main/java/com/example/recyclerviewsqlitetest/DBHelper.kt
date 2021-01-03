package com.example.recyclerviewsqlitetest

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.recyclerviewsqlitetest.FeedReaderContract.FeedEntry

class DBHelper (
        context: Context?,
        name: String?,
        factory : SQLiteDatabase.CursorFactory?,
        version : Int
    ) : SQLiteOpenHelper(context, name, factory, version){

    private val SQL_CREATE_ENTRIES =
            "CREATE TABLE if not exists ${FeedEntry.tableName}(" +
            "${BaseColumns._ID} integer primary key," +
            "${FeedEntry.col1} text," +
            "${FeedEntry.col2} blob," +
            "${FeedEntry.col3} integer);"

    private val SQL_DROP_ENTRIES = "DROP TABLE if exists ${FeedEntry.tableName}"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DROP_ENTRIES)
        onCreate(db)
    }

}