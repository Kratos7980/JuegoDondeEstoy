package com.example.dondeestoygame.modelo

object Informacion {

    private var puntos: Int = 0
    private var intentos: Int = 5
    private val listComida: ArrayList<Comida> = ArrayList()
    private var dificultad: Int = 1
    private var acertado: Boolean = false

    fun getPuntos(): Int {
        return puntos
    }

    fun sumarPuntos(puntos: Int) {
        this.puntos += puntos
    }

    fun getIntentos(): Int {
        return intentos
    }

    fun restarIntentos(intentos: Int) {
        this.intentos -= intentos
    }

    fun getListComida(): ArrayList<Comida> {
        return listComida
    }

    fun addComida(comida: Comida) {
        listComida.add(comida)
    }

    fun setDificultad(nivel: Int) {
        this.dificultad = nivel
    }

    fun getDificultad(): Int {
        return dificultad
    }
    fun setAcertado(acertado: Boolean){
        this.acertado = acertado
    }
    fun getAcertado(): Boolean{
        return acertado
    }
}
