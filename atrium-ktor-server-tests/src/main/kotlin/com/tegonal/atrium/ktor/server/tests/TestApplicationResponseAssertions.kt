package com.tegonal.atrium.ktor.server.tests

import ch.tutteli.atrium.api.cc.en_GB.property
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlantNullable
import ch.tutteli.atrium.domain.builders.AssertImpl
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.TestApplicationResponse


/**
 * Shortcut for `returnValueOf(TestApplicationResponse::status)`.
 *
 * Well... almost, due to a Kotlin bug it is a bit more verbose and this shortcut comes in very handy.
 *
 * @returns The [Assert] for the [TestApplicationResponse.status].
 */
val Assert<TestApplicationResponse>.status
    get(): AssertionPlantNullable<HttpStatusCode?> {
        val l: () -> HttpStatusCode? = { subject.status() }
        return AssertImpl.feature.returnValueOf0(this, l, "status")
    }

/**
 * Makes the assertion that the status of the [TestApplicationResponse] is the given [httpStatusCode].
 *
 * Is a shortcut for `returnValueOf(TestApplicationResponse::status).toBe(httpStatusCode)`
 * but returns the current assertion plant.
 *
 * Notice that an equality check is applied and that [HttpStatusCode.equals] is overridden and only compares the
 * [HttpStatusCode.value].
 *
 * @returns The current [Assert] for a fluent API.
 */
fun Assert<TestApplicationResponse>.hasStatus(httpStatusCode: HttpStatusCode): Assert<TestApplicationResponse> =
    apply { status.toBe(httpStatusCode) }

/**
 * Shortcut for `property(TestApplicationResponse::content)`.
 */
val Assert<TestApplicationResponse>.content get(): AssertionPlantNullable<String?> =
    property(TestApplicationResponse::content)
