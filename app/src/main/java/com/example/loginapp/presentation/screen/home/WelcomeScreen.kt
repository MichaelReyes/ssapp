package com.example.loginapp.presentation.screen.home

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.finishAffinity
import com.example.loginapp.R
import com.example.loginapp.ui.theme.LoginAppTheme
import kotlinx.coroutines.delay

@Composable
fun WelcomeScreen() {

    var exit by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(key1 = exit) {
        if (exit) {
            delay(2000)
            exit = false
        }
    }

    BackHandler(enabled = true) {
        if (exit) {
            finishAffinity(context as ComponentActivity)
        } else {
            exit = true
            Toast.makeText(context, "Press again to exit", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = dimensionResource(R.dimen.space_medium)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.welcome_header),
            style = MaterialTheme.typography.displayLarge.copy(
                color = Color(0xFF6200EE)
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.space_small)))
        Text(
            text = stringResource(R.string.welcome_body),
            style = MaterialTheme.typography.bodySmall.copy(fontSize = 18.sp)
        )
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.space_medium)))
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    LoginAppTheme {
        WelcomeScreen()
    }
}