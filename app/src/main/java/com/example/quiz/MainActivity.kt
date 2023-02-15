package com.example.quiz

import androidx.appcompat.app.AppCompatActivity
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        val formulario = getFormulario() // Este método obtiene un objeto Formulario

        val layoutPreguntas = findViewById<LinearLayout>(R.id.layout_preguntas)
        val radioGroups = mutableListOf<RadioGroup>()
        for (pregunta in formulario.preguntas) {
            val textoPregunta = TextView(this)
            textoPregunta.text = pregunta.texto
            layoutPreguntas.addView(textoPregunta)

            val radioGroup = RadioGroup(this)
            radioGroups.add(radioGroup)
            for (opcion in pregunta.opciones) {
                val radioButton = RadioButton(this)
                radioButton.text = opcion
                radioGroup.addView(radioButton)
            }
            layoutPreguntas.addView(radioGroup)
        }

        val botonValidar = Button(this)
        botonValidar.text = "Validar"
        botonValidar.setOnClickListener {
            var correctas = 0
            for ((index, radioGroup) in radioGroups.withIndex()) {
                val idSeleccionado = radioGroup.checkedRadioButtonId
                if (idSeleccionado != -1) {
                    val radioButtonSeleccionado = radioGroup.findViewById<RadioButton>(idSeleccionado)
                    val opcionSeleccionada = radioGroup.indexOfChild(radioButtonSeleccionado)
                    if (opcionSeleccionada == formulario.preguntas[index].respuestaCorrecta) {
                        correctas++
                        radioButtonSeleccionado.setTextColor(Color.GREEN)
                    } else {
                        radioButtonSeleccionado.setTextColor(Color.RED)
                    }
                }
            }
            val mensaje = "Respuestas correctas: $correctas/${formulario.preguntas.size}"
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }
        layoutPreguntas.addView(botonValidar)
    }

    private fun getFormulario(): Form {
        val preguntas = mutableListOf(
            Question(1, "¿Cuál es tu color favorito?", listOf("Rojo", "Azul", "Verde", "Amarillo"), 1),
            Question(2, "¿Cuál es tu comida favorita?", listOf("Pizza", "Hamburguesa", "Tacos", "Sushi"), 0),
            Question(3, "¿Cuál es tu deporte favorito?", listOf("Fútbol", "Béisbol", "Baloncesto", "Tenis"), 2)
        )
        return Form(1, "Formulario de ejemplo", preguntas)
    }



}