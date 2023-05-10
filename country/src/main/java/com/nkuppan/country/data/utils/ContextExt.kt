package com.nkuppan.country.data.utils

import android.content.Context
import java.io.InputStreamReader

fun Context.openInputStreamReader(fileName: String): InputStreamReader? {
    return kotlin.runCatching {
        return this.assets.open(fileName).reader()
    }.getOrNull()
}