package com.a4nt0n64r.notes.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.a4nt0n64r.notes.database.firebase.FireBaseRepository
import com.a4nt0n64r.notes.database.room.AppRoomDataBase
import com.a4nt0n64r.notes.database.room.AppRoomRepository
import com.a4nt0n64r.notes.utils.REPOSITORY
import com.a4nt0n64r.notes.utils.Type
import com.a4nt0n64r.notes.utils.showToast

class StartFragmentViewModel(app: Application) : AndroidViewModel(app) {

    private val context = app

    fun initDataBase(databaseType: Type, onSuccess: () -> Unit) {
        when (databaseType) {
            Type.Room -> {
                val dao = AppRoomDataBase.getInstance(context).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }
            Type.Firebase -> {
                REPOSITORY = FireBaseRepository()
                REPOSITORY.connectToDatabase({ onSuccess() }, { showToast(it) })
            }
        }
    }
}