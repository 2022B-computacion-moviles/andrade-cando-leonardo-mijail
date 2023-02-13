package com.example.crud_sqlite

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class PlanetaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planeta)

        val planeta = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("planeta", Planeta::class.java)


        } else {
            intent.getSerializableExtra("planeta") as Planeta
        }

        findViewById<TextView>(R.id.cl_id_planeta).text = planeta?.id_planeta.toString()
        findViewById<TextView>(R.id.cl_nombre_planeta).text = planeta?.nombre_planeta
        findViewById<TextView>(R.id.cl_fecha_planeta).text = planeta?.fecha_planeta
        findViewById<TextView>(R.id.cl_distancia_planeta).text = planeta?.distancia_planeta.toString()
        findViewById<TextView>(R.id.cl_tamanio_planeta).text = planeta?.tamanio_planeta.toString()
        if (planeta != null) {
            findViewById<ImageView>(R.id.cl_img_planeta).setImageResource(planeta.imagen)
        }
    }
}