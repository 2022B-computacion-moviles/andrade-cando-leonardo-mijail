package com.example.crud_firebase.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.crud_firebase.Entities.SistemaPlanetario
import com.example.crud_firebase.R

class SistemaPlanetarioAdapter(
    private val mContext: Context,
    private val listaSistemaPlanetario: List<SistemaPlanetario>
) : ArrayAdapter<SistemaPlanetario>(mContext, 0,listaSistemaPlanetario) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_sistema_planetario,parent,false)

        val sistemaPlanetario = listaSistemaPlanetario[position]
        layout.findViewById<TextView>(R.id.id_sistema_planetario).text = sistemaPlanetario.id_sistema_planetario
        layout.findViewById<TextView>(R.id.nombre_sistema_planetario).text = sistemaPlanetario.nombre_sistema_planetario
        layout.findViewById<TextView>(R.id.galaxia_sistema_planetario).text = sistemaPlanetario.galaxia_sistema_planetario
        layout.findViewById<TextView>(R.id.formacion_sistema_planetario).text = sistemaPlanetario.formacion_sistema_planetario
        layout.findViewById<TextView>(R.id.tipo_sistema_planetario).text = sistemaPlanetario.tipo_sistema_planetario
        layout.findViewById<ImageView>(R.id.img_sistema_planetario).setImageResource(R.drawable.ic_launcher_background)
        return layout
    }
}