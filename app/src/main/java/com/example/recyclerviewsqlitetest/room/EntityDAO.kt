package com.example.recyclerviewsqlitetest.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EntityDAO {
    @Query("SELECT * FROM entity")
    fun getAll(): List<Entity>

    @Query("SELECT * FROM entity WHERE uid = :id")
    fun getByUid(id : Int) : List<Entity>

    @Insert
    fun insertData(entity: Entity)

    @Insert
    fun insertAllData(vararg entity : Entity)

    @Delete
    fun delData(entity: Entity)

    @Update
    fun updateData(entity: Entity)

}