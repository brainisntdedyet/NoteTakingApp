package com.example.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application){
    val notesList: LiveData<List<Note>>
    val repo: NotesRepo

    init {
        val dao = NotesDB.getDB(application).retrieveNotesDao()
        repo = NotesRepo(dao)
        notesList = repo.notesList
    }
    // Notes for viewModelScope: defined by each ViewModel in App
    // If ViewModel Cleared -> all coroutines in scope is cleared
    fun addNote(note: Note) = viewModelScope.launch( Dispatchers.IO ) {
        repo.insert(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch( Dispatchers.IO ) {
        repo.delete(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch( Dispatchers.IO ) {
        repo.update(note)
    }

}