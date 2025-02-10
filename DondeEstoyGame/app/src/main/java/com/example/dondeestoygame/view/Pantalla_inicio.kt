package com.example.dondeestoygame.view

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
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
        val dificil: RadioButton = binding.rbDificil
        val facil: RadioButton = binding.rbFacil

        jugar.setOnClickListener {

            if(dificil.isChecked || facil.isChecked){
                if (dificil.isChecked) {
                    Informacion.setDificultad(2)
                }
                intent = Intent(this, PantallaPrincipal::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Selecciona una dificultad", Toast.LENGTH_SHORT).show()
            }

        }

    }
}