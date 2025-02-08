package com.example.dondeestoygame.view

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.example.dondeestoygame.databinding.ActivityPantallaInicioBinding
import com.example.dondeestoygame.modelo.Informacion

class Pantalla_inicio : AppCompatActivity() {

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
        val dificultad = binding.ckDificil

        jugar.setOnClickListener {
            if(dificultad!!.isChecked){
                Informacion.setDificultad(2)
            }
            intent = Intent(this, PantallaPrincipal::class.java)
            startActivity(intent)
        }

    }
}