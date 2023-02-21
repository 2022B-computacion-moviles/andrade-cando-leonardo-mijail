package com.example.messenger

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(
    private val userList: List<Usuario>,
    private val contexto: MainActivity,
    private val recycler: RecyclerView

) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resource = userList[position].imageview1
        val name = userList[position].textview1
        val msg = userList[position].textview3
        val time = userList[position].textview2
        val line = userList[position].divider
        holder.setData(resource, name, msg, time, line)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView
        private val textView: TextView
        private val textView2: TextView
        private val textView3: TextView
        private val divider: TextView

        init {
            imageView = itemView.findViewById(R.id.imageview1)
            textView = itemView.findViewById(R.id.textview)
            textView2 = itemView.findViewById(R.id.textview2)
            textView3 = itemView.findViewById(R.id.textview3)
            divider = itemView.findViewById(R.id.divider)
        }

        fun setData(resource: Int, name: String?, msg: String?, time: String?, line: String?) {
            imageView.setImageResource(resource)
            textView.text = name
            textView3.text = msg
            textView2.text = time
            divider.text = line
        }
    }
}