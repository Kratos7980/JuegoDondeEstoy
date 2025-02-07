package com.example.dondeestoygame.adapterInformacion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.dondeestoygame.R
import com.example.dondeestoygame.modelo.Comida

class InformacionAdapter(private val comidas: MutableList<Comida>): Adapter<ImagenViewHolder>() {

    private val selectedItems = mutableListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagenViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ImagenViewHolder(layoutInflater.inflate(R.layout.item_puntuacion, parent, false))
    }

    override fun getItemCount(): Int {
        return comidas.size
    }

    override fun onBindViewHolder(holder: ImagenViewHolder, position: Int) {
        val item = comidas[position]
        holder.nombre.text = item.title
        holder.puntuacion.text = item.puntuacion.toString()
        holder.imagen.setImageResource(item.image)

        
    }


}