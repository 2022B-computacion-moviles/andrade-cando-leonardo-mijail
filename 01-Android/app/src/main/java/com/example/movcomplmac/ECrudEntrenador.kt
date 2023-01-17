package com.example.movcomplmac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView.FindListener
import android.widget.Button
import android.widget.EditText

class ECrudEntrenador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecrud_entrenadores)
        val botonCrearBDD = findViewById<Button>(R.id.btn_crear_bdd)
        botonCrearBDD.setOnClickListener{
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val email = findViewById<EditText>(R.id.input_email)
            EBasedeDatos.tablaEntrenador!!.crearEntrenador(
                nombre.text.toString(),
                email.text.toString()
            )
        }
        val botonBuscarBDD = findViewById<Button>(R.id.btn_buscar_bdd)
        botonBuscarBDD.setOnClickListener{
            val id = findViewById<EditText>(R.id.input_id)
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val email = findViewById<EditText>(R.id.input_email)
            val entrenador = EBasedeDatos.tablaEntrenador!!.
                    consultarEntrenadorPorId(
                        id.text.toString().toInt()
                    )
            id.setText(entrenador.id.toString())
            nombre.setText(entrenador.nombre)
            email.setText(entrenador.email)
        }
        val botonActualizarBDD = findViewById<Button>(R.id.btn_buscar_bdd)
        botonActualizarBDD.setOnClickListener{
            val id = findViewById<EditText>(R.id.input_id)
            val nombre = findViewById<EditText>(R.id.input_nombre)
            val email = findViewById<EditText>(R.id.input_email)
            EBasedeDatos.tablaEntrenador!!.actualizarEntrenadorFormulario(
                nombre.text.toString(),
                email.text.toString(),
                id.text.toString().toInt()
            )
        }
        val botonEliminarBDD = findViewById<Button>(R.id.btn_eliminar_bdd)
        botonEliminarBDD.setOnClickListener{
            val id=findViewById<EditText>(R.id.input_id)
            EBasedeDatos.tablaEntrenador!!.eliminarEntrenadorFormulario(
                id.text.toString().toInt()
            )
        }
    }
}