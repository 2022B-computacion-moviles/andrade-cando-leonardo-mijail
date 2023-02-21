package com.example.messenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        var listaUsuarios = arrayListOf<Usuario>()
        listaUsuarios.add(Usuario(R.drawable.rick,"Ricardo","10:40 AM","Hola","_________________________________________________"))
        listaUsuarios.add(Usuario(R.drawable.alex,"Alexander","10:40 AM","Confirma","_________________________________________________"))
                      
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        inicializarRecyclerView(listaUsuarios,recyclerView)
               
    }

    private fun inicializarRecyclerView(listaUsuarios: ArrayList<Usuario>, recyclerView: RecyclerView) {
        val adaptador = Adapter(listaUsuarios,this, recyclerView)
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()


    }


}