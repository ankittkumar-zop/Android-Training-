package com.example.project_1.ui.showPost.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_1.R
import com.example.project_1.data.remote.showPost.ShowPost
import com.example.project_1.data.remote.showPost.ShowPostData
import retrofit2.http.POST

class PostAdapter(private var posts : List<ShowPostData>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    class PostViewHolder(view : View): RecyclerView.ViewHolder(view.rootView){

        private val itemTitleTextView: TextView = view.findViewById(R.id.titleTextView)
        private val itemPostImageView: ImageView = view.findViewById(R.id.imageView)

        fun setPost(post: ShowPostData){
            with(post){
                itemTitleTextView.text = title
                Glide.with(itemView.context).load(post.url).into(itemPostImageView)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sample_view, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.setPost(posts[position])
    }
}