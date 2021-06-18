package com.a4nt0n64r.notes.screens.note

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.a4nt0n64r.notes.R
import com.a4nt0n64r.notes.databinding.FragmentNoteBinding
import com.a4nt0n64r.notes.models.AppNote
import com.a4nt0n64r.notes.utils.APP_ACTIVITY

class NoteFragment : Fragment() {

    private var binding: FragmentNoteBinding? = null
    private val notNullBinding get() = binding!!

    private lateinit var vievModel: NoteFragmentViewModel

    private lateinit var currentNote: AppNote

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNoteBinding.inflate(layoutInflater, container, false)
        currentNote = arguments?.getSerializable("note") as AppNote
        return notNullBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {
        setHasOptionsMenu(true)
        notNullBinding.noteName.text = currentNote.name
        notNullBinding.noteText.text = currentNote.text
        vievModel = ViewModelProvider(this).get(NoteFragmentViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.note_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_delete -> {
                vievModel.delete(currentNote) { APP_ACTIVITY.navController.navigate(R.id.action_noteFragment_to_mainFragment) }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}