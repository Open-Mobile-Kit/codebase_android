package com.open_mobile_kit.android.codebase.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RequestHeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            // Add headers here, e.g., from a session manager
            // .addHeader("Authorization", "Bearer YOUR_TOKEN")
            .build()
        return chain.proceed(request)
    }
}
