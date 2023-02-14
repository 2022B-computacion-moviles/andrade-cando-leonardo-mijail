package com.example.crud_sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class PresentacionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presentacion)

        findViewById<TextView>(R.id.id_presentacion).text = "CRUD - DEBER"
        val btnContinuar = findViewById<Button>(R.id.btn_continuar)
        btnContinuar.setOnClickListener {
            irActividad(SistemaPlanetarioMainActivity::class.java)
        }
    }

    fun irActividad(
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}