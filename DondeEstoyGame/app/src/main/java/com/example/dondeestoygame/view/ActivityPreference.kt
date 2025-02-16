package com.example.dondeestoygame.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dondeestoygame.databinding.ActivityPreferenceBinding

class ActivityPreference : AppCompatActivity() {

    lateinit var binding: ActivityPreferenceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}