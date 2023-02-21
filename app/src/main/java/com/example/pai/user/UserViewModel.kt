package com.example.pai.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pai.configurations.network.Retrofit
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface UserUiState {
    data class Success(val users: List<User>) : UserUiState
    object Error : UserUiState
    object Loading : UserUiState
}

class UserViewModel: ViewModel() {
    private var userService = Retrofit.getService<UserService>()
    var userUiState: UserUiState by mutableStateOf(UserUiState.Loading)
        private set

    fun getUserList() {
        viewModelScope.launch {
            userUiState = UserUiState.Loading
            userUiState = try {
                UserUiState.Success(userService.getAllUsers())
            } catch (e: IOException) {
                UserUiState.Error
            }
        }
    }

    init {
        getUserList()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                UserViewModel()
            }
        }
    }
}

