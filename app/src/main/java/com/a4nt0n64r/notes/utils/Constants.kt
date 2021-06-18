package com.a4nt0n64r.notes.utils

import com.a4nt0n64r.notes.MainActivity
import com.a4nt0n64r.notes.database.DatabaseRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

//Есть предположение что это плохо
lateinit var APP_ACTIVITY: MainActivity
lateinit var REPOSITORY: DatabaseRepository
lateinit var EMAIL: String
lateinit var PASSWORD: String

enum class Type {
    Firebase,
    Room
}

const val ID_FIREBASE = "id_firebase"
const val NAME = "name"
const val TEXT = "text"

lateinit var AUTH: FirebaseAuth
lateinit var CURRENT_ID: String
lateinit var REF_DATABASE: DatabaseReference