package com.a4nt0n64r.notes.utils

import android.content.Context
import android.content.SharedPreferences

object AppPreferences {

    private const val INIT_USER = "init_user"
    private const val TYPE_DB = "type_db"
    private const val NAME_PREF = "preferences"

    private lateinit var preferences: SharedPreferences

    fun getPreferences(context: Context): SharedPreferences {
        preferences = context.getSharedPreferences(NAME_PREF, Context.MODE_PRIVATE)
        return preferences
    }

    fun setInitUser(init: Boolean) {
        preferences.edit()
            .putBoolean(INIT_USER, init)
            .apply()
    }

    fun setTypeDataBase(type: Type) {
        preferences.edit()
            .putString(
                TYPE_DB,
                type.name
            )
            .apply()
    }

    fun getInitUser(): Boolean {
        return preferences.getBoolean(INIT_USER, false)
    }

    fun getTypeDataBase(): String {
        return preferences.getString(TYPE_DB, Type.Room.name).toString()
    }
}