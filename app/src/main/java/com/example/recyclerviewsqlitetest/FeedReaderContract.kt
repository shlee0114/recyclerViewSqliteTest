package com.example.recyclerviewsqlitetest

import android.provider.BaseColumns

object FeedReaderContract {
    object FeedEntry : BaseColumns{
        const val tableName = "test_table"
        const val col1 = "main_text"
        const val col2 = "image_data"
        const val col3 = "select_type"
    }
}