package com.training.jetdemo.blogs.blogs

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.training.jetdemo.R
import com.training.jetdemo.base.BaseItemViewHolder
import com.training.jetdemo.data.local.db.entity.BlogEntity
import com.training.jetdemo.di.component.ViewHolderComponent
import kotlinx.android.synthetic.main.item_view_blog.view.*

class BlogItemViewHolder(parent: ViewGroup) :
    BaseItemViewHolder<BlogEntity, BlogItemViewModel>(R.layout.item_view_blog, parent) {

    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.name.observe(this, Observer {
            itemView.tvName.text = it
        })

        viewModel.userDesignation.observe(this, Observer {
            itemView.tvDesignation.text = it
        })

        viewModel.commentCount.observe(this, Observer {
            itemView.tvCommentsCount.text = "$it Comments"
        })


        viewModel.likesCount.observe(this, Observer {
            itemView.tvLikeCount.text = "$it Likes"
        })

        viewModel.postTime.observe(this, Observer {
            itemView.tvBlogTime.text = it
        })

        viewModel.postBody.observe(this, Observer {
            itemView.tvBlogContent.text = it
        })

        viewModel.postImage.observe(this, Observer {
            Glide.with(itemView.ivBlogImage.context)
                .load(it.toString())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(itemView.ivBlogImage)
        })

        viewModel.avatar.observe(this, Observer {

            Glide.with(itemView.ivAvatar.context)
                .load(it)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(itemView.ivAvatar)

        })



    }

    override fun setupView(view: View) {
    }
}