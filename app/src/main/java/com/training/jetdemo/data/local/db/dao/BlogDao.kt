package com.training.jetdemo.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.training.jetdemo.data.local.db.entity.BlogEntity
import io.reactivex.Single

@Dao
interface BlogDao {
    @Query("SELECT * FROM blog_entity")
    fun getAll(): Single<List<BlogEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(item: List<BlogEntity>)
}