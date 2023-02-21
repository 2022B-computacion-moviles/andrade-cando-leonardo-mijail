package com.example.messenger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class ChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        var listaChat = arrayListOf<Chat>()
        listaChat.add(Chat(R.drawable.rick,"Hola","10:40 AM"))
        listaChat.add(Chat(R.drawable.rick,"Confirma","10:40 AM"))


        val recyclerView = findViewById<RecyclerView>(R.id.chatRecyclerView)
        inicializarRecyclerView(listaChat,recyclerView)
    }

    private fun inicializarRecyclerView(listaChat: ArrayList<Chat>, recyclerView: RecyclerView) {
        val adaptador = AdapterChat(listaChat,this, recyclerView)
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        adaptador.notifyDataSetChanged()



    }

}