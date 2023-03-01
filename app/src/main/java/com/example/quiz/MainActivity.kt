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
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class MainActivity : AppCompatActivity() {

    private var formulario: Form? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        val formulario = getFormulario() // Este método obtiene un objeto Formulario

        /*
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

         */
    }

    fun jsonToForm(json: String): Form {
        val gson = Gson()
        return gson.fromJson(json, Form::class.java)
    }

    private fun getFormulario() {
        /*
        val preguntas = mutableListOf(
            Question(1, "¿Cuál es tu color favorito?","random", listOf("Rojo", "Azul", "Verde", "Amarillo"), 1),
            Question(2, "¿Cuál es tu comida favorita?","random",  listOf("Pizza", "Hamburguesa", "Tacos", "Sushi"), 0),
            Question(3, "¿Cuál es tu deporte favorito?","random",  listOf("Fútbol", "Béisbol", "Baloncesto", "Tenis"), 2)
        )
        return Form(1, "random", "Formulario de ejemplo", preguntas)

         */
        val url = "https://opoblueblood.000webhostapp.com/"
        getJsonFromUrlAsync(url, object : JsonCallback {
            override fun onJsonReceived(json: String) {
                println(json)
                // Aquí puedes procesar el JSON recibido
                val form = jsonToForm(json)
                val pregunta = form.preguntas[0].texto
                println(pregunta)

            }

            override fun onError(exception: Exception) {
                println("Error al obtener el JSON: ${exception.message}")
            }
        })

    }


    interface JsonCallback {
        fun onJsonReceived(json: String)
        fun onError(exception: Exception)
    }

    fun getJsonFromUrlAsync(url: String, callback: JsonCallback) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onError(e)
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body?.string() ?: ""
                callback.onJsonReceived(json)
            }
        })
    }




}