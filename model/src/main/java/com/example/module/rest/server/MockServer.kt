package com.example.module.rest.server

import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * The idea of Mock Server is to return fake responses that will satisfy our needs.
 * Tests should rely on server that returns always the same response, regardless internet connection,
 * or server maintanance.
 */
class MockServer(baseUrl: String) : Server(baseUrl, OkHttpClient()) {

    private val interceptors: MutableMap<String, Interceptor> = mutableMapOf()

    init {
        recreate()
    }

    private fun recreate() {
        val clientBuilder = OkHttpClient.Builder()

        for (interceptor in interceptors.values) {
            clientBuilder.addInterceptor(interceptor)
        }

        val restClient = clientBuilder
                .addInterceptor { chain -> Response.Builder().protocol(Protocol.HTTP_1_0).request(chain.request()).code(200).body(ResponseBody.create(MediaType.parse(""), "null")).build() }
                .build()

        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create().withNullSerialization())
                .client(restClient)
                .build()
    }

    fun rewriteResponse(request: String, responseCode: Int, responseBody: String) {
        interceptors.put(request, RewriteResponseInterceptor(request, responseCode, responseBody))
    }

    override fun <T> getService(serviceClass: Class<T>): T {
        recreate()
        return super.getService(serviceClass)
    }
}

class RewriteResponseInterceptor(val request: String, val responseCode: Int, val responseBody: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain?): Response {
        val chainRequest = chain?.request()!!
        if (chainRequest.url()!!.toString().contains(request)) {
            return Response.Builder()
                    .protocol(Protocol.HTTP_1_0)
                    .request(chainRequest)
                    .code(responseCode)
                    .body(ResponseBody.create(MediaType.parse("application/json"), responseBody))
                    .build()
        }

        return chain.proceed(chainRequest)
    }

}
