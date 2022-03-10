package com.example.notesapp

import androidx.lifecycle.LiveData

class NotesRepo (private val notesDao: NotesDao) {
    val notesList: LiveData<List<Note>> = notesDao.retrieveNotes()
    // inserting the note in DAO
    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }
    // deleting note from DAO
    suspend fun delete(note: Note) {
        notesDao.delete(note)
    }
    // updating the note in DAO
    suspend fun update(note: Note) {
        notesDao.update(note)
    }

}