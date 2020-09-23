package com.training.jetdemo.di.component

import com.training.jetdemo.di.ViewHolderScope
import com.training.jetdemo.di.module.ViewHolderModule
import com.training.jetdemo.blogs.blogs.BlogItemViewHolder

import dagger.Component

@ViewHolderScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

    fun inject(viewHolder: BlogItemViewHolder)
}