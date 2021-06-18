package com.a4nt0n64r.notes.screens.addnote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.a4nt0n64r.notes.models.AppNote
import com.a4nt0n64r.notes.utils.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNoteViewModel(app: Application) : AndroidViewModel(app) {

    fun insert(note: AppNote, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.insert(note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }
}

//Хороший пример чтобы переделать позже:

//suspend fun DBClass.insert(note: AppNote) {
//    database.insert()  // тут может быть ексепшен
//}
//
//suspend fun ViewModelClass.insert(note: AppNote, onSuccess: () -> Unit) {
//    val succesLiveData = MutableLiveData(false)
//
//
//    viewModelScope.launch(Dispatchers.Main) {
//        try {
//            withContext(Dispatchers.IO) {
//                DBClass.insert(note)
//                successLiveData.setValue(true)
//            }
//            onSuccess()
//        } catch (Exception e) {
//            Log.e(e)
//        }
//    }
//}
//
//class Fragment {
//    addNoteViewModel.successLiveData.observe
//    {
//        // во вьюмодели триггернуть успешную лайвдвту и тогда ок
//    }
//
//    fun onViewCreated(...) {
//        addNoteViewModel.insert(Appnote(...))
//    }
//
//}
