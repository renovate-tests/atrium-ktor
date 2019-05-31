package com.tegonal.atrium.ktor.server.tests

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.verbs.expect
import io.ktor.application.Application
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.TestApplicationCall
import io.ktor.server.testing.TestApplicationResponse
import io.ktor.server.testing.createTestEnvironment
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import kotlin.coroutines.EmptyCoroutineContext

object TestApplicationResponseAssertionsSpec : Spek({

    val dummyApplicationCall = TestApplicationCall(Application(createTestEnvironment()), false, EmptyCoroutineContext)

    describe("hasStatus") {
        listOf(
            Triple("same code, different description", true, HttpStatusCode(404, "only value is considered")),
            Triple("same description, different code", false, HttpStatusCode(500, HttpStatusCode.NotFound.description)),
            Triple("same code and description", true, HttpStatusCode(404, HttpStatusCode.NotFound.description)),
            Triple("same object", true, HttpStatusCode.NotFound)
        ).forEach { (description, holds, statusCode) ->
            describe(description) {
                if (holds) {
                    it("does not throw") {
                        val response = TestApplicationResponse(dummyApplicationCall)
                        response.status(statusCode)
                        expect(response)
                            .hasStatus(HttpStatusCode.NotFound)
                            .hasStatus(HttpStatusCode.NotFound) // check fluent API
                    }
                } else {
                    it("throws") {
                        val response = TestApplicationResponse(dummyApplicationCall)
                        response.status(statusCode)
                        expect {
                            expect(response).hasStatus(HttpStatusCode.NotFound)
                        }.toThrow<AssertionError> { messageContains("status(): $statusCode") }
                    }
                }
            }
        }

        describe("no status set") {
            it("throws") {
                expect {
                    expect(TestApplicationResponse(dummyApplicationCall)).hasStatus(HttpStatusCode.NotFound)
                }.toThrow<AssertionError> { messageContains("status(): null") }
            }
        }
    }

    describe("content") {
        it("provides a shortcut") {
            val response = TestApplicationResponse(dummyApplicationCall)
            expect(response).content.toBe(null)
        }
    }

    describe("headers") {
        it("provides a shortcut to the ResponseHeaders object of a response") {
            val response = TestApplicationResponse(dummyApplicationCall)
            expect(response).headers.toBe(response.headers)
        }
    }

    val response by memoized {
        TestApplicationResponse(dummyApplicationCall).apply {
            headers.append("Content-Type", "text/html; charset=utf-8", safeOnly = false)
        }
    }

    describe("hasHeader with string") {

        it("throws an exception if key does not exist, showing what key was tried to access") {
            expect {
                expect(response).hasHeader("non-existing-key", "hello")
            }.toThrow<AssertionError> { messageContains("headers[non-existing-key]") }
        }
        it("throws if the value is not the expected one") {
            expect {
                expect(response).hasHeader("Content-Type", "hello")
            }.toThrow<AssertionError> { messageContains("headers[Content-Type]: \"text/html; charset=utf-8\"") }
        }
        it("passes if the value is the expected one") {
            expect(response)
                .hasHeader("Content-Type", "text/html; charset=utf-8")
                .hasHeader("Content-Type", "text/html; charset=utf-8") // check fluent API
        }
    }

    describe("hasHeader with assertion creator lambda") {
        it("throws an exception if key does not exist, showing what key was tried to access") {
            expect {
                expect(response).header("non-existing-key") { startsWith("hello") }
            }.toThrow<AssertionError> { messageContains("headers[non-existing-key]") }
        }
        it("throws if the value is not the expected one") {
            expect {
                expect(response).header("Content-Type") { startsWith("hello") }
            }.toThrow<AssertionError> { messageContains("headers[Content-Type]: \"text/html; charset=utf-8\"") }
        }
        it("passes if the value is the expected one") {
            expect(response)
                .header("Content-Type") { startsWith("text/html") }
                .header("Content-Type") { endsWith("charset=utf-8") } // check fluent API
        }
    }
})
