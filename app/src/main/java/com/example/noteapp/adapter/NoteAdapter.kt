package com.nexgenScript.noteapp.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.NoteLayoutBinding


class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NotViewHolder>() {

    class  NotViewHolder(val  itemBinding: NoteLayoutBinding): RecyclerView.ViewHolder(itemBinding.root){

    }

    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.NotViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: NoteAdapter.NotViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}