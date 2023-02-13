package com.example.crud_sqlite

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PlanetaDao {

    @Query("SELECT * FROM Planeta, SistemaPlanetario WHERE Planeta.id_sistema_planeta=SistemaPlanetario.id_sistema_planetario AND Planeta.id_sistema_planeta = :id")
    fun getAll(id:Int): LiveData<List<Planeta>>

    @Query("SELECT * FROM Planeta WHERE id_planeta = :id")
    fun get(id: Int): LiveData<Planeta>

    @Insert
    fun insertAll(vararg planeta: Planeta)

    @Update
    fun update(planeta: Planeta)

    @Delete
    fun delete(planeta: Planeta)

}