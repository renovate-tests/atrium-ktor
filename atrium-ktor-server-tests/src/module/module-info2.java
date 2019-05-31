//TODO rename file to module-info.java as soon as https://github.com/ktorio/ktor/issues/1137 is fixed
module com.tegonal.atrium.ktor.server.tests {
    requires ch.tutteli.atrium.bundle.cc.en_GB.robstoll;
    requires kotlin.stdlib;
    requires ktor.server.test.host;
    requires ktor.http.jvm;

    exports com.tegonal.atrium.ktor.server.tests;
}
