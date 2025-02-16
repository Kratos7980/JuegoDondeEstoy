package com.example.dondeestoygame.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.dondeestoygame.R
import com.example.dondeestoygame.databinding.ActivityMirarVideosBinding
import com.example.dondeestoygame.modelo.Comida

class MirarVideos : AppCompatActivity() {
    private lateinit var comida: Comida
    private lateinit var binding: ActivityMirarVideosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMirarVideosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.getBundleExtra("data")
        comida = bundle?.getSerializable("comida") as Comida

        binding.botPlay.setOnClickListener {
            var mediaControls: MediaController = MediaController(this)
            mediaControls.setAnchorView(binding.vv)

            // set the media controller for video view
            binding.vv.setMediaController(mediaControls)

            // set the absolute path of the video file which is going to be played
            when(comida.title) {
                "Migas de pastor" -> {
                    binding.vv.setVideoURI(
                        Uri.parse("android.resource://"
                                + packageName + "/" + R.raw.migas
                        ))
                }
                "Sopa de ajo" -> {
                    enConstruccion()
                }
                "Cocas" -> {
                    enConstruccion()
                }
                "Salmorejo" -> {
                    enConstruccion()
                }
                "Zarangollo" -> {
                    enConstruccion()
                }

            }

            binding.vv.requestFocus()

            // arranca the video
            binding.vv.start()

            // display a toast message after the video is completed
            binding.vv.setOnCompletionListener {
                Toast.makeText(applicationContext, "Video completado",
                    Toast.LENGTH_LONG).show()
            }

            // display a toast message if any error occurs while playing the video
            binding.vv.setOnErrorListener { mp, what, extra ->
                Toast.makeText(applicationContext, "Ha ocurrido un errror " +
                        "mientros se reproduce el video !!!", Toast.LENGTH_LONG).show()
                false
            }
        }

        binding.botPausar.setOnClickListener {

            //pausa la ejecución
            binding.vv.pause()

        }

        binding.botContinuar.setOnClickListener {
            //continua la ejecución por dónde iba si se había pausado
            binding.vv.start()
        }

        binding.botDetener.setOnClickListener {
            //detiene completamente
            binding.vv.stopPlayback()
        }
    }

    private fun enConstruccion(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¡LO SENTIMOS!")
        builder.setMessage("¡EN CONSTRUCCIÓN!")
        builder.setPositiveButton("OK") { dialog, which ->
            val intent = Intent(this, PantallaPrincipal::class.java)
            startActivity(intent)
            finish()
        }
        builder.show()
    }
}