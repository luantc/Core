package com.luantc.core.api

import com.luantc.core.model.Comment
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface CloudService {

    @GET(ApiSettings.COMMENT)
    fun  getComment(@Path(ApiSettings.PATH_COMMENT) comment: String) : Observable<MutableList<Comment>>

}