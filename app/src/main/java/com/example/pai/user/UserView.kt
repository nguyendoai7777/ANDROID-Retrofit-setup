package com.example.pai.user

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
        item {
            Divider(modifier = Modifier.fillMaxWidth())
        }
        item {
            UserInfo()
        }
        item {
            Divider(modifier = Modifier.fillMaxWidth())
        }
        users.forEach {
            item {
                Text(text = it.name)
            }
        }
    }

}