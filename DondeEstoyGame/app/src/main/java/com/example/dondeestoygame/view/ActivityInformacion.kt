package com.example.dondeestoygame.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dondeestoygame.adapter.ComidaAdapter
import com.example.dondeestoygame.adapterInformacion.InformacionAdapter
import com.example.dondeestoygame.databinding.ActivityInformacionBinding
import com.example.dondeestoygame.modelo.Informacion

class ActivityInformacion : AppCompatActivity() {

    private lateinit var  binding: ActivityInformacionBinding
    private lateinit var myAdapter: InformacionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Obtenemos la lista de comidas.
        val listComida = Informacion.getListComida();
        //Recuperamos el recycler view.
        val rv = binding.rvInformacion
        //Creamos el adaptador y lo vinculamos con el recycler view.
        myAdapter = InformacionAdapter(listComida)
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv.adapter = myAdapter
        //Recuperamos el TextView de puntos y lo actualizamos.
        val puntos = binding.tvPuntos
        puntos.text = Informacion.getPuntos().toString()

    }
}