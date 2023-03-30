package com.example.fragments

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerAdapter ( private val names: List<String>) : RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>()
{
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val largeTextView: TextView = itemView.findViewById(R.id.itemTextViewLarge)
        val smallTextView: TextView = itemView.findViewById(R.id.itemTextViewSmall)
        val button : ImageButton = itemView.findViewById(R.id.itemListButton)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int)  {
        holder.largeTextView.text = names[position]
        holder.smallTextView.text = "underline"
        holder.button.setOnClickListener {
           Log.d("RecyclerView", "onButtonClick $position")

        }
    }

    override fun getItemCount() = names.size
}