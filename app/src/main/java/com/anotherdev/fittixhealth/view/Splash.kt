package com.anotherdev.fittixhealth.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.anotherdev.fittixhealth.MainActivity
import com.anotherdev.fittixhealth.databinding.ActivitySplashBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Splash : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val animacion = binding.animacionHome
        val btnInicio = binding.btninicio

        btnInicio.setOnClickListener {
            setAnimacionBoton(animacion)
        }

    }

    private fun setAnimacionBoton(imageView: LottieAnimationView) {
        imageView.playAnimation()
        // Lanzar una corrutina para iniciar la actividad después de un breve retraso
        GlobalScope.launch(Dispatchers.Main) {
            delay(2000) // Esperar 2 segundos
            startActivity(Intent(this@Splash, MainActivity::class.java))
            finish() // Cerrar la actividad actual
        }
    }



}
