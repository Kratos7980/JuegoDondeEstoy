package com.example.dondeestoygame.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dondeestoygame.databinding.ItemComidaBinding

class ComidaViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

    //Creo un binding de ItemPlantasBinding
    val binding = ItemComidaBinding.bind(itemView)

    //Recupero los elementos del xml con el binding
    val imageItem: ImageView = binding.imgComida
    val nombreComidaItem: TextView = binding.titleComida
}