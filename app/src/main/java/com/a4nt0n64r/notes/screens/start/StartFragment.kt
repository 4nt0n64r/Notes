package com.a4nt0n64r.notes.screens.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.a4nt0n64r.notes.R
import com.a4nt0n64r.notes.databinding.FragmentStartBinding
import com.a4nt0n64r.notes.utils.*


class StartFragment : Fragment() {

    private var binding: FragmentStartBinding? = null
    private val notNullBinding get() = binding!!

    private lateinit var startFragmentViewModel: StartFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStartBinding.inflate(layoutInflater, container, false)
        return notNullBinding.root

//        В туториале так не делается. Почему?
//        return binding!!.root
    }

    override fun onStart() {
        super.onStart()

        startFragmentViewModel = ViewModelProvider(this).get(StartFragmentViewModel::class.java)

        if (AppPreferences.getInitUser()) {
            startFragmentViewModel.initDataBase(AppPreferences.getTypeDataBase().toType()) {
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        } else {
            initialization()
        }
    }

    private fun initialization() {

        notNullBinding.startBtnRoom.setOnClickListener {
            startFragmentViewModel.initDataBase(Type.Room) {
                AppPreferences.setInitUser(true)
                AppPreferences.setTypeDataBase(Type.Room)
                APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
            }
        }

        notNullBinding.startBtnFirebase.setOnClickListener {
            notNullBinding.inputEmail.visibility = View.VISIBLE
            notNullBinding.inputPassword.visibility = View.VISIBLE
            notNullBinding.startBtnLogin.visibility = View.VISIBLE

            notNullBinding.startBtnLogin.setOnClickListener {
                val inputEmail = notNullBinding.inputEmail.text.toString()
                val inputPassword = notNullBinding.inputPassword.text.toString()
                if (inputEmail.isNotEmpty() && inputPassword.isNotEmpty()) {
                    EMAIL = inputEmail
                    PASSWORD = inputPassword
                    startFragmentViewModel.initDataBase(Type.Firebase) {
                        AppPreferences.setInitUser(true)
                        AppPreferences.setTypeDataBase(Type.Firebase)
                        APP_ACTIVITY.navController.navigate(R.id.action_startFragment_to_mainFragment)
                    }
                } else {
                    showToast(getString(R.string.empty_email_and_password))
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

private fun String.toType(): Type {
    return when (this) {
        "Room" -> Type.Room
        "Firebase" -> Type.Firebase
        //Боже, ну и код
        else -> Type.Room
    }
}
