package com.a4nt0n64r.notes.database.firebase

import androidx.lifecycle.LiveData
import com.a4nt0n64r.notes.database.DatabaseRepository
import com.a4nt0n64r.notes.models.AppNote
import com.a4nt0n64r.notes.utils.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FireBaseRepository : DatabaseRepository {

    override val allNotes: LiveData<List<AppNote>>
        get() = AllNotesLiveData()

    init {
        AUTH = FirebaseAuth.getInstance()
    }

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        val idNote = REF_DATABASE.push().key.toString()
        val mapNote = hashMapOf<String, Any>()
        mapNote[ID_FIREBASE] = idNote
//        mapNote.put(ID_FIREBASE,idNote)  идентичный код
        mapNote[NAME] = note.name
        mapNote[TEXT] = note.text

        REF_DATABASE.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        REF_DATABASE.child(note.idFirebase).removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFailure: (String) -> Unit) {

        if (AppPreferences.getInitUser()){
            initRefs()
            onSuccess()
        } else{
            AUTH.signInWithEmailAndPassword(EMAIL, PASSWORD)
                .addOnSuccessListener {
                    initRefs()
                    onSuccess() }
                .addOnFailureListener {
                    AUTH.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                        .addOnSuccessListener {
                            initRefs()
                            onSuccess() }
                        .addOnFailureListener { onFailure(it.message.toString()) }
                }
        }



    }

    private fun initRefs() {
        CURRENT_ID = AUTH.currentUser?.uid.toString()
        REF_DATABASE = FirebaseDatabase.getInstance().reference
            .child(CURRENT_ID)
    }

    override fun signOut() {
        AUTH.signOut()
    }
}