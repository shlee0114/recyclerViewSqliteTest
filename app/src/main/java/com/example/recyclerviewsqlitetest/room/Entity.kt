package com.example.recyclerviewsqlitetest.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity
data class Entity (
    @PrimaryKey(autoGenerate = true) var uid : Long?,
    @ColumnInfo(name="textCol") var text : String?,
    @ColumnInfo(name="numCol") var num : Int?,
    @Ignore var resId : Int
){
    constructor() : this(null, "", 0, 0)
}