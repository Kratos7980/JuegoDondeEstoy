package com.example.dondeestoygame.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dondeestoygame.adapter.ComidaAdapter
import com.example.dondeestoygame.databinding.ActivityInformacionBinding
import com.example.dondeestoygame.modelo.Informacion

class ActivityInformacion : AppCompatActivity() {

    private lateinit var  binding: ActivityInformacionBinding
    private lateinit var myAdapter: ComidaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformacionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listComida = Informacion.getListComida();

        val rv = binding.rvInformacion

        myAdapter = ComidaAdapter(listComida, this)
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv.adapter = myAdapter

        val puntos = binding.tvPuntos
        puntos.text = Informacion.getPuntos().toString()

    }
}