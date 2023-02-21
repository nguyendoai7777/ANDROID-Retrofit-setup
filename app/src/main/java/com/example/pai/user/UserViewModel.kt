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
import com.example.pai.configurations.service.BaseUiState
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface UserUiState {
    data class Success(val users: List<User>) : UserUiState
    object Error : UserUiState
    object Loading : UserUiState
}

sealed interface UserInfoUiState {
    data class Success(val users: UserInfo) : UserInfoUiState
    object Error : UserInfoUiState
    object Loading : UserInfoUiState
}

typealias UserState = BaseUiState

class UserViewModel: ViewModel() {
    private var userService = Retrofit.getService<UserService>()
    var userUiState: UserUiState by mutableStateOf(UserUiState.Loading)
        private set

    var userInfoUiState: BaseUiState by mutableStateOf(BaseUiState.Loading)
        private set

    var v: BaseUiState by mutableStateOf(BaseUiState.Loading)
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

    fun getUserInfo() {
        viewModelScope.launch {
            userInfoUiState = BaseUiState.Loading
            userInfoUiState = try {
                BaseUiState.Success(userService.getUserSettings("1"))
            } catch (e: IOException) {
                BaseUiState.Error
            }
        }
    }


    init {
        getUserList()
        getUserInfo()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                UserViewModel()
            }
        }
    }
}

