package com.example.project_1.ui.showPost.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_1.R
import com.example.project_1.data.remote.showPost.ShowPostData

class PostAdapter(private val context : Context, private var posts : List<ShowPostData>, private val onLikeClick:(Int) -> Unit ): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {


    class PostViewHolder(view : View): RecyclerView.ViewHolder(view){
         val itemTitleTextView: TextView = view.findViewById(R.id.titleTextView)
         val itemPostImageView: ImageView = viwqew.findViewById(R.id.imageView)
         val likeButton: ImageView = view.findViewById(R.id.btnLike)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.show_post_recycler_view, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val current = posts[position]
        holder.itemTitleTextView.text = current.title
        Glide.with(holder.itemView.context).load(current.url).into(holder.itemPostImageView)
            holder.likeButton.setImageDrawable(ContextCompat.getDrawable(context, if(current.isLiked) R.drawable.liked_icon else R.drawable.ic_favourite_button))
        holder.likeButton.setOnClickListener {
            onLikeClick(current.postId)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newPost : List<ShowPostData>){
        posts = newPost
        notifyDataSetChanged()
    }
}

