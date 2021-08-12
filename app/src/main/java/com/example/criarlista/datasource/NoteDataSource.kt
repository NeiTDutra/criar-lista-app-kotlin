package com.example.criarlista.datasource

import com.example.criarlista.models.Note
import java.util.*

object NoteDataSource {

    private val list = arrayListOf<Note>()

    fun getList() = list.toList()

    fun insertNote(note: Note) {
        if (note.id == 0) {
            list.add(note.copy(id = list.size + 1))
        } else {
            list.remove(note)
            list.add(note)
        }
    }

    fun findById(noteId: Int) = list.find { it.id == noteId }

    fun deleteNote(note: Note) {
        list.remove(note)
    }
}
