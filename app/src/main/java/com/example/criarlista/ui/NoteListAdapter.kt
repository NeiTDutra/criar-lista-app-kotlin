package com.example.criarlista.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.criarlista.databinding.ItemNotaBinding
import com.example.criarlista.models.Note

class NoteListAdapter : ListAdapter<Note, NoteListAdapter.NoteViewHolder>(DiffCallback()) {


    class NoteViewHolder(private val binding : ItemNotaBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind (item: Note) {
            binding.tvTitle.text = item.title
            binding.tvDescricao.text = item.description
            binding.tvDatePre.text = item.datePre
            binding.tvTimePre.text = item.timePre
            binding.tvDatePos.text = item.datePos
            binding.tvTimePos.text = item.timePos
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNotaBinding.inflate(inflater, parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class DiffCallback: DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Note, newItem: Note) = oldItem.id == newItem.id
}