package com.example.movcomplmac

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FRecyclerViewAdaptadorNombreCedula(
    private val contexto: GRecyclerView,
    private val lista: ArrayList<BEntrenador>,
    private val recycler: RecyclerView
): RecyclerView.Adapter<FRecyclerViewAdaptadorNombreCedula.MyViewHolder>() {
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nombreTextView: TextView
        val cedulaTextView: TextView
        val likeTextView: TextView
        val accionButton: Button
        var numeroLikes = 0
        init {
            nombreTextView = view.findViewById(R.id.tv_nombre)
            cedulaTextView = view.findViewById(R.id.tv_cedula)
            likeTextView = view.findViewById(R.id.tv_likes)
            accionButton = view.findViewById(R.id.btn_dar_like)
            accionButton.setOnClickListener{anadirLike()}
        }
        fun anadirLike(){
            numeroLikes = numeroLikes + 1
            likeTextView.text = numeroLikes.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_view_vista,
            parent,
            false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val entrenadorActual = this.lista[position]
        holder.nombreTextView.text = entrenadorActual.nombre
        holder.cedulaTextView.text = entrenadorActual.email
        holder.accionButton.text = "Like ${entrenadorActual.nombre}"
        holder.likeTextView.text = "0"
    }

    override fun getItemCount(): Int {
        return this.lista.size
    }


}


