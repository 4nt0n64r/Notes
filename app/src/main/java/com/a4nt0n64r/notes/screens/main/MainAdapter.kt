package com.a4nt0n64r.notes.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.a4nt0n64r.notes.R
import com.a4nt0n64r.notes.models.AppNote

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var listNotes = emptyList<AppNote>()

    fun setListOfNotes(list: List<AppNote>) {
        listNotes = list
        notifyDataSetChanged()
    }

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameNote: TextView = view.findViewById(R.id.item_note_name)
        val textNote: TextView = view.findViewById(R.id.item_note_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.textNote.text = listNotes[position].text
        holder.nameNote.text = listNotes[position].name
    }

    override fun getItemCount(): Int {
        return listNotes.size
    }

    override fun onViewAttachedToWindow(holder: MainViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            MainFragment.click(listNotes[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: MainViewHolder) {
        holder.itemView.setOnClickListener(null)
        super.onViewDetachedFromWindow(holder)
    }
}