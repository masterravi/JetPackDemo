package com.training.jetdemo.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.training.jetdemo.data.local.db.dao.BlogDao
import com.training.jetdemo.data.local.db.entity.BlogEntity
import javax.inject.Singleton

@Singleton
@Database(
    entities = [
        BlogEntity::class
    ],
    exportSchema = false,
    version = 5
)
@TypeConverters(Converter::class)
abstract class DatabaseService : RoomDatabase() {
    abstract fun repoDao(): BlogDao
}