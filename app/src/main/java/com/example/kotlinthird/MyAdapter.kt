package com.example.kotlinthird

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(var list: ArrayList<TodoModel>, val context: Context) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_items, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.text.setText(list.get(position).title)
        Picasso.get().load(list.get(position).url).placeholder(R.drawable.ic_launcher_background)
            .into(holder.img)
        Picasso.get().load(list.get(position).thumbnailUrl)
            .placeholder(R.drawable.ic_launcher_background).into(holder.post)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var text: TextView = view.findViewById(R.id.text)
        var img: ImageView = view.findViewById(R.id.img_one)
        var post: ImageView = view.findViewById(R.id.post)
    }
}