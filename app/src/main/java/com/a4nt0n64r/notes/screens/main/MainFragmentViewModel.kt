package com.a4nt0n64r.notes.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.a4nt0n64r.notes.utils.REPOSITORY

class MainFragmentViewModel(app:Application):AndroidViewModel(app) {
    val allNotes = REPOSITORY.allNotes

    fun signOut(){
        REPOSITORY.signOut()
    }
}