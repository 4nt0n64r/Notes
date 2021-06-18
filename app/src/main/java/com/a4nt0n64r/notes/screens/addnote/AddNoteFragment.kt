package com.a4nt0n64r.notes.screens.addnote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.a4nt0n64r.notes.R
import com.a4nt0n64r.notes.databinding.FragmentAddNoteBinding
import com.a4nt0n64r.notes.models.AppNote
import com.a4nt0n64r.notes.utils.APP_ACTIVITY
import com.a4nt0n64r.notes.utils.showToast

class AddNoteFragment : Fragment() {

    private var binding: FragmentAddNoteBinding? = null
    private val notNullBinding get() = binding!!

    private lateinit var addNoteFragmentViewModel: AddNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(layoutInflater, container, false)
        return notNullBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        addNoteFragmentViewModel = ViewModelProvider(this).get(AddNoteViewModel::class.java)
        notNullBinding.btnAddNote.setOnClickListener {
            val name = notNullBinding.inputNameNote.text.toString()
            val text = notNullBinding.inputTextNote.text.toString()
            if (name.isEmpty()) {
                showToast(getString(R.string.toast_empty_name))
            } else {
                addNoteFragmentViewModel.insert(
                    AppNote(name = name, text = text)
                ) { APP_ACTIVITY.navController.navigate(R.id.action_addNoteFragment_to_mainFragment) }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}