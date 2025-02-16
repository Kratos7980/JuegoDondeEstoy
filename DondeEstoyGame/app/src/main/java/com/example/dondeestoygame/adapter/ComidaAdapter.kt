package com.example.dondeestoygame.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dondeestoygame.R
import com.example.dondeestoygame.modelo.Comida
import com.example.dondeestoygame.modelo.Informacion
import com.example.dondeestoygame.view.MirarVideos
import com.example.dondeestoygame.view.PantallaMapa

class ComidaAdapter (private val listComida: ArrayList<Comida>, private val context: Context): RecyclerView.Adapter<ComidaViewHolder>() {
    // Crea el ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComidaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ComidaViewHolder(layoutInflater.inflate(R.layout.item_comida, parent, false))
    }
    // Devuelve el número de elementos
    override fun getItemCount(): Int {
        return listComida.size
    }
    // Carga los datos en el ViewHolder
    override fun onBindViewHolder(holder: ComidaViewHolder, position: Int) {

        val comida = listComida[position]

        holder.nombreComidaItem.text = comida.title
        holder.imageItem.setImageResource(comida.image)

        //Definir onClick de los items
        holder.itemView.setOnClickListener {
            if(Informacion.getListComida().contains(comida) || Informacion.getIntentos()==0){
                val intent = Intent(context, MirarVideos::class.java)
                val bundle = Bundle()
                bundle.putSerializable("comida", comida)
                intent.putExtra("data", bundle)
                context.startActivity(intent)
            }else {
                val intent = Intent(context, PantallaMapa::class.java)
                val bundle = Bundle()
                bundle.putSerializable("comida", comida)
                intent.putExtra("data", bundle)

                context.startActivity(intent)
            }
        }

    }
}