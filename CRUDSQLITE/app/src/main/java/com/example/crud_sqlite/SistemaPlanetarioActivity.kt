package com.example.crud_sqlite

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class SistemaPlanetarioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sistema_planetario)

        val sistemaPlanetario = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("sistemaPlanetario", SistemaPlanetario::class.java)


        } else {
            intent.getSerializableExtra("sistemaPlanetario") as SistemaPlanetario
        }

        findViewById<TextView>(R.id.lv_id_sistema_planetario).text = sistemaPlanetario?.id_sistema_planetario.toString()
        findViewById<TextView>(R.id.lv_nombre_sistema_planetario).text = sistemaPlanetario?.nombre_sistema_planetario
        findViewById<TextView>(R.id.lv_formacion_sistema_planetario).text = sistemaPlanetario?.formacion_sistema_planetario.toString()
        findViewById<TextView>(R.id.lv_galaxia_sistema_planetario).text = sistemaPlanetario?.galaxia_sistema_planetario
        findViewById<TextView>(R.id.lv_tipo_sistema_planetario).text = sistemaPlanetario?.tipo_sistema_planetario
        sistemaPlanetario?.let {
            findViewById<ImageView>(R.id.lv_img_sistema_planetario).setImageResource(
                it.imagen)
        }
    }
}