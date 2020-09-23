package com.training.jetdemo.data.repository

import com.training.jetdemo.data.local.db.DatabaseService
import com.training.jetdemo.data.local.db.entity.BlogEntity
import com.training.jetdemo.data.remote.NetworkService
import com.training.jetdemo.utils.network.NetworkHelper
import io.reactivex.Single
import javax.inject.Inject


class PostRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService,
    private val networkHelper: NetworkHelper
) {

    fun getPostList(page: Int,limit:Int): Single<List<BlogEntity>>{
        if(networkHelper.isNetworkConnected()){
            return networkService.getBlogList(page,limit).map { it }
        }else{
            return  databaseService.repoDao().getAll()
        }
    }


    fun saveRepoList(repoList:List<BlogEntity>){
        databaseService.repoDao().insertAll(repoList)
    }


}