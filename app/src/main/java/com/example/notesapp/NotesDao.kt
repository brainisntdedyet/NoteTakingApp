package com.example.notesapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {
    // If data has same ID, ignore this conflict
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from notesTable order by id ASC")
    fun retrieveNotes(): LiveData<List<Note>>
}