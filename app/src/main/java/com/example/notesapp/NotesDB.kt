package com.example.notesapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Shortened from ArrayOf
@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDB : RoomDatabase(){
    abstract fun retrieveNotesDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: NotesDB? = null

        fun getDB(context: Context) : NotesDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDB::class.java,
                    "note_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}