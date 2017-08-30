package com.dnalves.giphy.network

import com.dnalves.giphy.BuildConfig
import com.google.gson.annotations.SerializedName
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

data class DataEnvelope<T>(
        @SerializedName("data")
        val data: T
)

object GiphyNetwork {

    private val URL = "https://api.giphy.com/v1/gifs/"

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
                .addInterceptor(GiphyRequestInterceptor(BuildConfig.GiphyApiKey))
                .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    val giphyService: GiphyService by lazy {
        retrofit.create(GiphyService::class.java)
    }

}

class GiphyRequestInterceptor(val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        val request = chain!!.request()
        val originalUrl = request.url()

        val newUrl = originalUrl.newBuilder()
                .addQueryParameter("api_key", apiKey)
                .build()

        return chain.proceed(request.newBuilder().url(newUrl).build())

    }

}

data class DataGif(

        @SerializedName("type")
        val type: String,

        @SerializedName("id")
        val id:String,

        @SerializedName("image_url")
        val imageUrl: String,

        @SerializedName("images")
        val images: DataImages

)

data class DataImages(

        @SerializedName("fixed_height_small")
        val fixedHeightSmall: FixedHeightSmallImage

)

data class FixedHeightSmallImage(

        @SerializedName("url")
        val url: String

)
