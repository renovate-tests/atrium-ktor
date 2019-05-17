[![EUPL](https://img.shields.io/badge/license-EUPL%201.2-brightgreen.svg)](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12)
[![Build Status Travis](https://travis-ci.org/tegonal/atrium-ktor.svg?branch=master)](https://travis-ci.org/tegonal/atrium-ktor)
[![Build status AppVeyor](https://ci.appveyor.com/api/projects/status/ftbrft2lwo5v2tkg/branch/master?svg=true)](https://ci.appveyor.com/project/tegonal/atrium-ktor/branch/master)

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
    testImplementation "com.tegonal.atrium.ktor:atrium-ktor-client:0.1.0"    
}
```

# License
Atrium-Ktor is licensed under [EUPL 1.2](https://joinup.ec.europa.eu/collection/eupl/eupl-text-11-12).