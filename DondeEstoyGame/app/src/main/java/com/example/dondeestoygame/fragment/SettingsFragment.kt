package com.example.dondeestoygame.fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.preference.PreferenceFragmentCompat
import com.example.dondeestoygame.R
import com.example.dondeestoygame.modelo.Informacion

class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey) // vincula el fuch
    }

    override fun onResume() {
        super.onResume()
        preferenceManager.sharedPreferences?.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceManager.sharedPreferences?.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            "pref_sound" -> {
                val soundEnabled = sharedPreferences?.getBoolean(key, true)
                if (soundEnabled == true) {
                    Informacion.activarSonido()
                    Toast.makeText(requireContext(), "Sonido activado", Toast.LENGTH_SHORT).show()
                } else {
                    Informacion.desactivarSonido()
                    Toast.makeText(requireContext(), "Sonido desactivado", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}