package com.anotherdev.fittixhealth.model

class IMCLogic {

    fun calculateIMC(peso: Float, altura: Float): Float {
        return peso / (altura * altura)
    }
}