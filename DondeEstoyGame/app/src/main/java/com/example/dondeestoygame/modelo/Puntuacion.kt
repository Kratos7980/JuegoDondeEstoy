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

    fun getIntentos():Int{
        return intentos
    }

    fun restarIntentos(intentos:Int){
        this.intentos -= intentos
    }

//    1ª -> 100 puntos
//    2ª -> 75 puntos
//    3ª -> 50 puntos
//    4ª -> 25 puntos
//    5ª -> 10 puntos
}