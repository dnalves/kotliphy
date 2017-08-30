package com.dnalves.giphy.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyService {

    @GET("random")
    fun random(): Call<DataEnvelope<DataGif>>

    @GET("trending")
    fun trending(@Query("limit") limit: Int = 25): Call<DataEnvelope<List<DataGif>>>

}
