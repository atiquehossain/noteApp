package com.nexgenScript.noteapp.repository

import androidx.room.Query
import com.nexgenScript.noteapp.database.NoteDatabase
import com.nexgenScript.noteapp.model.Note

class NoteRepository(private  val db: NoteDatabase) {

    suspend fun insertNote(note:Note) = db.getNoteDao().insertNote(note)
    suspend fun  deleteNote(note: Note) = db.getNoteDao().deleteNote(note)
    suspend fun  updateNote(note: Note) = db.getNoteDao().updateNote(note)
     fun  getAllNotes(note: Note) = db.getNoteDao().getAllNotes()
    fun searchNote(query: String?) = db.getNoteDao().searchNote(query)

}