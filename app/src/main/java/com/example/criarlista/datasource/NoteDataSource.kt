package com.example.criarlista.datasource

import com.example.criarlista.models.Note
import java.util.*

object NoteDataSource {
    private val list = ArrayList <Note> ()

    fun getList() = list

    fun insertNote(note: Note) {
        list.add(note.copy(id = list.size + 1))
    }
}
