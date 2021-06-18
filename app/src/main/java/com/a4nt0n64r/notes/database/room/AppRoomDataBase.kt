package com.a4nt0n64r.notes.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.a4nt0n64r.notes.models.AppNote

@Database(entities = [AppNote::class], version = 1)
abstract class AppRoomDataBase: RoomDatabase() {
    abstract fun getAppRoomDao(): AppRoomDao

    companion object{
        @Volatile
        private var database:AppRoomDataBase? = null

        @Synchronized
        fun getInstance(context: Context): AppRoomDataBase{
            return if (database == null){
                database = Room.databaseBuilder(context,
                    AppRoomDataBase::class.java,"database").build()
                database as AppRoomDataBase
            } else database as AppRoomDataBase
        }
    }
}