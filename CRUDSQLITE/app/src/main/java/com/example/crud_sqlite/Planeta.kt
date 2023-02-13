package com.example.crud_sqlite

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Planeta")
class Planeta(
    val id_sistema_planeta: Int,
    val nombre_planeta:String,
    val distancia_planeta:Double,
    val tamanio_planeta:Float,
    val fecha_planeta:String,
    val imagen: Int,
    @PrimaryKey(autoGenerate = true)
    var id_planeta: Int = 0


): Serializable{
}

