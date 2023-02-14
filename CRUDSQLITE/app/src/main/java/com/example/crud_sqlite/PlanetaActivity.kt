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

    private lateinit var database: AppDatabase
    private lateinit var planeta: Planeta
    private lateinit var planetaLiveData: LiveData<Planeta>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planeta)

        val id_planeta = intent.getIntExtra("id", 0)

        database = AppDatabase.getDatabase(this)
        planetaLiveData = database.planeta().get(id_planeta)

        if(intent.hasExtra("planeta")){
            val planeta2 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.extras?.getSerializable("planeta",Planeta::class.java)

            } else {
                TODO("VERSION.SDK_INT < TIRAMISU")
            }
            CoroutineScope(Dispatchers.IO).launch {
                if (planeta2 != null) {
                    database.planeta().delete(planeta2)
                    this@PlanetaActivity.finish()
                }
            }

        }else{
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
}