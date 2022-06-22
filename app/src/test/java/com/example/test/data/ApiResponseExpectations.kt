package com.example.test.data

import com.example.test.data.models.response.ResponseModel
import com.example.test.data.models.User

val ListResponseSuccess =
    ResponseModel(
        status = "success",
        data = listOf(
            "9532c8c5-59ac-42fe-9ee9-ab92edfe3325",
            "ba9a0df1-2992-48ac-96c9-39f26ddf08a4",
            "71326200-5792-4042-aff1-24cda600e3f6",
            "63bb2cc4-b414-4c97-bd3c-d3c18a722545",
            "e3e548e5-568e-4be8-950d-e1b3c15ea7e3",
            "d333fbe0-adb5-48e1-ba68-1a7a16eab879",
            "981dcbb3-3647-4aaf-8578-10eca563ab88",
            "83bb23f4-3a99-4eab-afcb-95ae00b91d62",
            "8008800880088008",
            "a7f1ef73-4582-4d72-a93b-54fea53fe70c",
            "285710ec-56e4-49a6-a60f-212cabe1d328",
            "5c01f486-a368-427a-9070-142e69646ee5",
            "ba084732-a4dc-4d2c-8bbf-8e5ec64ce1af",
            "5ad15e83-1e5e-4bb3-946e-e9a39eeba562",
            "4a399ee5-8a46-41c3-b549-a14f3f3dceb1"
        )
    )

const val ID_FOR_GET_USER_REQUEST = "6b7fbff2-c489-4a82-b86f-66b8fc1674a4"

val GetUserResponseSuccess =
    ResponseModel(
        status = "success",
        data = User(
            id = ID_FOR_GET_USER_REQUEST,
            firstName = "Larry",
            lastName = "Smith",
            age = 23,
            gender = "Female",
            country = "India"
        )
    )