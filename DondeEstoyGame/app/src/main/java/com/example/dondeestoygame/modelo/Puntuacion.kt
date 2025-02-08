package com.example.dondeestoygame.modelo

object Puntuacion {

    private var puntos:Int
    private var intentos:Int

    init {
        puntos = 0
        intentos = 5
    }

    fun getPuntos():Int{
        return puntos
    }

    fun sumarPuntos(puntos:Int){
        this.puntos += puntos
    }

    fun setIntentos(intentos:Int){
        this.intentos = intentos
    }

    fun getIntentos():Int{
        return intentos
    }
}