package com.example.dondeestoygame.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dondeestoygame.adapter.ComidaAdapter
import com.example.dondeestoygame.databinding.ActivityPantallaPrincipalBinding
import com.example.dondeestoygame.modelo.ComidaSurtidor

class PantallaPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaPrincipalBinding
    private lateinit var myAdapter: ComidaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val listComida = ComidaSurtidor.getComida();

        val rv = binding.rvItemsList

        myAdapter = ComidaAdapter(listComida, this)
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv.adapter = myAdapter

    }
}