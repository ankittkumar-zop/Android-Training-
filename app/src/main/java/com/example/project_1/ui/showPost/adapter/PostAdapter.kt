package com.example.project_1.ui.showPost.adapter

import android.annotation.SuppressLint
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_1.R
import com.example.project_1.data.remote.showPost.ShowPostData

class PostAdapter(private var posts : List<ShowPostData>, private val onLikeClick:(Int) -> Unit ): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {


    class PostViewHolder(view : View): RecyclerView.ViewHolder(view){
         val itemTitleTextView: TextView = view.findViewById(R.id.titleTextView)
         val itemPostImageView: ImageView = view.findViewById(R.id.imageView)
         val likeButton: ImageView = view.findViewById(R.id.btnLike)

//        fun setPost(post: ShowPostData , onLikeClick: (Int) -> Unit){
////                itemTitleTextView.text = post.title
////                Glide.with(post.view.context).load(post.url).into(itemPostImageView)
//            val currentPost = post.postId
//            itemTitleTextView.text = post.title
//            Glide.with(post
//            )
//        }
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
//        holder.setPost(posts[position], onLikeClick)
        val current = posts[position]
        holder.itemTitleTextView.text = current.title
        Glide.with(holder.itemView.context).load(current.url).into(holder.itemPostImageView)
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

