package com.example.criarlista.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
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

        binding.rvNota.adapter = adapter
        
        updateList()

        insertListeners()
    }

    private fun insertListeners() {

        binding.fabNovaNota.setOnClickListener {

            startActivityForResult(Intent(this, AddNoteActivity::class.java), CREATE_NEW_NOTE)
        }

        adapter.listenerEdit = {

            val intent = Intent(this, AddNoteActivity::class.java)

            intent.putExtra(AddNoteActivity.NOTE_ID, it.id)
            startActivityForResult(intent, CREATE_NEW_NOTE)
        }

        adapter.listenerDelete = {

            NoteDataSource.deleteNote(it)
            updateList()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CREATE_NEW_NOTE && resultCode == RESULT_OK) {

            binding.rvNota.adapter = adapter
            adapter.submitList(NoteDataSource.getList())
        }
    }

    private fun updateList() {

        val list = NoteDataSource.getList()

        binding.itemEmptyInclude.emptyState.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE

        adapter.submitList(list)
    }

    companion object {
        private const val CREATE_NEW_NOTE = 1000
    }
}