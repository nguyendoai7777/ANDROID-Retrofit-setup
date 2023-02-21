package com.example.pai.user

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pai.configurations.service.BaseUiState

@Composable
fun UserInfo() {
    val userInfoUiState: UserViewModel = viewModel(factory = UserViewModel.Factory)
    when(val userInfoState = userInfoUiState.userInfoUiState) {
        is BaseUiState.Success -> UserInfoRow(userInfoState.data as UserInfo, userInfoUiState::getUserInfo)
        is BaseUiState.Loading -> Text("loading... ♥")
        is BaseUiState.Error -> Text("Opps!! Somethings when wrong.")
    }
}

@Composable
fun UserInfoRow(user: UserInfo, reload: () -> Unit = {}) {
    Column() {
        Button(onClick = reload) {

        }
        Text("Full name: ${user.firstName} ${user.lastName}")
        Text("Date of birth: ${user.dob}")
        Text("Gender: ${user.gender}")
    }
}

