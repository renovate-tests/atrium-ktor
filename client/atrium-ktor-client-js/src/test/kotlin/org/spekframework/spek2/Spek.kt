//TODO remove as soon as https://github.com/spekframework/spek/issues/706 is fixed
package org.spekframework.spek2

import org.spekframework.spek2.lifecycle.CachingMode
import org.spekframework.spek2.style.specification.MemoizedValue

interface Root {
    //is not here in spek but in the same scope thus OK (the whole thing is hack anyway)
    fun <T> memoized(mode: CachingMode = CachingMode.EACH_GROUP, body: () -> T): MemoizedValue<T> =
        throw IllegalStateException("not implemented")
}

@Suppress("UnnecessaryAbstractClass")
abstract class Spek(val root: Root.() -> Unit)
