package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddModifyNoteActivity : AppCompatActivity() {
    lateinit var noteEditTitle: EditText
    lateinit var noteEditDesc: EditText
    lateinit var noteAddUpdateBtn: Button
    lateinit var viewModel: NotesViewModel
    var noteId = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_modify_note)

        noteEditTitle = findViewById(R.id.noteEditTitle)
        noteEditDesc = findViewById(R.id.noteEditDesc)
        noteAddUpdateBtn = findViewById(R.id.noteAddUpdateBtn)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NotesViewModel::class.java)

        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDesc = intent.getStringExtra("noteDescription")
            noteId = intent.getIntExtra("noteId", -1)
            noteAddUpdateBtn.setText("Update Note")
            noteEditTitle.setText(noteTitle)
            noteEditDesc.setText(noteDesc)
        } else {
            noteAddUpdateBtn.setText("Save Note")
        }

        noteAddUpdateBtn.setOnClickListener {
            val noteTitle = noteEditTitle.text.toString()
            val noteDesc = noteEditDesc.text.toString()

            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDesc.isNotEmpty()) {
                    val dta = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate:String = dta.format(Date())
                    val updateNote = Note(noteTitle, noteDesc, currentDate)
                    updateNote.id = noteId
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this, "Note Updated", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDesc.isNotEmpty()) {
                    val dta = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDate:String = dta.format(Date())
                    viewModel.addNote(Note(noteTitle, noteDesc, currentDate))
                    Toast.makeText(this, "Note Added", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
    }
}