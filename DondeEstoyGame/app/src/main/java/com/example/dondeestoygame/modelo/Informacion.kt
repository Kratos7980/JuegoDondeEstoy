package com.example.dondeestoygame.modelo

object Informacion {

    private var puntos:Int = 0
    private var intentos:Int = 5
    private val listComida:ArrayList<Comida> = ArrayList()

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

    fun getListComida():ArrayList<Comida>{
        return listComida
    }

    fun addComida(comida:Comida){
        listComida.add(comida)
    }

//    1ª -> 100 puntos
//    2ª -> 75 puntos
//    3ª -> 50 puntos
//    4ª -> 25 puntos
//    5ª -> 10 puntos
}