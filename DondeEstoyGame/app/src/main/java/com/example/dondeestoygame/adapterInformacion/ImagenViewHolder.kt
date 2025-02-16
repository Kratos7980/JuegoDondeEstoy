package com.example.dondeestoygame.adapterInformacion

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.dondeestoygame.databinding.ItemComidaBinding
import com.example.dondeestoygame.databinding.ItemPuntuacionBinding

class ImagenViewHolder(itemView: View) : ViewHolder(itemView) {
    // Creo un binding de ItemPlantasBinding
    val binding = ItemPuntuacionBinding.bind(itemView)
    // Recupero los elementos del xml con el binding
    val nombre = binding.titleComida
    val imagen = binding.imgComida

}