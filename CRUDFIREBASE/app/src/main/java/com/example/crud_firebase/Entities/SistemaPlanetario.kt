package com.example.crud_firebase.Entities

import java.io.Serializable


class SistemaPlanetario (
    val nombre_sistema_planetario: String,
    val formacion_sistema_planetario: String,
    val galaxia_sistema_planetario: String,
    val tipo_sistema_planetario: String,
    var id_sistema_planetario: String

): Serializable {
}