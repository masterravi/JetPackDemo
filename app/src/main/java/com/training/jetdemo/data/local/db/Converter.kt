package com.training.jetdemo.data.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.training.jetdemo.data.remote.models.Media
import com.training.jetdemo.data.remote.models.User

class Converter {

    @TypeConverter
    fun MediaListToJson(value: List<Media>?): String? {
        value?.let {
            return Gson().toJson(value)
        }?: return null
    }

    @TypeConverter
    fun jsonToMediaList(value: String?): List<Media>? {
        val objects = Gson().fromJson(value, Array<Media>::class.java).toList()
        return objects?:null
    }

    @TypeConverter
    fun UserListToJson(value: List<User>?): String? {
        value?.let {
            return Gson().toJson(value)
        }?: return null
    }

    @TypeConverter
    fun jsonToUserList(value: String?):List<User>? {
        val objects = Gson().fromJson(value, Array<User>::class.java).toList()
        return objects?:null
    }
}