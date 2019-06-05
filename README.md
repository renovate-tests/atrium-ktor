[![EUPL](https://img.shields.io/badge/license-EUPL%201.2-brightgreen.svg)](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12)
[![Build Status Travis](https://travis-ci.org/tegonal/atrium-ktor.svg?branch=master)](https://travis-ci.org/tegonal/atrium-ktor)
[![Build status AppVeyor](https://ci.appveyor.com/api/projects/status/ftbrft2lwo5v2tkg/branch/master?svg=true)](https://ci.appveyor.com/project/tegonal/atrium-ktor/branch/master)
[![SonarCloud Status](https://sonarcloud.io/api/project_badges/measure?project=tegonal_atrium-ktor&metric=alert_status)](https://sonarcloud.io/dashboard?id=tegonal_atrium-ktor)

# Atrium-Ktor 

This project provides [Atrium](https://atriumlib.org) 
assertion functions (for the fluent API) for different [Ktor](https://ktor.io) types.

# Setup

```
repositories {
    jcenter()
    // either use jcenter or the repository on the next line
    // maven { url "http://dl.bintray.com/tegonal/oss" }
}
dependencies {
    testImplementation "com.tegonal.atrium.ktor:atrium-ktor-client:0.2.0"
    testImplementation "com.tegonal.atrium.ktor:atrium-ktor-server-tests:0.2.0"       
}
```

Depending on your use case you do not need all of the dependencies. 
The naming of the sub-projects follow the one of Ktor. 

For instance, if you write tests by using `kto-server-tests` (meaning something along the line of `withTestApplication(...)`)
then it suffices to depend only on `atrium-ktor-server-tests`

Also, the shown dependencies above are JVM dependencies. 
Currently `atrium-ktor-client` is also available for Common (`-common`), Android (`-android`) and JS (`-js`). 
Add the corresponding suffix, e.g. `atrium-ktor-client-js` for JS.

# Example

Have a look at [ktor-seed](https://github.com/tegonal/ktor-seed)'s [ApplicationTest](https://github.com/tegonal/ktor-seed/blob/master/src/test/kotlin/com/tegonal/ApplicationTest.kt#29).

# Contribute
You are welcome to contribute to this project:
- [open a bug report](https://github.com/tegonal/atrium-ktor/issues/new?template=bug_report.md) 
  if something does not work as you expected
- [create a feature request](https://github.com/tegonal/atrium-ktor/issues/new?template=feature_request.md&title=[Feature]) 
  if you miss something
- contribute in another way, we appreciate it 😊

# License
Atrium-Ktor is licensed under [EUPL 1.2](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12).