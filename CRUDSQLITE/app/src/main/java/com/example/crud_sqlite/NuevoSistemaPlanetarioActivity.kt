package com.example.crud_sqlite

import android.os.Build
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


        var id_sistema_planetario: Int? = null

        if(intent.hasExtra("sistema_planetario")){
            val sistema_planetario = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.extras?.getSerializable("sistema_planetario",SistemaPlanetario::class.java)

            } else {
                TODO("VERSION.SDK_INT < TIRAMISU")
            }
            findViewById<TextView>(R.id.t_nombre_sistema_planetario).setText(sistema_planetario?.nombre_sistema_planetario)
            findViewById<TextView>(R.id.t_formacion_sistema_planetario).setText(sistema_planetario?.formacion_sistema_planetario.toString())
            findViewById<TextView>(R.id.t_galaxia_sistema_planetario).setText(sistema_planetario?.galaxia_sistema_planetario)
            findViewById<TextView>(R.id.t_tipo_sistema_planetario).setText(sistema_planetario?.tipo_sistema_planetario)
            id_sistema_planetario = sistema_planetario?.id_sistema_planetario
        }


        val database = AppDatabase.getDatabase(this)

        val guardarbtn = findViewById<Button>(R.id.btn_guarda_sistema_planetario)
        guardarbtn.setOnClickListener{
            val nombre = findViewById<TextView>(R.id.t_nombre_sistema_planetario).text.toString()
            val anio = findViewById<TextView>(R.id.t_formacion_sistema_planetario).text.toString().toFloat()
            val galaxia = findViewById<TextView>(R.id.t_galaxia_sistema_planetario).text.toString()
            val tipo = findViewById<TextView>(R.id.t_tipo_sistema_planetario).text.toString()

            val sistema_planetario = SistemaPlanetario(nombre,anio,galaxia,tipo, R.drawable.ic_launcher_background)

            if(id_sistema_planetario != null){
                CoroutineScope(Dispatchers.IO).launch {
                    sistema_planetario.id_sistema_planetario = id_sistema_planetario
                    database.sistemaPlanetario().update(sistema_planetario)
                    this@NuevoSistemaPlanetarioActivity.finish()
                }
            }else{
                CoroutineScope(Dispatchers.IO).launch {
                    database.sistemaPlanetario().insertAll(sistema_planetario)
                    this@NuevoSistemaPlanetarioActivity.finish()
                }
            }

        }
    }
}