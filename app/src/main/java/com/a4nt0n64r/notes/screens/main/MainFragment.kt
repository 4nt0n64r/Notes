package com.a4nt0n64r.notes.screens.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.a4nt0n64r.notes.R
import com.a4nt0n64r.notes.databinding.FragmentMainBinding
import com.a4nt0n64r.notes.models.AppNote
import com.a4nt0n64r.notes.utils.APP_ACTIVITY
import com.a4nt0n64r.notes.utils.AppPreferences

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private val notNullBinding get() = binding!!

    private lateinit var vievModel: MainFragmentViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MainAdapter
    private lateinit var observerOnList: Observer<List<AppNote>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return notNullBinding.root
    }

    override fun onStart() {
        super.onStart()
        initialization()
    }

    private fun initialization() {

        setHasOptionsMenu(true)

        adapter = MainAdapter()
        recyclerView = notNullBinding.recyclerView
        recyclerView.adapter = adapter
        observerOnList = Observer {
            val list = it.asReversed()
            adapter.setListOfNotes(list)
        }

        //Почему нельзя использовать .javaClass?
        vievModel = ViewModelProvider(this).get(MainFragmentViewModel::class.java)

        vievModel.allNotes.observe(this, observerOnList)

        notNullBinding.btnAddNote.setOnClickListener {
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_addNoteFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.exit_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_exit -> {
                vievModel.signOut()
                AppPreferences.setInitUser(false)
                APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_startFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        vievModel.allNotes.removeObserver(observerOnList)
        recyclerView.adapter = null
    }

    companion object {
        fun click(note: AppNote) {
            val bundle = Bundle()
            bundle.putSerializable("note", note)
            APP_ACTIVITY.navController.navigate(R.id.action_mainFragment_to_noteFragment, bundle)
        }
    }
}