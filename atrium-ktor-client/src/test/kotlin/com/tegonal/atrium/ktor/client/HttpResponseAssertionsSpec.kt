package com.tegonal.atrium.ktor.client

import ch.tutteli.atrium.api.cc.en_GB.toThrow
import ch.tutteli.atrium.verbs.expect
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
                            .hasStatus(HttpStatusCode.NotFound) //check we fluent works
                    }
                } else {
                    it("throws") {
                        expect {
                            expect(SimpleHttpResponse(status = statusCode)).hasStatus(HttpStatusCode.NotFound)
                        }.toThrow<AssertionError> { }
                    }
                }
            }
        }
    }
})
