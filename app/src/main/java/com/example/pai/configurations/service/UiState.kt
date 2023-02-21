package com.example.pai.configurations.service

import com.example.pai.user.UserInfo


sealed interface BaseUiState {
    data class Success(val data: Any) : BaseUiState
    object Error : BaseUiState
    object Loading : BaseUiState
}
