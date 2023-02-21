package com.example.pai.user

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel


@SuppressLint("UnrememberedMutableState")
@Composable
fun UserView() {
    val userViewModel: UserViewModel = viewModel(factory = UserViewModel.Factory)
    when (val userUiState = userViewModel.userUiState) {
        is UserUiState.Success -> UserList(userUiState.users, userViewModel::getUserList)
        is UserUiState.Error -> Text("Somethings went wrong")
        is UserUiState.Loading -> Text("loading...")
    }
}

@Composable
fun UserList(
    users: List<User>,
    retry: () -> Unit
) {
    LazyColumn {
        item {
            Button(onClick = retry) {
                Text(text = "reload")
            }
        }
        users.forEach {
            item {
                Text(text = it.name)
            }
        }
    }

}