package com.example.dondeestoygame.adapterInformacion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.dondeestoygame.R
import com.example.dondeestoygame.modelo.Comida

class InformacionAdapter(private val comidas: List<Comida>): Adapter<ImagenViewHolder>() {

    private var posSelectedItem: Int = -1
    private var data:List<Comida>
    init {
        data = comidas
    }
    // Actualiza los datos
    fun updateData(newData: List<Comida>) {
        this.data = newData
        notifyItemChanged(0, data.size-1)
        notifyDataSetChanged()
    }
    // Devuelve la posición del elemento seleccionado
    fun getSelectedItem(): Int {
        return posSelectedItem
    }
    // Crea el ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagenViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ImagenViewHolder(layoutInflater.inflate(R.layout.item_puntuacion, parent, false))
    }
    // Devuelve el número de elementos
    override fun getItemCount(): Int {
        return data.size
    }
    // Carga los datos en el ViewHolder
    override fun onBindViewHolder(holder: ImagenViewHolder, position: Int) {
        val comida = comidas[position]
        holder.nombre.text = comida.title
        holder.imagen.setImageResource(comida.image)
    }


}