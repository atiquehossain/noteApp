package com.nexgenScript.noteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.databinding.NoteLayoutBinding
import com.example.noteapp.model.Note

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NotViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem // This might not be ideal, consider content comparison
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NoteLayoutBinding.inflate(inflater, parent, false)
        return NotViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotViewHolder, position: Int) {
        val note = differ.currentList[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class NotViewHolder(private val itemBinding: NoteLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(note: Note) {
            itemBinding.noteTitle.text = note.noteTitle
            itemBinding.noteDesc.text = note.noteDesc
            // Bind other views if needed
        }
    }
}
