package com.example.daggerpractice2.ui.main.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.daggerpractice2.models.Post

import java.util.ArrayList
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerpractice2.R

class PostRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var posts: List<Post> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_post_list_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostViewHolder).bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun setPosts(posts: List<Post>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var title: TextView

        init {
            title = itemView.findViewById(R.id.title)
        }

        fun bind(post: Post) {
            title.text = post.title
        }
    }
}