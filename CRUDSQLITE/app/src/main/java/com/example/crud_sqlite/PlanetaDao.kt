package com.example.crud_sqlite

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PlanetaDao {

    @Query("SELECT * FROM Planeta")
    fun gatAll(): LiveData<List<Planeta>>

    @Query("SELECT * FROM Planeta WHERE id_planeta = :id")
    fun get(id: Int): LiveData<Planeta>

    @Insert
    fun insertAll(vararg planeta: Planeta)

    @Update
    fun update(planeta: Planeta)

}