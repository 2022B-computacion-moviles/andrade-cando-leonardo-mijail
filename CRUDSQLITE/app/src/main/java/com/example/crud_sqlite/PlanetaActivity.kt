package com.example.crud_sqlite

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlanetaActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planeta)

        var database= AppDatabase.getDatabase(this)
        var planeta: Planeta
        var planetaLiveData: LiveData<Planeta>
        val id_planeta = intent.getIntExtra("id", 0)

        planetaLiveData = database.planeta().get(id_planeta)

        planetaLiveData.observe(this, Observer {
            planeta = it

            findViewById<TextView>(R.id.cl_id_planeta).text = planeta?.id_planeta.toString()
            findViewById<TextView>(R.id.cl_nombre_planeta).text = planeta?.nombre_planeta
            findViewById<TextView>(R.id.cl_fecha_planeta).text = planeta?.fecha_planeta
            findViewById<TextView>(R.id.cl_distancia_planeta).text = planeta?.distancia_planeta.toString()
            findViewById<TextView>(R.id.cl_tamanio_planeta).text = planeta?.tamanio_planeta.toString()
            if (planeta != null) {
                findViewById<ImageView>(R.id.cl_img_planeta).setImageResource(planeta.imagen)
            }
        })




    }
}