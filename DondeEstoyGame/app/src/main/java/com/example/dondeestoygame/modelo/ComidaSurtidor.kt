package com.example.dondeestoygame.modelo

import com.example.dondeestoygame.R

object ComidaSurtidor {

    private val listComida = ArrayList<Comida>()

    fun getComida():ArrayList<Comida>{
        listComida.add(Comida("Migas de pastor", R.drawable.migas, 0, 0, 0.00, false))
        listComida.add(Comida("Sopa de ajo", R.drawable.sopa_castellana, 0, 0, 0.00, false))
        listComida.add(Comida("Cocas", R.drawable.cocas, 0, 0, 0.00, false))
        listComida.add(Comida("Salmorejo", R.drawable.salmorejo, 0, 0, 0.00, false))
        listComida.add(Comida("Zarangollo", R.drawable.zarangollo, 0, 0, 0.00, false))

        return listComida
    }

}