package com.tegonal.atrium.ktor.client

import ch.tutteli.atrium.api.cc.en_GB.property
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.creating.Assert
import io.ktor.client.response.HttpResponse
import io.ktor.http.HttpStatusCode

/**
 * Shortcut for `property(HttpResponse::status)`.
 */
val <T : HttpResponse> Assert<T>.status get() : Assert<HttpStatusCode> = property(HttpResponse::status)

/**
 * Makes the assertion that the status of the [HttpResponse] is the given [httpStatusCode].
 *
 * Is a shortcut for `property(HttpResponse::status).toBe(httpStatusCode)`
 *
 * Notice that an equality check is applied and that [HttpStatusCode.equals] is overridden and only compares the
 * [HttpStatusCode.value].
 */
fun <T : HttpResponse> Assert<T>.hasStatus(httpStatusCode: HttpStatusCode): Assert<HttpResponse> =
    apply { status.toBe(httpStatusCode) }
