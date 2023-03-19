package com.example.crud_firebase.Activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crud_firebase.R
import com.google.firebase.firestore.FirebaseFirestore

class VerPlanetaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_planeta)

        val id_planeta = intent.getStringExtra("id")

        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        db.collection("Sistema Planetario")
            .document(id_planeta.toString())
            .get()
            .addOnSuccessListener { resultado ->

                findViewById<TextView>(R.id.cl_id_planeta).text = resultado.id
                findViewById<TextView>(R.id.cl_nombre_planeta).text = resultado["nombre"].toString()
                findViewById<TextView>(R.id.cl_fecha_planeta).text = resultado["fecha"].toString()
                findViewById<TextView>(R.id.cl_distancia_planeta).text = resultado["distancia"].toString()
                findViewById<TextView>(R.id.cl_tamanio_planeta).text = resultado["tamanio"].toString()
                findViewById<ImageView>(R.id.cl_img_planeta).setImageResource(R.drawable.ic_launcher_background)
            }
            .addOnFailureListener {
                Toast.makeText(this,"No se cargaron los datos", Toast.LENGTH_SHORT).show()
            }
    }
}