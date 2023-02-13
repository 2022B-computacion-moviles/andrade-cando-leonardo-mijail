package com.example.crud_sqlite

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NuevoPlanetaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_planeta)

        var id_planeta:Int? = null

        if (intent.hasExtra("planeta")){
            val planeta = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.extras?.getSerializable("planeta",Planeta::class.java)
            } else {
                TODO("VERSION.SDK_INT < TIRAMISU")
            }

            findViewById<TextView>(R.id.t_nombre_planeta).setText(planeta?.nombre_planeta)
            findViewById<TextView>(R.id.t_distancia_planeta).setText(planeta?.distancia_planeta.toString())
            findViewById<TextView>(R.id.t_tamanio_planeta).setText(planeta?.tamanio_planeta.toString())
            findViewById<TextView>(R.id.t_fecha_planeta).setText(planeta?.fecha_planeta)
            findViewById<TextView>(R.id.t_id_fk).setText(planeta?.id_sistema_planeta.toString())
            id_planeta = planeta?.id_planeta

        }

        val database = AppDatabase.getDatabase(this)

        val btn_guardar = findViewById<Button>(R.id.btn_guardar_planeta)
        btn_guardar.setOnClickListener {
            val nombre = findViewById<TextView>(R.id.t_nombre_planeta).text.toString()
            val distancia = findViewById<TextView>(R.id.t_distancia_planeta).text.toString().toDouble()
            val tamanio = findViewById<TextView>(R.id.t_tamanio_planeta).text.toString().toFloat()
            val fecha = findViewById<TextView>(R.id.t_fecha_planeta).text.toString()
            val fk = findViewById<TextView>(R.id.t_id_fk).text.toString().toInt()


            val planeta = Planeta(fk,nombre,distancia,tamanio,fecha,R.drawable.ic_launcher_foreground)

            if(id_planeta != null){
                CoroutineScope(Dispatchers.IO).launch {
                    planeta.id_planeta = id_planeta
                    database.planeta().update(planeta)
                    this@NuevoPlanetaActivity.finish()
                }
            }else{
                CoroutineScope(Dispatchers.IO).launch {
                    database.planeta().insertAll(planeta)
                    this@NuevoPlanetaActivity.finish()
                }
            }
        }


    }
}