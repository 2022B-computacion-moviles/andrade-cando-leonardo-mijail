package com.example.crud_sqlite

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "SistemaPlanetario")
class SistemaPlanetario (
    val nombre_sistema_planetario: String,
    val formacion_sistema_planetario: Float,
    val galaxia_sistema_planetario: String,
    val tipo_sistema_planetario: String,
    val imagen:Int,
    @PrimaryKey(autoGenerate = true)
    var id_sistema_planetario: Int = 0

): Serializable {
}