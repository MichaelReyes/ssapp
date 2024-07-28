package com.example.loginapp.domain.repository

import com.example.loginapp.data.authentication.AuthenticationService
import com.example.loginapp.domain.common.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Although it wasn't part of the requirement,
 * this was added to demonstrate the implementation of the Repository pattern for the data layer.
 */
@Singleton
class AuthenticationRepository @Inject constructor(private val authenticationService: AuthenticationService) {
    companion object {
        const val STATIC_EMAIL_SUCCESS = "sample@email.com"
        const val STATIC_PASSWORD_SUCCESS = "dummypassword"
    }

    fun login(email: String, password: String): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        delay(800) // Added delay show loader since it'll just be fast on dummy check
        try {
            /* This is how it'll call the API
            val response = authenticationService.login(LoginRequest(email, password))
            if (response.isSuccessful) {
                emit(DataState.Success(true)) // or parse response (if available) to save user data on cache
            } else {
                emit(DataState.Error(Exception("Login failed")))
            }
            */

            // Dummy checking for success and fail scenario
            if (email == STATIC_EMAIL_SUCCESS && password == STATIC_PASSWORD_SUCCESS) {
                emit(DataState.Success(true))
            } else {
                emit(DataState.Error(Exception("Email or Password is incorrect")))
            }
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}
