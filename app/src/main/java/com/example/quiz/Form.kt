package com.example.quiz
import com.google.gson.Gson

class Form {

    var id: Int = 0
    var nombre: String = ""
    var tema: String = ""
    var preguntas: List<Question> = emptyList()

    constructor(id: Int, tema: String, nombre: String, preguntas: List<Question>) {
        this.id = id
        this.tema = tema
        this.nombre = nombre
        this.preguntas = preguntas
    }

    constructor()

    companion object {
        fun fromJson(json: String): Form {
            return Gson().fromJson(json, Form::class.java)
        }
    }
}
