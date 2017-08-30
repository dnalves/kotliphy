package com.dnalves.giphy.network

import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.sql.Types

class GiphyConverter<T>(private val delegate: Converter<ResponseBody, DataEnvelope<T>>) : Converter<ResponseBody, T> {

    override fun convert(value: ResponseBody?): T = delegate.convert(value).data

}

class GiphyConverterFactory : Converter.Factory() {

    override fun responseBodyConverter(type: Type?,
                                       annotations: Array<out Annotation>?,
                                       retrofit: Retrofit?): Converter<ResponseBody, *>? {
//
//        Type
//        retrofit.nextResponseBodyConverter(this, , annotations)

        return super.responseBodyConverter(type, annotations, retrofit)
    }
}