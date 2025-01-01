package ru.mrfix1033.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class CustomAdapter(private val list: MutableList<GarderobeElement>) :
    RecyclerView.Adapter<CustomAdapter.GarderobeElementHolderView>() {
    class GarderobeElementHolderView(itemView: View) : ViewHolder(itemView) {
        val textViewName = itemView.findViewById<TextView>(R.id.textViewName)
        val textViewDescription = itemView.findViewById<TextView>(R.id.textViewDescription)
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GarderobeElementHolderView {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.garderobe_item, parent, false)!!
        return GarderobeElementHolderView(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GarderobeElementHolderView, position: Int) {
        list[position].run {
            holder.run {
                textViewName.setText(name)
                textViewDescription.setText(description)
                imageView.setImageResource(image)
            }
        }
    }
}