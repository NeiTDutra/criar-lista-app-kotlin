package com.example.criarlista.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.criarlista.R
import com.example.criarlista.databinding.ItemNotaBinding
import com.example.criarlista.models.Note

class NoteListAdapter : ListAdapter<Note, NoteListAdapter.NoteViewHolder>(DiffCallback()) {

    var listenerEdit:(Note) -> Unit = {}
    var listenerDelete:(Note) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNotaBinding.inflate(inflater, parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NoteViewHolder(private val binding: ItemNotaBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Note) {
            binding.tvTitle.text = item.title
            binding.tvDescricao.text = item.description
            binding.tvDatePre.text = item.datePre
            binding.tvTimePre.text = item.timePre
            binding.tvDatePos.text = item.datePos
            binding.tvTimePos.text = item.timePos
            binding.imgMore.setOnClickListener {
                showPopup(item)
            }

        }

        private fun showPopup(item: Note) {
            val imgMore = binding.imgMore
            val popupMenu = PopupMenu(imgMore.context, imgMore)
            popupMenu.menuInflater.inflate(R.menu.menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menuEdit -> listenerEdit(item)
                    R.id.menuDelete -> listenerDelete(item)
                }
                return@setOnMenuItemClickListener true
            }
            popupMenu.show()
        }
    }
}

class DiffCallback: DiffUtil.ItemCallback<Note>() {

    override fun areItemsTheSame(oldItem: Note, newItem: Note) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Note, newItem: Note) = oldItem.id == newItem.id
}