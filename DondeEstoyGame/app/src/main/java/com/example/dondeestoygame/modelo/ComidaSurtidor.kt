package com.example.dondeestoygame.modelo

import com.example.dondeestoygame.R

object ComidaSurtidor {

    private val listComida = ArrayList<Comida>()

    fun getComida():ArrayList<Comida>{
        listComida.add(Comida("Migas de pastor", R.drawable.migas, 0, 0))
//        listComida.add(Comida("Sopa de ajo", "sopa_castellana", 0, 0))
//        listComida.add(Comida("Cocas", "cocas", 0, 0))
//        listComida.add(Comida("Salmorejo", "salmorejo", 0, 0))
//        listComida.add(Comida("Zarangollo", "zarangollo", 0, 0))

        return listComida
    }

}