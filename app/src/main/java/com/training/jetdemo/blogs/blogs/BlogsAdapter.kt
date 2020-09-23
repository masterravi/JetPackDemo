package com.training.jetdemo.blogs.blogs

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.training.jetdemo.data.local.db.entity.BlogEntity

class BlogsAdapter(
    parentLifecycle: Lifecycle,
    posts: ArrayList<BlogEntity>
) : com.training.jetdemo.base.BaseAdapter<BlogEntity, BlogItemViewHolder>(parentLifecycle, posts) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BlogItemViewHolder(parent)
}