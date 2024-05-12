package com.nexgenScript.noteapp.repository

import com.nexgenScript.noteapp.database.NoteDatabase
import com.nexgenScript.noteapp.model.Note

class NoteRepository(private  val db: NoteDatabase) {

    suspend fun insertNote(note:Note) = db.getNoteDao().insertNote(note)
}