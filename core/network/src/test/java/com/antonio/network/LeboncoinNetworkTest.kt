package com.antonio.network

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@ExperimentalCoroutinesApi
class LeboncoinNetworkTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var api: LeboncoinNetworkDataSource

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        val testRetrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = testRetrofit.create(LeboncoinNetworkDataSource::class.java)
    }

    @Test
    fun testApiReturnsExpectedData() = runBlocking {
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("[{\"id\": 1, \"title\": \"Album 1\", \"thumbnailUrl\": \"https://example.com/1.jpg\"}]")

        mockWebServer.enqueue(mockResponse)

        val response = api.getAlbums()

        assertEquals(1, response.size)
        assertEquals("Album 1", response[0].title)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}