package com.example.loginapp.data.authentication

import com.example.loginapp.domain.model.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {
    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<Void>
}
