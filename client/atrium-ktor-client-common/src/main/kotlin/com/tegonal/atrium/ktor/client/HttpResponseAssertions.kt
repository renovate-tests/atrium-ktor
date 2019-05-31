package com.tegonal.atrium.ktor.client

import ch.tutteli.atrium.api.cc.en_GB.notToBeNull
import ch.tutteli.atrium.api.cc.en_GB.property
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.translating.TranslatableWithArgs
import ch.tutteli.atrium.reporting.translating.Untranslatable
import io.ktor.client.response.HttpResponse
import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode

/**
 * Shortcut for `property(HttpResponse::status)`.
 *
 * @returns The [Assert] for the [HttpResponse.status].
 */
val <T : HttpResponse> Assert<T>.status get() : Assert<HttpStatusCode> = property(HttpResponse::status)

/**
 * Makes the assertion that the status of the [HttpResponse] is the given [httpStatusCode].
 *
 * Is a shortcut for `property(HttpResponse::status).toBe(httpStatusCode)` but returns the current assertion plant.
 *
 * Notice that an equality check is applied and that [HttpStatusCode.equals] is overridden and only compares the
 * [HttpStatusCode.value].
 *
 * @returns The current [Assert] for a fluent API.
 */
fun <T : HttpResponse> Assert<T>.hasStatus(httpStatusCode: HttpStatusCode): Assert<HttpResponse> =
    apply { status.toBe(httpStatusCode) }

/**
 * Shortcut for `property(HttpResponse::headers)`.
 *
 * @returns he [Assert] for the [HttpResponse.headers].
 */
val <T : HttpResponse> Assert<T>.headers get(): Assert<Headers> = property(HttpResponse::headers)

/**
 * Makes the assertion that the response has a header with the given [key] and the [expected] value.
 *
 * @returns The current [Assert] for a fluent API.
 */
fun <T : HttpResponse> Assert<T>.hasHeader(key: String, expected: String) =
    apply { header(key) { toBe(expected) } }

/**
 * Makes the assertion that the response has a header with the given [key] and that its value holds the assertions
 * created by the given [assertionCreator].
 *
 * @returns The current [Assert] for a fluent API.
 */
fun <T : HttpResponse> Assert<T>.header(key: String, assertionCreator: Assert<String>.() -> Unit) =
    apply {
        val l: () -> String? = { subject.headers[key] }
        AssertImpl.feature.property(
            this, l, TranslatableWithArgs(Untranslatable("headers[%s]"), key)
        ).notToBeNull(assertionCreator)
    }
