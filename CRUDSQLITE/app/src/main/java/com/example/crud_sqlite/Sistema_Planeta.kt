package com.example.crud_sqlite

import androidx.room.Embedded

import androidx.room.Relation


data class Sistema_Planeta(
    @Embedded val sistemaplanetario: SistemaPlanetario,
    @Relation(
        parentColumn = "id_sistema_planetario",
        entityColumn = "id_planeta"
    )
    val lists: List<Planeta>
)