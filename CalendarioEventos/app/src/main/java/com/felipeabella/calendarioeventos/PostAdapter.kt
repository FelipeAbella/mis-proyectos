package com.felipeabella.calendarioeventos

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PostAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewContent: TextView = itemView.findViewById(R.id.textViewContent)
        val textViewDate: TextView = itemView.findViewById(R.id.textViewDate)
        val buttonLike: ImageView = itemView.findViewById(R.id.buttonLike)
        val buttonDislike: ImageView = itemView.findViewById(R.id.buttonDislike)
        val imageViewPost: ImageView = itemView.findViewById(R.id.imageViewPost)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]
        holder.textViewContent.text = post.content
        holder.textViewDate.text = post.date

        post.imageUri?.let { uri ->
            Glide.with(holder.itemView.context)
                .load(uri)
                .into(holder.imageViewPost)
        } ?: run {
            holder.imageViewPost.visibility = View.GONE
        }

        holder.buttonLike.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Me gusta: ${post.content}", Toast.LENGTH_SHORT)
                .show()
        }

        holder.buttonDislike.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "No me gusta: ${post.content}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount() = posts.size
}