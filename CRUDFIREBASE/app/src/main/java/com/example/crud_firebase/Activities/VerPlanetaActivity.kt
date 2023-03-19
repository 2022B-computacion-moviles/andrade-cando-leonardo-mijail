package com.example.crud_firebase.Activities

import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.crud_firebase.Entities.Planeta
import com.example.crud_firebase.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VerPlanetaActivity : AppCompatActivity() {
    private lateinit var database: AppDatabase
    private lateinit var planeta: Planeta
    private lateinit var planetaLiveData: LiveData<Planeta>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_planeta)

        val id_planeta = intent.getIntExtra("id", 0)

        database = AppDatabase.getDatabase(this)
        planetaLiveData = database.planeta().get(id_planeta)

        if(intent.hasExtra("planeta")){
            val planeta2 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.extras?.getSerializable("planeta", Planeta::class.java)

            } else {
                TODO("VERSION.SDK_INT < TIRAMISU")
            }
            CoroutineScope(Dispatchers.IO).launch {
                if (planeta2 != null) {
                    database.planeta().delete(planeta2)
                    this@VerPlanetaActivity.finish()
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
                findViewById<ImageView>(R.id.cl_img_planeta).setImageResource(R.drawable.ic_launcher_background)

            })
        }
    }
}