package com.ulusoyapps.tictactoe.unittesting

import com.google.common.truth.ThrowableSubject
import com.google.common.truth.Truth.assertThat

inline fun assertThrows(block: () -> Unit): ThrowableSubject {
    try {
        block()
    } catch (e: Throwable) {
        return assertThat(e)
    }
    throw AssertionError("Expected Throwable")
}
