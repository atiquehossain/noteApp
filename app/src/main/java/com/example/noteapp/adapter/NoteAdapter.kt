package com.nexgenScript.noteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.NoteLayoutBinding
import com.example.noteapp.model.Note

// Adapter for the RecyclerView to display notes
class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NotViewHolder>() {

     private val differCallback = object : DiffUtil.ItemCallback<Note>() {
        // Checks if two items represent the same Note by comparing their IDs
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        // Checks if the contents of two Note items are the same
        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

    // AsyncListDiffer to handle the list of notes and update the RecyclerView efficiently
    val differ = AsyncListDiffer(this, differCallback)

    // Inflates the view and creates a ViewHolder for the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NoteLayoutBinding.inflate(inflater, parent, false)
        return NotViewHolder(binding)
    }

    // Binds data to the ViewHolder at the specified position
    override fun onBindViewHolder(holder: NotViewHolder, position: Int) {
        val note = differ.currentList[position]
        holder.bind(note)
    }

    // Returns the total number of items in the list
    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    // ViewHolder class to hold and bind data for each item in the RecyclerView
    class NotViewHolder(private val itemBinding: NoteLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        // Binds the Note data to the views in the layout
        fun bind(note: Note) {
            itemBinding.noteTitle.text = note.noteTitle
            itemBinding.noteDesc.text = note.noteDesc
            // Bind other views if needed
        }
    }
}
