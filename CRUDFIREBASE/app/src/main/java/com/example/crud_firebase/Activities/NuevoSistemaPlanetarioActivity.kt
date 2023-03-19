package com.example.crud_firebase.Activities

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crud_firebase.R
import com.google.firebase.firestore.FirebaseFirestore

class NuevoSistemaPlanetarioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_sistema_planetario)

        var id_sistema_planetario: String? = null

        if(intent.hasExtra("sistema_planetario")){
            val sistema_planetario = intent.getIntExtra("sistema_planetario",0)
            val db: FirebaseFirestore = FirebaseFirestore.getInstance()
            db.collection("Sistema Planetario")
                .document(sistema_planetario.toString())
                .get()
                .addOnSuccessListener {resultado ->


                    findViewById<TextView>(R.id.lv_nombre_sistema_planetario).text = resultado["nombre"].toString()
                    findViewById<TextView>(R.id.lv_formacion_sistema_planetario).text = resultado["formacion"].toString()
                    findViewById<TextView>(R.id.lv_galaxia_sistema_planetario).text =resultado["galaxia"].toString()
                    findViewById<TextView>(R.id.lv_tipo_sistema_planetario).text = resultado["tipo"].toString()
                    id_sistema_planetario = sistema_planetario.toString()

                }
                .addOnFailureListener {
                    Toast.makeText(this,"Error al guardar",Toast.LENGTH_SHORT).show()
                }

        }


        val guardarbtn = findViewById<Button>(R.id.btn_guarda_sistema_planetario)
        guardarbtn.setOnClickListener{
            val nombre = findViewById<TextView>(R.id.t_nombre_sistema_planetario).text.toString()
            val anio = findViewById<TextView>(R.id.t_formacion_sistema_planetario).text.toString()
            val galaxia = findViewById<TextView>(R.id.t_galaxia_sistema_planetario).text.toString()
            val tipo = findViewById<TextView>(R.id.t_tipo_sistema_planetario).text.toString()

            val dato = hashMapOf(
                "nombre" to nombre,
                "formacion" to anio,
                "galaxia" to galaxia,
                "tipo" to tipo
            )

            if(id_sistema_planetario != null){
                //update
                val db: FirebaseFirestore = FirebaseFirestore.getInstance()
                db.collection("Sistema Planetario")
                    .document(id_sistema_planetario.toString())
                    .set(dato)
                    .addOnSuccessListener {
                        Toast.makeText(this,"Actualizado",Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this,"Error al Actualizar",Toast.LENGTH_SHORT).show()
                    }

            }else{
                //insert
                val db: FirebaseFirestore = FirebaseFirestore.getInstance()
                db.collection("Sistema Planetario")
                    .add(dato)
                    .addOnSuccessListener {
                        Toast.makeText(this,"Guardado",Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this,"Error al guardar",Toast.LENGTH_SHORT).show()
                    }
            }

        }
    }
}