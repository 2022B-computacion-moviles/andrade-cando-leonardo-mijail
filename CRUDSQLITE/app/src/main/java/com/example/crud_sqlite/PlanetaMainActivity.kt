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

class PlanetaMainActivity : AppCompatActivity() {
    var idItemSeleccionado = 0
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.planeta_activity_main)

        var listaPlaneta = emptyList<Planeta>()
        val lista = findViewById<ListView>(R.id.lista_planeta)

        val database = AppDatabase.getDatabase(this)
        database.planeta().gatAll().observe(this, Observer {
            listaPlaneta = it
            val adapter = PlanetasAdapter(this,listaPlaneta)
            lista.adapter = adapter


        })
        registerForContextMenu(lista)
        lista.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, PlanetaActivity::class.java)
            intent.putExtra("planeta", listaPlaneta[position])
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
        idItemSeleccionado = id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        return when (item.itemId){
            R.id.editar_planeta ->{
                "${idItemSeleccionado}"
                return true
            }
            R.id.eliminar_planeta ->{
                abrirDialogo()
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