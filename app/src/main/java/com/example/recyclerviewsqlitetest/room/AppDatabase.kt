package com.example.recyclerviewsqlitetest.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Entity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun entityDAO() : EntityDAO

    companion object{
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase?{
            INSTANCE?:run{
                synchronized(AppDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java, "test")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}