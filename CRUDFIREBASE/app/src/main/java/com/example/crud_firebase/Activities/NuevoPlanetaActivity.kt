package com.example.crud_firebase.Activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crud_firebase.Entities.Planeta
import com.example.crud_firebase.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NuevoPlanetaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_planeta)
        var id_planeta: String? = null
        var id_sistemaPlanetario :String? =null

        if (intent.hasExtra("planeta")){

            val planeta = intent.getStringExtra("planeta")
            val sistema_planetario = intent.getStringExtra("id_sistemaPlanetario")

            val db: FirebaseFirestore = FirebaseFirestore.getInstance()

            db.collection("Sistema Planetario")
                .document(sistema_planetario.toString())
                .collection("Planeta")
                .document(planeta.toString())
                .get()
                .addOnSuccessListener {resultado ->

                    findViewById<TextView>(R.id.t_nombre_planeta).text = resultado["nombre"].toString()
                    findViewById<TextView>(R.id.t_distancia_planeta).text = resultado["fecha"].toString()
                    findViewById<TextView>(R.id.t_tamanio_planeta).text =resultado["distancia"].toString()
                    findViewById<TextView>(R.id.t_fecha_planeta).text = resultado["tamanio"].toString()
                    id_planeta = planeta.toString()
                    id_sistemaPlanetario = sistema_planetario.toString()

                }
                .addOnFailureListener {
                    Toast.makeText(this,"Error al cargar", Toast.LENGTH_SHORT).show()
                }
        }

        val btn_guardar = findViewById<Button>(R.id.btn_guardar_planeta)
        btn_guardar.setOnClickListener {
            val nombre = findViewById<TextView>(R.id.t_nombre_planeta).text.toString()
            val distancia = findViewById<TextView>(R.id.t_distancia_planeta).text.toString()
            val tamanio = findViewById<TextView>(R.id.t_tamanio_planeta).text.toString()
            val fecha = findViewById<TextView>(R.id.t_fecha_planeta).text.toString()

            val dato = hashMapOf(
                "nombre" to nombre,
                "distancia" to distancia,
                "tamanio" to tamanio,
                "fecha" to fecha
            )

            if(id_planeta != null){
                //update
                val db: FirebaseFirestore = FirebaseFirestore.getInstance()
                db.collection("Sistema Planetario")
                    .document(id_sistemaPlanetario.toString())
                    .collection("Planeta")
                    .document(id_planeta.toString())
                    .set(dato)
                    .addOnSuccessListener {
                        Toast.makeText(this,"Actualizado",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,PlanetaActivity::class.java))
                    }
                    .addOnFailureListener {
                        Toast.makeText(this,"Error al Actualizar",Toast.LENGTH_SHORT).show()
                    }

            }else{
               //insert
                val db: FirebaseFirestore = FirebaseFirestore.getInstance()
                db.collection("Sistema Planetario")
                    .document(id_sistemaPlanetario.toString())
                    .collection("Planeta")
                    .add(dato)
                    .addOnSuccessListener {
                        Toast.makeText(this,"Resgistro gurdadao",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,PlanetaActivity::class.java))

                    }
                    .addOnFailureListener {
                        Toast.makeText(this,"Error al guardar",Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}