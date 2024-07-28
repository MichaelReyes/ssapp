package com.example.loginapp.presentation.screen.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.loginapp.R
import com.example.loginapp.data.isValidEmail
import com.example.loginapp.domain.common.DataState
import com.example.loginapp.domain.common.DataState.Error
import com.example.loginapp.domain.common.DataState.Loading
import com.example.loginapp.domain.common.DataState.Success
import com.example.loginapp.presentation.navigation.tool.Screen
import com.example.loginapp.presentation.widgets.InputField
import com.example.loginapp.ui.theme.LoginAppTheme

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val loginState by viewModel.loginState.collectAsState()
    LoginForm(
        navController = navController,
        state = loginState,
        onClick = { email, password -> viewModel.login(email, password) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginForm(
    navController: NavController,
    state: DataState<*>?,
    onClick: (String, String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isButtonEnabled by remember { mutableStateOf(false) }

    LaunchedEffect(state) {
        if (state is Success) {
            navController.navigate(Screen.Welcome.route)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sample Login app") },
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = dimensionResource(R.dimen.space_large),
                        end = dimensionResource(R.dimen.space_large),
                        bottom = dimensionResource(R.dimen.space_huge)
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InputField(
                    label = stringResource(R.string.email),
                    value = email,
                    leadingIcon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    onChange = {
                        email = it
                        isButtonEnabled = email.isNotBlank() &&
                            email.isValidEmail() &&
                            password.isNotBlank()
                    },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.space_small)))
                InputField(
                    label = stringResource(R.string.password),
                    value = password,
                    leadingIcon = {
                        Icon(
                            Icons.Default.Lock,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    onChange = {
                        password = it
                        isButtonEnabled = email.isNotBlank() &&
                            email.isValidEmail() &&
                            password.isNotBlank()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.space_medium)))
                when (state) {
                    is Loading -> {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.space_medium)))
                    }

                    is Success -> {
                        Text(
                            text = stringResource(R.string.login_success),
                            color = Color.Green
                        )
                        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.space_medium)))
                    }

                    is Error -> {
                        Text(
                            text = stringResource(
                                R.string.login_failed,
                                "${(state as Error).exception.message}"
                            ),
                            color = Color.Red
                        )
                        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.space_medium)))
                    }

                    else -> {
                        // Do nothing
                    }
                }
                Button(
                    onClick = { onClick.invoke(email, password) },
                    enabled = isButtonEnabled,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Login")
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginAppTheme {
        LoginForm(
            navController = rememberNavController(),
            state = DataState.Loading,
            onClick = { email, password -> }
        )
    }
}