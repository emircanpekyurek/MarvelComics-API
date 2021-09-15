package com.pekyurek.marvelcomics.presentation.core.extensions

import org.junit.Assert.assertEquals
import org.junit.Test

class SecurityKtTest {

    @Test
    fun `text to md5 encryption`() {
        assertEquals("test".toMd5(), "098f6bcd4621d373cade4e832627b4f6")
        assertEquals("1flısakhf238soafb23yur7as4t26r7du".toMd5(), "659f9ed2a27dfc4479f8b5c0a2189324")
    }

}