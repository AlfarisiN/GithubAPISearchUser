package com.example.githubsearch.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubsearch.R
import com.example.githubsearch.local.UserEntity

class UserListAdapter(
    private val onClick: (UserEntity) -> Unit
): RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private val users = mutableListOf<UserEntity>()

    fun submitList(data: List<UserEntity>) {
        users.clear()
        users.addAll(data)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatar: ImageView = view.findViewById(R.id.imgAvatar)
        val username: TextView = view.findViewById(R.id.txtUsername)
        val type: TextView = view.findViewById(R.id.txtType)
        val viewType: TextView = view.findViewById(R.id.txtViewType)
        val cardUser: CardView = view.findViewById(R.id.cardUser)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.username.text = user.login
        Glide.with(holder.itemView)
            .load(user.avatar_url)
            .into(holder.avatar)
        holder.viewType.text = user.user_view_type
        holder.type.text = user.type
        holder.cardUser.setOnClickListener {
            onClick(user)
        }
    }


}