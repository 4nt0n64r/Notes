package com.a4nt0n64r.notes

import android.os.Bundle
import android.view.LayoutInflater


import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.a4nt0n64r.notes.databinding.ActivityMainBinding
import com.a4nt0n64r.notes.utils.APP_ACTIVITY
import com.a4nt0n64r.notes.utils.AppPreferences

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var navController: NavController
    private var binding: ActivityMainBinding? = null

    //Лайфхак!
    val notNullBinding get() = binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(notNullBinding.root)
        APP_ACTIVITY = this
        toolbar = notNullBinding.toolbar
        navController = Navigation.findNavController(this,R.id.nav_host_fragment)
        setSupportActionBar(toolbar)
        title = getString(R.string.app_name)
        AppPreferences.getPreferences(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}