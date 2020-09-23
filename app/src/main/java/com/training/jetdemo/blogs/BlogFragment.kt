package com.training.jetdemo.blogs

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.training.jetdemo.R
import com.training.jetdemo.base.BaseFragment
import com.training.jetdemo.di.component.FragmentComponent
import com.training.jetdemo.blogs.blogs.BlogsAdapter
import kotlinx.android.synthetic.main.fragment_blog.*
import javax.inject.Inject

class BlogFragment : BaseFragment<BlogViewModel>(){

    companion object{
        val TAG="PostFragment"

        fun newInstance():BlogFragment{
            val args=Bundle()
            val fragment=BlogFragment()
            fragment.arguments=args
            return  fragment
        }
    }

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var postAdapter: BlogsAdapter


    override fun provideLayoutId():Int= R.layout.fragment_blog

    override fun injectDependencies(fragmentComponent: FragmentComponent)=
       fragmentComponent.inject(this)

    override fun setupObservers() {
        super.setupObservers()

        viewModel.repos.observe(this, Observer {
            it.data?.run { postAdapter.appendData(this) }
        })
    }

    override fun setupView(view: View) {
        rvPosts.apply {
            layoutManager = linearLayoutManager
            adapter = postAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    layoutManager?.run {
                        if (this is LinearLayoutManager
                            && itemCount > 0
                            && itemCount == findLastVisibleItemPosition() + 1
                        ) viewModel.onLoadMore()
                    }
                }
            })
        }
    }

}