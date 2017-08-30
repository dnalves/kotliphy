package com.dnalves.giphy.network

import retrofit2.Call
import retrofit2.http.GET

interface GiphyService {

    @GET("random")
    fun random(): Call<DataEnvelope<DataRandom>>

}
