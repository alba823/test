package com.example.test.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: String,
    val country: String
) : Parcelable {
    companion object {
        private const val emptyValue = ""
        val EMPTY_USER = User(emptyValue, emptyValue, emptyValue, 0, emptyValue, emptyValue)
    }
}