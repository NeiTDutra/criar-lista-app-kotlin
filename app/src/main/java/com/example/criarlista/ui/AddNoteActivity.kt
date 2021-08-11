package com.example.criarlista.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.criarlista.databinding.ActivityAddTaskBinding
import com.example.criarlista.databinding.ActivityMainBinding
import com.example.criarlista.datasource.NoteDataSource
import com.example.criarlista.extensions.format
import com.example.criarlista.extensions.text
import com.example.criarlista.models.Note
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class AddNoteActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var bindingA: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertListeners()
    }

    private fun insertListeners() {

        binding.tilDataPre.editText?.setOnClickListener {
            Log.e("TAG", "insertListeners:")
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offSet = timeZone.getOffset(Date().time) * -1
                binding.tilDataPre.text = Date(it + offSet).format()
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }

        binding.tilHourPre.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).build()
            timePicker.addOnPositiveButtonClickListener {
                val hour = if (timePicker.hour in 0..9) "0${timePicker.hour}" else "${timePicker.hour}"
                val min = if (timePicker.minute in 0..9) "0${timePicker.minute}" else "${timePicker.minute}"
                binding.tilHourPre.text = "$hour:$min"
            }
            timePicker.show(supportFragmentManager, null)
        }

        binding.mbCancelarNota.setOnClickListener {
            finish()
        }

        binding.mbCriarNota.setOnClickListener {
            val note = Note(
                title = binding.tilTitle.text,
                description = binding.tilDescricao.text,
                datePre = binding.tilDataPre.text,
                timePre = binding.tilHourPre.text,
                datePos = binding.tilDataPos.text,
                timePos = binding.tilHourPos.text
            )
            NoteDataSource.insertNote(note)
        }

        bindingA.fabNovaNota.setOnClickListener {

        }
    }
}