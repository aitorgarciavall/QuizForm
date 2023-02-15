package com.example.quiz

class Form {

    var id: Int
    var nombre: String
    var preguntas: List<Question>

    constructor(id: Int, nombre: String, preguntas: List<Question>) {
        this.id = id
        this.nombre = nombre
        this.preguntas = preguntas
    }

}