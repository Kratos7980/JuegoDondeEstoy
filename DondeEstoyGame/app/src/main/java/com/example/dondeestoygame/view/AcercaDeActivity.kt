package com.example.dondeestoygame.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dondeestoygame.databinding.ActivityAcercaDeBinding

class AcercaDeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityAcercaDeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAcercaDeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}