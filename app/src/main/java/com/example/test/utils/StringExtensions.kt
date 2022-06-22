package com.example.test.utils

import android.util.Base64

fun String.encode(): String = Base64.encodeToString(this.toByteArray(Charsets.UTF_8), Base64.NO_WRAP)