package com.example.dondeestoygame.modelo

import android.media.MediaPlayer
import com.example.dondeestoygame.R

object Informacion {

    private var puntos: Int = 0
    private var intentos: Int = 5
    private var victorias: Int = 0
    private val listComida: ArrayList<Comida> = ArrayList()
    private var dificultad: Int = 1
    private var sonido: Boolean = true

    // Activa sonido (preferencencias).
    fun activarSonido(){
        sonido = true
    }
    // Desactiva sonido (preferencencias).
    fun desactivarSonido(){
        sonido = false
    }
    // Devuelve el estado del sonido (preferencencias).
    fun getSonido(): Boolean {
        return sonido
    }

    // Devuelve el número de puntos.
    fun getPuntos(): Int {
        return puntos
    }
    // Suma puntos.
    fun sumarPuntos(puntos: Int) {
        this.puntos += puntos
    }
    // Devuelve el número de victorias.
    fun getVictorias(): Int {
        return victorias
    }
    // Suma victorias.
    fun sumarVictorias() {
        this.victorias += 1
    }

    // Devuelve el número de intentos.
    fun getIntentos(): Int {
        return intentos
    }
    // Resta intentos.
    fun restarIntentos(intentos: Int) {
        this.intentos -= intentos
    }
    // Devuelve la lista de comidas.
    fun getListComida(): ArrayList<Comida> {
        return listComida
    }
    // Añade una comida a la lista.
    fun addComida(comida: Comida) {
        listComida.add(comida)
    }
    // Cambia la dificultad.
    fun setDificultad(nivel: Int) {
        this.dificultad = nivel
    }
    // Devuelve la dificultad.
    fun getDificultad(): Int {
        return dificultad
    }
}
