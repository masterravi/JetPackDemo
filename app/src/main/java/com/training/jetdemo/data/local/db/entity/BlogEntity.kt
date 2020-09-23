package com.training.jetdemo.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.training.jetdemo.data.remote.models.Media
import com.training.jetdemo.data.remote.models.User
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "blog_entity")
data class BlogEntity(

    @PrimaryKey(autoGenerate = true)
    @NotNull
    val id: Int,

    @ColumnInfo(name = "comments")
    val comments: Int?=null,

    @ColumnInfo(name = "content")
    val content: String?=null,

    @ColumnInfo(name = "createdAt")

    val createdAt: String?=null,

    @ColumnInfo(name = "likes")
    val likes: Int?=null,

    @ColumnInfo
    val media: List<Media>?=null,

    @ColumnInfo
    val user: List<User>?=null



):Serializable
