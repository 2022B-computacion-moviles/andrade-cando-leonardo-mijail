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
import com.example.crud_firebase.Adapters.PlanetasAdapter
import com.example.crud_firebase.Entities.Planeta
import com.example.crud_firebase.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore

class PlanetaActivity : AppCompatActivity() {
    var id_planeta = 0
    var listaPlaneta = mutableListOf<Planeta>()
    var intent_id_sistema_planetario = ""
    var id_sistema_planetario = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planeta)

        intent_id_sistema_planetario = intent.getStringExtra("id_sistema_planetario").toString()
        id_sistema_planetario = intent_id_sistema_planetario
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        db.collection("Sistema Planetario").document(intent_id_sistema_planetario)
            .collection("Planeta")
            .get()
            .addOnSuccessListener {
                for (documento in it){
                    var planeta = Planeta(documento["nombre"].toString(),documento["distancia"].toString(),
                        documento["tamanio"].toString(),documento["fecha"].toString(),documento.id)
                    listaPlaneta.add(planeta)
                }
                val lista = findViewById<ListView>(R.id.lista_planeta)
                val adapter = PlanetasAdapter(this,listaPlaneta)
                lista.adapter = adapter
                adapter.notifyDataSetChanged()
                registerForContextMenu(lista)
                lista.setOnItemClickListener { parent, view, position, id ->
                    val intent = Intent(this, VerPlanetaActivity::class.java)
                    intent.putExtra("id", listaPlaneta[position].id_planeta)
                    startActivity(intent)
                }
            }
            .addOnFailureListener {
                Toast.makeText(this,"No se encontraron datos", Toast.LENGTH_SHORT).show()
            }

        val agregarBoton = findViewById<FloatingActionButton>(R.id.floatingActionButton2)
        agregarBoton.setOnClickListener{
            val intent = Intent(this,NuevoPlanetaActivity::class.java)
            intent.putExtra("id_sistemaPlanetario",intent.getStringExtra("id_sistema_planetario").toString())
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
                intent.putExtra("id_sistemaPlanetario",intent_id_sistema_planetario)
                startActivity(intent)
                return true
            }
            R.id.eliminar_planeta ->{
                val db: FirebaseFirestore = FirebaseFirestore.getInstance()
                db.collection("Sistema Planetario")
                    .document(intent_id_sistema_planetario)
                    .collection("Planeta")
                    .document(listaPlaneta[id_planeta].id_planeta)
                    .delete()
                    .addOnSuccessListener {
                        Toast.makeText(this,"Borrado Exitoso", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,PlanetaActivity::class.java))
                    }
                    .addOnFailureListener {
                        Toast.makeText(this,"No se pudo borrar", Toast.LENGTH_SHORT).show()
                    }
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }


}