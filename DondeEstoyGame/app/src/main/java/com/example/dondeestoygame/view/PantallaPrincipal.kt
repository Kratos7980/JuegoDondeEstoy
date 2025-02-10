package com.example.dondeestoygame.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dondeestoygame.R
import com.example.dondeestoygame.adapter.ComidaAdapter
import com.example.dondeestoygame.databinding.ActivityPantallaPrincipalBinding
import com.example.dondeestoygame.modelo.ComidaSurtidor
import com.example.dondeestoygame.modelo.Informacion

class PantallaPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaPrincipalBinding
    private lateinit var myAdapter: ComidaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.tbMenu
        setSupportActionBar(toolbar)

        val listComida = ComidaSurtidor.getComida()

        val rv = binding.rvItemsList

        myAdapter = ComidaAdapter(listComida, this)
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv.adapter = myAdapter



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.item_settings ->{
                val intent = Intent(this, ActivityPreference::class.java)
                startActivity(intent);
                true
            }
            R.id.item_info ->{
                val intent = Intent(this, AcercaDeActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.item_puntuacion ->{
                val intent = Intent(this, ActivityInformacion::class.java)
                startActivity(intent)
                true
            }
            else -> false
        }
    }
}