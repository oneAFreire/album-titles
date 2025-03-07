package com.antonio.network

import com.antonio.network.model.NetworkAlbum
import retrofit2.http.GET

interface LeboncoinNetworkDataSource {
    @GET("img/shared/technical-test.json")
    suspend fun getAlbums(): List<NetworkAlbum>
}