package com.example.crud_sqlite

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton


class SistemaPlanetarioMainActivity : AppCompatActivity() {

    var id_sistema_planetario = 0
    var listaSistemaPlanetario = emptyList<SistemaPlanetario>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sistema_planetario_main)


        val lista = findViewById<ListView>(R.id.lista_sistema_planetario)

        val database = AppDatabase.getDatabase(this)

        database.sistemaPlanetario().gatAll().observe(this, Observer {
            listaSistemaPlanetario = it
            val adapter = SistemaPlanetarioAdapter(this,listaSistemaPlanetario)
            lista.adapter = adapter


        })
        registerForContextMenu(lista)

        lista.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, SistemaPlanetarioActivity::class.java)
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
                intent.putExtra("sistema_planetario",listaSistemaPlanetario[id_sistema_planetario])
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
                val intent = Intent(this,PlanetaMainActivity::class.java )
                intent.putExtra("id_sistema_planetario",listaSistemaPlanetario[id_sistema_planetario].id_sistema_planetario)


                startActivity(intent)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}