package com.example.messenger

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class AdapterChat(
    private val chatList: List<Chat>,
    private val contexto: ChatActivity,
    private val recycler: RecyclerView

) : RecyclerView.Adapter<AdapterChat.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_container_sent_message, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val img = chatList[position].imageView
        val txt = chatList[position].textview1
        val time = chatList[position].textview2


        holder.setData(img,txt,time)

    }

    override fun getItemCount(): Int {
        return chatList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textView1: TextView
        private val textView2: TextView
        private val imageView: ImageView



        init {
            textView1 = itemView.findViewById(R.id.textMessage)
            textView2 = itemView.findViewById(R.id.textDateTimeSend)
            imageView = itemView.findViewById(R.id.imageview15)


        }

        fun setData(img:Int,text: String?, time: String?) {
            imageView.setImageResource(img)
            textView1.text = text
            textView2.text = time

        }

    }
}