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

class SistemaPlanetarioActivity : AppCompatActivity() {
    private lateinit var database: AppDatabase
    private lateinit var sistemaPlanetario: SistemaPlanetario
    private lateinit var sistemaPlanetarioLiveData: LiveData<SistemaPlanetario>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sistema_planetario)

        database = AppDatabase.getDatabase(this)

        val id_sistemaPlanetario = intent.getIntExtra("id", 0)


        sistemaPlanetarioLiveData = database.sistemaPlanetario().get(id_sistemaPlanetario)
        sistemaPlanetario = SistemaPlanetario("",0f,"","",0,0)
        sistemaPlanetarioLiveData.observe(this, Observer{

            sistemaPlanetario = it
            findViewById<TextView>(R.id.lv_id_sistema_planetario).text = sistemaPlanetario?.id_sistema_planetario.toString()
            findViewById<TextView>(R.id.lv_nombre_sistema_planetario).text = sistemaPlanetario?.nombre_sistema_planetario
            findViewById<TextView>(R.id.lv_formacion_sistema_planetario).text = sistemaPlanetario?.formacion_sistema_planetario.toString()
            findViewById<TextView>(R.id.lv_galaxia_sistema_planetario).text = sistemaPlanetario?.galaxia_sistema_planetario
            findViewById<TextView>(R.id.lv_tipo_sistema_planetario).text = sistemaPlanetario?.tipo_sistema_planetario
            sistemaPlanetario?.let {
                findViewById<ImageView>(R.id.lv_img_sistema_planetario).setImageResource(
                    it.imagen)
            }
        })

        if(intent.hasExtra("sistema_planetario")){
            val sistema_planetario = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.extras?.getSerializable("sistema_planetario",SistemaPlanetario::class.java)

            } else {
                TODO("VERSION.SDK_INT < TIRAMISU")
            }
            CoroutineScope(Dispatchers.IO).launch {
                if (sistema_planetario != null) {
                    database.sistemaPlanetario().delete(sistema_planetario)
                }
                this@SistemaPlanetarioActivity.finish()
            }

        }









    }
}