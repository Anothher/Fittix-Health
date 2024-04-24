package com.anotherdev.fittixhealth

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.anotherdev.fittixhealth.view.Suscripciones
import com.anotherdev.fittixhealth.databinding.ActivityMainBinding
import com.anotherdev.fittixhealth.model.IMCLogic

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val imcModel = IMCLogic()
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        animationViewUp()

    }

    @SuppressLint("SetTextI18n")
    private fun init() {

        val weightEditText = binding.editTextWeight
        val heightEditText = binding.editTextHeight
        val calculateButton = binding.buttonCalculate
        val resultTextView = binding.textViewResult
        val recomendacionTextView = binding.textViewAdvice

        calculateButton.setOnClickListener {
            val pesoText = weightEditText.text.toString()
            val alturatText = heightEditText.text.toString()
            if (pesoText.isNotEmpty() && alturatText.isNotEmpty()) {
                val peso = pesoText.toFloat()
                val altura = alturatText.toFloat()
                val imc = calculateIMC(peso, altura)
                val imcText = String.format("Tu IMC: %.2f", imc)
                resultTextView.text = imcText

                // Mostrar mensaje para mejorar hábitos alimenticios según el IMC
                val message = getIMCMessage(imc)
                recomendacionTextView.text = message

            } else {
                resultTextView.text = "Por favor ingrese su peso y altura."
                recomendacionTextView.text =
                    "" // Limpiar el TextView si no hay valores válidos de peso y altura
            }
        }


        val botonDonacion = binding.btnDonar
        botonDonacion.setOnClickListener {
            pantallaSuscripciones()
        }
    }

    private fun getIMCMessage(imc: Float?): String {
        return if (imc != null) {
            when {
                imc < 18.5 -> "Tu IMC indica que estás bajo peso. Es importante consumir una dieta equilibrada y nutritiva para alcanzar un peso saludable."
                imc < 25 -> "Tu IMC indica que tienes un peso saludable. Sigue manteniendo hábitos alimenticios balanceados y ejercítate regularmente para conservar tu salud."
                imc < 30 -> "Tu IMC indica que estás en sobrepeso. Considera realizar cambios en tu dieta y aumentar tu actividad física para reducir el riesgo de problemas de salud relacionados con el peso."
                else -> "Tu IMC indica que estás en obesidad. Es importante buscar orientación médica y adoptar cambios significativos en tu estilo de vida, incluyendo una dieta saludable y ejercicio regular, para mejorar tu salud."
            }
        } else {
            ""
        }
    }


    private fun pantallaSuscripciones() {
        val intent = Intent(this, Suscripciones::class.java)
        startActivity(intent)
    }

    private fun calculateIMC(peso: Float, altura: Float): Float {
        return imcModel.calculateIMC(peso, altura)
    }

    private fun animationViewUp() {

        binding.apply {


            // Animación para textView
            textView.apply {
                translationY = -50f
                alpha = 0f
                animate()
                    .translationY(0f)
                    .alpha(1f)
                    .setDuration(500)
                    .setStartDelay(300)
                    .withStartAction { visibility = View.VISIBLE }
                    .start()
            }
            textViewResult.animate().translationY(0f).alpha(1f).setDuration(50).setStartDelay(300).start()
            textViewAdvice.animate().translationY(0f).alpha(1f).setDuration(50).setStartDelay(300).start()
        }

    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Si quieres salir haga click nuevamente", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
}
