package com.example.catatanku.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.catatanku.databinding.ItemCatatanBinding
import com.example.catatanku.entities.Catatan
import com.example.catatanku.ui.DetailCatatanActivity

class CatatanAdapter(var data: (Catatan) -> Unit, val onDelete:(Catatan) -> Unit):androidx.recyclerview.widget.ListAdapter<Catatan, CatatanAdapter.CatatanViewHolder>(DIFF_CALLBACK) {
    inner class CatatanViewHolder(private val binding: ItemCatatanBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(catatan: Catatan){
            binding.apply {
                judul.setText(catatan.judul)
                deskripsi.setText(catatan.deskripsi)
                binding.imgBtnHapus.setOnClickListener {
                    onDelete.invoke(catatan)
                }
            }
            itemView.setOnClickListener {
                data.invoke(catatan)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Catatan>() {
            override fun areItemsTheSame(
                oldItem: Catatan,
                newItem: Catatan
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Catatan,
                newItem: Catatan
            ): Boolean {
                return oldItem.uid == newItem.uid
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatatanViewHolder {
        val binding =
            ItemCatatanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatatanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatatanViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}