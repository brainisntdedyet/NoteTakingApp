package com.example.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView

class NoteRVAdapter(val context: Context,
                    val noteClickInterface: NoteClickInterface,
                    val noteClickDeleteInterface: NoteClickDeleteInterface
                    ) : RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {

                        private val notesList = ArrayList<Note>()

                        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
                            val noteTitle = itemView.findViewById<TextView>(R.id.noteTitle)
                            val noteTime = itemView.findViewById<TextView>(R.id.noteTimeStamp)
                            val delBtn = itemView.findViewById<ImageView>(R.id.delBtn)

                    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val ItemView = LayoutInflater.from(parent.context).inflate(R.layout.notes_rv_item, parent, false)
        return ViewHolder(ItemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTitle.setText(notesList.get(position).noteTitle)
        holder.noteTime.setText("Last Updated: " + notesList.get(position).timeStamp)

        holder.delBtn.setOnClickListener {
            noteClickDeleteInterface.onDeleteIconClick(notesList.get(position))
        }

        holder.itemView.setOnClickListener {
            noteClickInterface.onNoteClick(notesList.get(position))
        }
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    fun updateList(newList: List<Note>) {
        notesList.clear()
        notesList.addAll(newList)
        notifyDataSetChanged()
    }

}

interface NoteClickDeleteInterface {
    fun onDeleteIconClick(note:Note)
}

interface NoteClickInterface {
    fun onNoteClick(note: Note)
}

