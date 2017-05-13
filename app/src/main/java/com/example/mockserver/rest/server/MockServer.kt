package com.example.mockserver.rest.server

import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * The idea of Mock Server is to return fake responses that will satisfy our needs.
 * Tests should rely on server that returns always the same response, regardless internet connection,
 * or server maintanance.
 */
class MockServer(baseUrl: String) : Server(baseUrl, OkHttpClient()) {

    init {
        recreate()
    }

    private fun recreate() {
        val restClient = OkHttpClient.Builder()
                .addInterceptor { chain -> Response.Builder().protocol(Protocol.HTTP_1_0).request(chain.request()).code(200).body(ResponseBody.create(MediaType.parse(""), "[]")).build() }
                .build()

        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .client(restClient)
                .build()
    }
}