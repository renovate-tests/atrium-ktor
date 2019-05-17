package com.tegonal.atrium.ktor.client

import io.ktor.client.call.HttpClientCall
import io.ktor.client.response.HttpResponse
import io.ktor.http.Headers
import io.ktor.http.HttpProtocolVersion
import io.ktor.http.HttpStatusCode
import io.ktor.util.date.GMTDate
import kotlinx.coroutines.io.ByteReadChannel
import kotlin.coroutines.CoroutineContext

class SimpleHttpResponse(
    content: String = "",
    override val headers: Headers =  Headers.Empty,
    override val status: HttpStatusCode = HttpStatusCode.OK,
    override val version: HttpProtocolVersion = HttpProtocolVersion.HTTP_1_1
) : HttpResponse() {
    override val content: ByteReadChannel = ByteReadChannel(content)

    override val call: HttpClientCall get() = TODO("not implemented")
    override val coroutineContext: CoroutineContext get() = TODO("not implemented")
    override val requestTime: GMTDate get() = TODO("not implemented")
    override val responseTime: GMTDate get() = TODO("not implemented")
}