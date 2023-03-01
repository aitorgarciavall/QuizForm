package com.example.quiz

class Question {

    var id: Int
    var texto: String
    var tema: String
    var opciones: List<String>
    var respuestaCorrecta: Int

    constructor(id: Int, texto: String, tema: String, opciones: List<String>, respuestaCorrecta: Int) {
        this.id = id
        this.texto = texto
        this.tema = tema
        this.opciones = opciones
        this.respuestaCorrecta = respuestaCorrecta
    }

}