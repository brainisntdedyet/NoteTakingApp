package com.example.notesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {
    lateinit var notesRView: RecyclerView
    lateinit var addBtn: FloatingActionButton
    lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notesRView = findViewById(R.id.notesRView)
        addBtn = findViewById(R.id.addBtn)
        notesRView.layoutManager = LinearLayoutManager(this)

        val noteRVAdapter = NoteRVAdapter(this, this, this)
        notesRView.adapter = noteRVAdapter
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NotesViewModel::class.java)
        // add data to noteRVAdapter list in noteRVAdapter class
        viewModel.notesList.observe(this, { list->
            list?.let {
                noteRVAdapter.updateList(it)
            }
        })
        addBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, AddModifyNoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onNoteClick(note: Note) {
        // Redirect to AddModifyActivity
        val intent = Intent(this@MainActivity, AddModifyNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDesc)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.noteTitle} Removed", Toast.LENGTH_LONG).show()
    }
}