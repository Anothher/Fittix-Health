package com.anotherdev.fittixhealth.view

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.anotherdev.fittixhealth.databinding.ActivitySuscripcionesBinding

class Suscripciones : AppCompatActivity() {
    private lateinit var animationDrawable: AnimationDrawable
    private lateinit var binding: ActivitySuscripcionesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuscripcionesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        animationViewUp()
    }

    private fun animationViewUp() {
        binding.apply {

            // Animaciones para los textViewDonationOption
            val donationOptions = listOf(
                textViewDonationOption1Title,
                textViewDonationOption2Title,
                textViewDonationOption3Title,
                textViewDonationOption4Title,
                textViewDonationOption5Title
            )

            donationOptions.forEachIndexed { index, textView ->
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
            }
        }
    }

}
