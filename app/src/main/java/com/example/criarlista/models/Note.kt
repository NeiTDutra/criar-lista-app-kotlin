package com.example.criarlista.models

data class Note(
    val title: String,
    val description: String,
    val datePre: String,
    val datePos: String,
    val timePre: String,
    val timePos: String,
    val id: Int = 0
)
