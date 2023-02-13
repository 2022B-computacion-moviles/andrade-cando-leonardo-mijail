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
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.google.android.material.floatingactionbutton.FloatingActionButton


class SistemaPlanetarioMainActivity : AppCompatActivity() {

    var idItemSeleccionado = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sistema_planetario_main)

        var listaSistemaPlanetario = emptyList<SistemaPlanetario>()
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
            intent.putExtra("sistemaPlanetario", listaSistemaPlanetario[position])
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
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when (item.itemId){
            R.id.editar_sistema_planetario ->{
                "${idItemSeleccionado}"
                return true
            }
            R.id.eliminar_sistema_planetario ->{
                abrirDialogo()
                "${idItemSeleccionado}"
                return true
            }
            R.id.ver_planetas ->{
                "${idItemSeleccionado}"
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