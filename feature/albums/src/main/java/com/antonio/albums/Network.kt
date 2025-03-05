package com.antonio.albums

import android.content.Context
import coil.ImageLoader
import okhttp3.OkHttpClient
import okhttp3.Request

internal object Network {
    fun getImageLoader(context: Context): ImageLoader {
        return ImageLoader.Builder(context = context)
            .okHttpClient {
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val original: Request = chain.request()
                        val request: Request = original.newBuilder()
                            .header("User-Agent", "AlbumTitles/1.0")
                            .method(original.method, original.body)
                            .build()
                        chain.proceed(request)
                    }
                    .build()
            }
            .build()
    }
}