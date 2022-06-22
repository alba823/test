package com.example.test.viewModels

import com.example.test.data.GetUserResponseSuccess
import com.example.test.data.ListResponseSuccess
import com.example.test.data.models.User
import com.example.test.data.models.response.ErrorCode
import com.example.test.data.models.response.ResponseState
import com.example.test.data.repository.Repository
import com.example.test.rules.TestDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Mock
    private lateinit var repository: Repository
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `loadUsers() should load user ids from repository`() {
        viewModel = createViewModel()

        Assert.assertTrue(viewModel.userIds.value is ResponseState.Success)
        Assert.assertEquals(
            ListResponseSuccess.data,
            (viewModel.userIds.value as ResponseState.Success).result.data
        )
    }

    @Test
    fun `loadUsers() should receive ResponseState Error from repository`() {
        viewModel = createViewModel(false)

        Assert.assertTrue(viewModel.userIds.value is ResponseState.Error)
    }

    @Test
    fun `loadUser() should update users`() {
        viewModel = createViewModel()

        Assert.assertTrue(viewModel.users.value.all { user -> user == GetUserResponseSuccess.data })
    }

    @Test
    fun `loadUser() should not update users`() {
        viewModel = createViewModel(false)

        Assert.assertEquals(
            listOf<User>(),
            viewModel.users.value
        )
    }

    @Test
    fun `userCount should show successfully loaded users count`() {
        viewModel = createViewModel()

        Assert.assertEquals(
            viewModel.users.value.size,
            viewModel.usersCount.value
        )
    }

    @Test
    fun `userCount should be 0 if response is Error`() {
        viewModel = createViewModel(false)

        Assert.assertEquals(
            0,
            viewModel.usersCount.value
        )
    }

    private fun createViewModel(responseSuccess: Boolean = true): MainViewModel {
        if (responseSuccess) {
            Mockito.`when`(repository.getUserIds())
                .thenReturn(flowOf(ResponseState.Success(ListResponseSuccess)))
            Mockito.`when`(repository.getUserById(any()))
                .thenReturn(flowOf(ResponseState.Success(GetUserResponseSuccess)))
        } else {
            Mockito.`when`(repository.getUserIds())
                .thenReturn(flowOf(ResponseState.Error(ErrorCode.BAD_RESPONSE_CODE)))
            Mockito.`when`(repository.getUserById(any()))
                .thenReturn(flowOf(ResponseState.Error(ErrorCode.BAD_RESPONSE_CODE)))
        }
        return MainViewModel(repository)
    }
}