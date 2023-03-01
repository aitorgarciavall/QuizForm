package com.example.quiz

class Form {

    var id: Int
    var nombre: String
    var tema: String
    var preguntas: List<Question>

    constructor(id: Int, tema: String, nombre: String, preguntas: List<Question>) {
        this.id = id
        this.tema = tema
        this.nombre = nombre
        this.preguntas = preguntas
    }

}