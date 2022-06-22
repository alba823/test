package com.example.test.utils

import android.net.Uri
import com.example.test.data.models.User
import com.google.gson.Gson

fun User.toJson(): String =
    Uri.encode(Gson().toJson(this))