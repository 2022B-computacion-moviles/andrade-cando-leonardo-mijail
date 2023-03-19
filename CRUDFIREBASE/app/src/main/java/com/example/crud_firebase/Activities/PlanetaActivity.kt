package com.example.crud_firebase.Activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.crud_firebase.Adapters.PlanetasAdapter
import com.example.crud_firebase.Entities.Planeta
import com.example.crud_firebase.Entities.SistemaPlanetario
import com.example.crud_firebase.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class PlanetaActivity : AppCompatActivity() {
    var id_planeta = 0
    var listaPlaneta = mutableListOf<Planeta>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planeta)

        val id_sistema_planetario = intent.getIntExtra("id_sistema_planetario",0)

        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        db.collection("Sistema Planetario")
            .document(id_sistema_planetario.toString())
            .collection("Planeta")
            .get()
            .addOnSuccessListener {
                for (documento in it){
                    var planeta = Planeta(documento["nombre"].toString(),documento["distancia"].toString(),
                        documento["tamanio"].toString(),documento["fecha"].toString(),documento.id)
                    listaPlaneta.add(planeta)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this,"No se encontraron datos", Toast.LENGTH_SHORT).show()
            }


        val lista = findViewById<ListView>(R.id.lista_planeta)
        val adapter = PlanetasAdapter(this,listaPlaneta)
        lista.adapter = adapter

        registerForContextMenu(lista)
        lista.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, VerPlanetaActivity::class.java)
            intent.putExtra("id", listaPlaneta[position].id_planeta)
            startActivity(intent)
        }
        val agregarBoton = findViewById<FloatingActionButton>(R.id.floatingActionButton2)
        agregarBoton.setOnClickListener{
            val intent = Intent(this,NuevoPlanetaActivity::class.java )
            startActivity(intent)
        }

    }
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        //llamamos las opciones del menu
        menuInflater.inflate(R.menu.menu_planeta, menu)
        //Obtener el id del ArraylistSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        id_planeta = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when (item.itemId){
            R.id.editar_planeta ->{
                val intent = Intent(this,NuevoPlanetaActivity::class.java )
                intent.putExtra("planeta",listaPlaneta[id_planeta].id_planeta)
                startActivity(intent)
                return true
            }
            R.id.eliminar_planeta ->{
                val intent = Intent(this,VerPlanetaActivity::class.java )
                intent.putExtra("planeta",listaPlaneta[id_planeta])
                startActivity(intent)

                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }


}