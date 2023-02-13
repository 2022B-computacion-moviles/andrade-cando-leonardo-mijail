package com.example.crud_sqlite

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SistemaPlanetarioDao {

    @Query("SELECT * FROM SistemaPlanetario")
    fun gatAll(): LiveData<List<SistemaPlanetario>>

    @Query("SELECT * FROM SistemaPlanetario WHERE id_sistema_planetario = :id")
    fun get(id: Int): LiveData<SistemaPlanetario>

    @Insert
    fun insertAll(vararg sistemaplanetario: SistemaPlanetario)

    @Update
    fun update(sistemaplanetario: SistemaPlanetario)

    @Query("DELETE FROM SistemaPlanetario WHERE id_sistema_planetario = :id")
    fun del(id: Int)

    @Delete
    fun delete(sistemaplanetario: SistemaPlanetario)
}
