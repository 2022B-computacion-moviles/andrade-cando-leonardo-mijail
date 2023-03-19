package com.example.crud_firebase.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.crud_firebase.Entities.Planeta
import com.example.crud_firebase.R

class PlanetasAdapter (
    private val mContext: Context,
    private val listaPlanetas: List<Planeta>
) : ArrayAdapter<Planeta>(mContext, 0,listaPlanetas) {

    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layout = LayoutInflater.from(mContext).inflate(R.layout.item_planeta,parent,false)
        val planeta = listaPlanetas[position]



        layout.findViewById<TextView>(R.id.id_planeta).text = planeta.id_planeta.toString()
        layout.findViewById<TextView>(R.id.nombre_planeta).text = planeta.nombre_planeta
        layout.findViewById<TextView>(R.id.distancia_planeta).text = planeta.distancia_planeta.toString()
        layout.findViewById<TextView>(R.id.fecha_planeta).text = planeta.fecha_planeta
        layout.findViewById<TextView>(R.id.tamanio_planeta).text = planeta.tamanio_planeta.toString()
        layout.findViewById<ImageView>(R.id.img_planeta).setImageResource(R.drawable.ic_launcher_background)

        return layout
    }
}