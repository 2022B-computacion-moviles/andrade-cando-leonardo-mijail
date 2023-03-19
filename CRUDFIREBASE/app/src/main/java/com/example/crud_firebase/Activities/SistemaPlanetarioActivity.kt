package com.example.crud_firebase.Activities

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.crud_firebase.Adapters.SistemaPlanetarioAdapter
import com.example.crud_firebase.Entities.SistemaPlanetario
import com.example.crud_firebase.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class SistemaPlanetarioActivity : AppCompatActivity() {
    var id_sistema_planetario = 0
    var listaSistemaPlanetario = mutableListOf<SistemaPlanetario>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sistema_planetario)

        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        db.collection("Sistema Planetario")
            .get()
            .addOnSuccessListener { resultado ->
                for (documento in resultado){
                    var sistemaPlanetario = SistemaPlanetario(documento["nombre"].toString(),documento["formacion"].toString(),
                    documento["galaxia"].toString(),documento["tipo"].toString(),documento.id)
                    listaSistemaPlanetario.add(sistemaPlanetario)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this,it.toString(), Toast.LENGTH_SHORT).show()
            }

        val lista = findViewById<ListView>(R.id.lista_sistema_planetario)
        val adapter = SistemaPlanetarioAdapter(this,listaSistemaPlanetario)
        lista.adapter = adapter

        registerForContextMenu(lista)

        lista.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, VerSistemaPlanetarioActivity::class.java)
            intent.putExtra("id", listaSistemaPlanetario[position].id_sistema_planetario)
            startActivity(intent)
        }

        val agregarBoton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        agregarBoton.setOnClickListener{
            val intent = Intent(this,NuevoSistemaPlanetarioActivity::class.java )
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
        menuInflater.inflate(R.menu.menu_sistema_planetario, menu)

        //Obtener el id del ArraylistSeleccionado
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        id_sistema_planetario = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when (item.itemId){
            R.id.editar_sistema_planetario ->{
                val intent = Intent(this,NuevoSistemaPlanetarioActivity::class.java )
                intent.putExtra("sistema_planetario",listaSistemaPlanetario[id_sistema_planetario].id_sistema_planetario)
                startActivity(intent)
                return true
            }
            R.id.eliminar_sistema_planetario ->{
                val intent = Intent(this,SistemaPlanetarioActivity::class.java )
                intent.putExtra("sistema_planetario",listaSistemaPlanetario[id_sistema_planetario])
                startActivity(intent)
                return true
            }
            R.id.ver_planetas ->{
                val intent = Intent(this,VerPlanetaActivity::class.java )
                intent.putExtra("id_sistema_planetario",listaSistemaPlanetario[id_sistema_planetario].id_sistema_planetario)
                startActivity(intent)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}