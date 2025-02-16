package com.example.dondeestoygame.modelo

import com.example.dondeestoygame.R

object ComidaProvider {
    // Lista comidas
    private val listComida = arrayListOf(
        Comida("Migas de pastor", R.drawable.migas, 0, 0, 0.00),
        Comida("Sopa de ajo", R.drawable.sopa_castellana, 0, 0, 0.00),
        Comida("Cocas", R.drawable.cocas, 0, 0, 0.00),
        Comida("Salmorejo", R.drawable.salmorejo, 0, 0, 0.00),
        Comida("Zarangollo", R.drawable.zarangollo, 0, 0, 0.00)
    )
    // Devuelve la lista de comidas
    fun getComida():ArrayList<Comida>{
        return listComida
    }

}