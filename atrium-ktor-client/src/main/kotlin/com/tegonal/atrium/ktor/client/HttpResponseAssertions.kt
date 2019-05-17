package com.tegonal.atrium.ktor.client

import ch.tutteli.atrium.api.cc.en_GB.property
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.creating.Assert
import io.ktor.client.response.HttpResponse
import io.ktor.http.HttpStatusCode

val <T: HttpResponse> Assert<T>.status get() = property(io.ktor.client.response.HttpResponse::status)

fun <T: HttpResponse> Assert<T>.hasStatus(httpStatusCode: HttpStatusCode): Assert<HttpResponse> =
    apply { status.toBe(httpStatusCode) }
