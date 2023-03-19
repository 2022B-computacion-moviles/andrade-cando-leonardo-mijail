package com.example.crud_firebase.Entities

import java.io.Serializable


class Planeta(
    val nombre_planeta:String,
    val distancia_planeta:String,
    val tamanio_planeta: String,
    val fecha_planeta:String,
    var id_planeta: String
): Serializable {
}