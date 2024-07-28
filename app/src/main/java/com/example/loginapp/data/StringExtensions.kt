package com.example.loginapp.data

import android.util.Patterns

fun String.isValidEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(this).matches()