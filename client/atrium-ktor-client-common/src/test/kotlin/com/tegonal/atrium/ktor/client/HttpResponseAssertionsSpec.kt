package com.tegonal.atrium.ktor.client

import ch.tutteli.atrium.api.cc.en_GB.*
import ch.tutteli.atrium.verbs.expect
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpStatusCode
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object HttpResponseAssertionsSpec : Spek({

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
                        expect(SimpleHttpResponse(status = statusCode))
                            .hasStatus(HttpStatusCode.NotFound)
                            .hasStatus(HttpStatusCode.NotFound) // check fluent API
                    }
                } else {
                    it("throws") {
                        expect {
                            expect(SimpleHttpResponse(status = statusCode)).hasStatus(HttpStatusCode.NotFound)
                        }.toThrow<AssertionError> { messageContains("status: $statusCode") }
                    }
                }
            }
        }
    }

    val headers by memoized {
        val builder = HeadersBuilder()
        builder["Content-Type"] = "text/html; charset=utf-8"
        builder.build()
    }

    describe("headers") {
        it("provides a shortcut to the Headers object of a response") {
            expect(SimpleHttpResponse(headers = headers)).headers.toBe(headers)
        }
    }

    describe("hasHeader with string") {
        it("throws an exception if key does not exist, showing what key was tried to access") {
            expect {
                expect(SimpleHttpResponse(headers = headers)).hasHeader("non-existing-key", "hello")
            }.toThrow<AssertionError> { messageContains("headers[non-existing-key]") }
        }
        it("throws if the value is not the expected one") {
            expect {
                expect(SimpleHttpResponse(headers = headers)).hasHeader("Content-Type", "hello")
            }.toThrow<AssertionError> { messageContains("headers[Content-Type]: \"text/html; charset=utf-8\"") }
        }
        it("passes if the value is the expected one") {
            expect(SimpleHttpResponse(headers = headers))
                .hasHeader("Content-Type", "text/html; charset=utf-8")
                .hasHeader("Content-Type", "text/html; charset=utf-8") // check fluent API
        }
    }

    describe("hasHeader with assertion creator lambda") {
        it("throws an exception if key does not exist, showing what key was tried to access") {
            expect {
                expect(SimpleHttpResponse(headers = headers)).header("non-existing-key") { startsWith("hello") }
            }.toThrow<AssertionError> { messageContains("headers[non-existing-key]") }
        }
        it("throws if the value is not the expected one") {
            expect {
                expect(SimpleHttpResponse(headers = headers)).header("Content-Type") { startsWith("hello") }
            }.toThrow<AssertionError> { messageContains("headers[Content-Type]: \"text/html; charset=utf-8\"") }
        }
        it("passes if the value is the expected one") {
            expect(SimpleHttpResponse(headers = headers))
                .header("Content-Type") { startsWith("text/html") }
                .header("Content-Type") { endsWith("charset=utf-8") } // check fluent API
        }
    }
})
