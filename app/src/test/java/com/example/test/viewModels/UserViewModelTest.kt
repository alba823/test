package com.example.test.viewModels

import androidx.lifecycle.SavedStateHandle
import com.example.test.data.GetUserResponseSuccess
import com.example.test.data.models.User
import com.example.test.ui.navigation.NavigationItems
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = "src/test/AndroidManifest.xml")
class UserViewModelTest {

    @Test
    fun `user should be initialized from SavedStateHandle`() {
        val user = GetUserResponseSuccess.data ?: User.EMPTY_USER
        val savedStateHandle =
            SavedStateHandle(
                mapOf(NavigationItems.UserScreen.USER_ARG to user)
            )
        val viewModel = UserViewModel(savedStateHandle)

        Assert.assertTrue(viewModel.user.value != User.EMPTY_USER)
        Assert.assertEquals(
            GetUserResponseSuccess.data,
            viewModel.user.value
        )
    }
}