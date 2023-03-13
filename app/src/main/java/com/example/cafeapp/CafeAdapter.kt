package com.example.cafeapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.cafeapp.databinding.ItemCafeRowBinding

class CafeAdapter (private var listCafe : ArrayList<Cafe> ) : RecyclerView.Adapter<CafeAdapter.CafeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeViewHolder {
        val binding = ItemCafeRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CafeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CafeViewHolder, position: Int) {
        val (gambar, nama, alamat, deskripsi) = listCafe[position]
        Glide.with(holder.itemView.context).load(gambar).into(holder.binding.gambarCafe)
        holder.binding.namaCafe.text = nama
        holder.binding.alamatCafe.text = alamat
        holder.binding.deskripsiCafe.text = deskripsi

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, CafeDetail::class.java)
            intentDetail.putExtra("key_cafe", listCafe[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount(): Int = listCafe.size

    class CafeViewHolder(var binding: ItemCafeRowBinding) : ViewHolder(binding.root)
}