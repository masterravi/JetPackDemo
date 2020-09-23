package com.training.jetdemo.data.remote

import com.training.jetdemo.data.local.db.entity.BlogEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {
    @GET(Endpoints.BLOGS)
    fun getBlogList(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Single<List<BlogEntity>>
}