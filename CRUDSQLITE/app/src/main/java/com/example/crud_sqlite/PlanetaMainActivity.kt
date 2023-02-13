package com.example.crud_sqlite

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PlanetaMainActivity : AppCompatActivity() {
    var id_planeta = 0
    var listaPlaneta = emptyList<Planeta>()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.planeta_activity_main)


        val lista = findViewById<ListView>(R.id.lista_planeta)

        val id_sistema_planetario = intent.getIntExtra("id_sistema_planetario",0) + 1

        val database = AppDatabase.getDatabase(this)
        database.planeta().getAll(id_sistema_planetario).observe(this, Observer {
            listaPlaneta = it
            val adapter = PlanetasAdapter(this,listaPlaneta)
            lista.adapter = adapter


        })
        registerForContextMenu(lista)
        lista.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, PlanetaActivity::class.java)
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
                intent.putExtra("planeta",listaPlaneta[id_planeta])
                startActivity(intent)
                return true
            }
            R.id.eliminar_planeta ->{
                val intent = Intent(this,PlanetaActivity::class.java )
                intent.putExtra("borrar",1)
                startActivity(intent)

                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Desea Eliminar")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{ dialog,
                                             which ->
                //Al aceptar eliminar el registro
            }
        )
        builder.setNegativeButton("Cancelar",null)

    }
}