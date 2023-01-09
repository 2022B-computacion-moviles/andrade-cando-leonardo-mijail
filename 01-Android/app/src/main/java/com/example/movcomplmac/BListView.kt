package com.example.movcomplmac

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class BListView : AppCompatActivity() {
    var arreglo:ArrayList<BEntrenador> = BBaseDatosMemoria.arregloBEntrenador
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)
        val listView = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1, //como se va ver (XML)
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonAñadirListView = findViewById<Button>(R.id.btn_anadir_list_view)
        botonAñadirListView.setOnClickListener{
            añadirEntrenador(adaptador)}
    }
    fun añadirEntrenador(adaptador:ArrayAdapter<BEntrenador>){
        arreglo.add(
            BEntrenador(1,"Ejemplo","e@e.com")
        )
        adaptador.notifyDataSetChanged()
    }
}