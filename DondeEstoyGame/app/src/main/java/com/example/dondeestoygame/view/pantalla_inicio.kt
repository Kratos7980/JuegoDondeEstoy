package com.example.dondeestoygame.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dondeestoygame.databinding.ActivityPantallaInicioBinding

class pantalla_inicio : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaInicioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPantallaInicioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val salir = binding.btnSalir

        salir.setOnClickListener {
            finish()
        }

        val jugar = binding.btnJugar

        jugar.setOnClickListener {
            intent = Intent(this, PantallaPrincipal::class.java)
            startActivity(intent)
        }


    }
}