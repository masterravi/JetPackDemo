package  com.training.jetdemo.blogs

import androidx.lifecycle.MutableLiveData
import com.training.jetdemo.base.BaseViewModel
import com.training.jetdemo.data.local.db.entity.BlogEntity
import com.training.jetdemo.data.repository.PostRepository
import com.training.jetdemo.utils.common.Resource
import com.training.jetdemo.utils.network.NetworkHelper
import com.training.jetdemo.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers

class BlogViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val postRepository: PostRepository,
    private val allRepoList: ArrayList<BlogEntity>,
    private val paginator: PublishProcessor<Int>
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val loading: MutableLiveData<Boolean> = MutableLiveData()
    private var  PAGE_NO:Int=1
    private val  LIMIT = 10
    val repos: MutableLiveData<Resource<List<BlogEntity>>> = MutableLiveData()

    init {

        compositeDisposable.add(
            paginator
                .onBackpressureDrop()
                .doOnNext {
                    loading.postValue(true)
                }
                .concatMapSingle { lastPageId ->
                    return@concatMapSingle postRepository
                        .getPostList(lastPageId, LIMIT)
                        .subscribeOn(Schedulers.io())
                        .doOnError {
                            handleNetworkError(it)
                        }
                }
                .subscribe(
                    {
                        if(networkHelper.isNetworkConnected()){
                            if(it.isNotEmpty() && it.size==10){
                                allRepoList.addAll(it)

                                postRepository.saveRepoList(allRepoList)
                                loading.postValue(false)
                                repos.postValue(Resource.success(allRepoList))
                            }else{
                                loading.value=false
                            }
                        }else{
                            allRepoList.addAll(it)
                            loading.postValue(false)
                            repos.postValue(Resource.success(allRepoList))
                            loading.value=false
                        }

                    },
                    {
                        handleNetworkError(it)
                    }
                )
        )
    }

    override fun onCreate() {
        loadMorePosts()
    }

    private fun loadMorePosts() {

        if (checkInternetConnectionWithMessage()) paginator.onNext(   if (allRepoList.size%10==0) PAGE_NO++ else 1)
    }

    fun onLoadMore() {
        if (loading.value !== null && loading.value == false) loadMorePosts()
    }
}
