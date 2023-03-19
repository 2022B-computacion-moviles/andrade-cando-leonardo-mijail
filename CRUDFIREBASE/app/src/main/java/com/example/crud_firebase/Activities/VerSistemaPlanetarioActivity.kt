package com.example.crud_firebase.Activities

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.crud_firebase.Entities.SistemaPlanetario
import com.example.crud_firebase.R
import com.google.firebase.firestore.FirebaseFirestore

class VerSistemaPlanetarioActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_sistema_planetario)

        val id_sistemaPlanetario = intent.getIntExtra("id", 0)

        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        db.collection("Sistema Planetario")
            .document(id_sistemaPlanetario.toString())
            .get()
            .addOnSuccessListener { resultado ->

                    findViewById<TextView>(R.id.lv_id_sistema_planetario).text = resultado.id
                    findViewById<TextView>(R.id.lv_nombre_sistema_planetario).text = resultado["nombre"].toString()
                    findViewById<TextView>(R.id.lv_formacion_sistema_planetario).text = resultado["formacion"].toString()
                    findViewById<TextView>(R.id.lv_galaxia_sistema_planetario).text =resultado["galaxia"].toString()
                    findViewById<TextView>(R.id.lv_tipo_sistema_planetario).text = resultado["tipo"].toString()

            }
            .addOnFailureListener {
                Toast.makeText(this,"No se cargaron los datos", Toast.LENGTH_SHORT).show()
            }
    }
}
