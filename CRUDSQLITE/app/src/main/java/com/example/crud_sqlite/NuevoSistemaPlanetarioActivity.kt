package com.example.crud_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NuevoSistemaPlanetarioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_sistema_planetario)

        val database = AppDatabase.getDatabase(this)

        val guardarbtn = findViewById<Button>(R.id.btn_guarda_sistema_planetario)
        guardarbtn.setOnClickListener{
            val nombre = findViewById<TextView>(R.id.t_nombre_sistema_planetario).text.toString()
            val anio = findViewById<TextView>(R.id.t_formacion_sistema_planetario).text.toString().toFloat()
            val galaxia = findViewById<TextView>(R.id.t_galaxia_sistema_planetario).text.toString()
            val tipo = findViewById<TextView>(R.id.t_tipo_sistema_planetario).text.toString()

            val sistema_planetario = SistemaPlanetario(nombre,anio,galaxia,tipo, R.drawable.ic_launcher_background)

            CoroutineScope(Dispatchers.IO).launch {
                database.sistemaPlanetario().insertAll(sistema_planetario)

                this@NuevoSistemaPlanetarioActivity.finish()
            }
        }
    }
}