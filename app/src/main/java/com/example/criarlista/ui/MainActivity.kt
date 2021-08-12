package com.example.criarlista.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.criarlista.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { NoteListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvNota.adapter = adapter

        insertListeners()
    }

    private fun insertListeners() {

        binding.fabNovaNota.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }
    }
}