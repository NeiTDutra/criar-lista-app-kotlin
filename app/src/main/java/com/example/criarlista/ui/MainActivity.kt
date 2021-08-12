package com.example.criarlista.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.criarlista.databinding.ActivityMainBinding
import com.example.criarlista.datasource.NoteDataSource

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter by lazy { NoteListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertListeners()
    }

    private fun insertListeners() {

        binding.fabNovaNota.setOnClickListener {
            startActivityForResult(Intent(this, AddNoteActivity::class.java), CREATE_NEW_NOTE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CREATE_NEW_NOTE) {

            binding.rvNota.adapter = adapter
            adapter.submitList(NoteDataSource.getList())
        }
    }

    companion object {
        private const val CREATE_NEW_NOTE = 1000
    }
}