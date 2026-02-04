package com.example.githubsearch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.githubsearch.R
import com.example.githubsearch.model.DashboardModel

class DashboardAdapter(
    private val onClick: (DashboardModel) -> Unit
) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    private val item = mutableListOf<DashboardModel>()
    fun submitList(data: List<DashboardModel>) {
        item.clear()
        item.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.imgIcon)
        val title: TextView = view.findViewById(R.id.titleTxt)

        fun bind(item: DashboardModel) {
            itemView.setOnClickListener {
                onClick(item)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_dashboard, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = item.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = item[position]
        holder.icon.setImageResource(item.icon)
        holder.title.setText(item.title)

        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }
}