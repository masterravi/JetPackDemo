package com.training.jetdemo.blogs.blogs

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.training.jetdemo.base.BaseItemViewModel
import com.training.jetdemo.data.local.db.entity.BlogEntity
import com.training.jetdemo.data.repository.PostRepository
import com.training.jetdemo.utils.common.TimeUtils
import com.training.jetdemo.utils.log.Logger
import com.training.jetdemo.utils.network.NetworkHelper
import com.training.jetdemo.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class BlogItemViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val postRepository: PostRepository
) : BaseItemViewModel<BlogEntity>(schedulerProvider, compositeDisposable, networkHelper) {

    companion object {
        const val TAG = "PostItemViewModel"
    }

    val name: LiveData<String> = Transformations.map(data){ if(!it.user.isNullOrEmpty()) it.user[0].name+" ${it.user[0].lastname}" else ""}
    val userDesignation: LiveData<String> = Transformations.map(data) { if(!it.user.isNullOrEmpty()) it.user[0].designation else ""}
    val postBody: LiveData<String> = Transformations.map(data) { if(!it.content.isNullOrEmpty()) it.content else ""}
    val postImage: LiveData<String> = Transformations.map(data) { if(!it.media.isNullOrEmpty()) it.media[0].image else ""}
    val avatar: LiveData<String> = Transformations.map(data) { if(!it.user.isNullOrEmpty()) it.user[0].avatar else ""}
    val postTime: LiveData<String> = Transformations.map(data) { TimeUtils.getTimeAgo(TimeUtils.stringToDate(it.createdAt!!)) }
    val likesCount: LiveData<Int> = Transformations.map(data) { it.likes?: 0 }
    val commentCount: LiveData<Int> = Transformations.map(data) { it.comments?: 0 }

    override fun onCreate() {
        Logger.d(TAG, "onCreate called")
    }


}