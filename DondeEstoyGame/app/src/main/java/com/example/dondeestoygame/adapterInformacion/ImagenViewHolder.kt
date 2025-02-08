package com.example.dondeestoygame.adapterInformacion

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.dondeestoygame.databinding.ItemComidaBinding
import com.example.dondeestoygame.databinding.ItemPuntuacionBinding

class ImagenViewHolder(itemView: View) : ViewHolder(itemView) {

    val binding = ItemPuntuacionBinding.bind(itemView)

    val nombre = binding.titleComida
    val imagen = binding.imgComida
//    val puntuacion = binding.puntuacionComida

}