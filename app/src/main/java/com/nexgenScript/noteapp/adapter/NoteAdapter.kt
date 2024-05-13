package com.nexgenScript.noteapp.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nexgenScript.noteapp.databinding.NoteLayoutBinding
import com.nexgenScript.noteapp.model.Note

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    class NoteViewHolder(val itemBinfing: NoteLayoutBinding): RecyclerView.ViewHolder(itemBinfing.root){

        private val differCallback = object : DiffUtil.ItemCallback<Note>(){
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                TODO("Not yet implemented")
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                TODO("Not yet implemented")
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}