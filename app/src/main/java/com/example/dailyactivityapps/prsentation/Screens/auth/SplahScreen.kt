package com.example.dailyactivityapps.prsentation.Screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dailyactivityapps.R
import com.example.dailyactivityapps.prsentation.navigation.Screens
import com.example.dailyactivityapps.ui.theme.LightPurple
import com.example.dailyactivityapps.ui.theme.PrimaryColor

@Composable
fun SplashScreen(
    navController : NavHostController,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.vector_1_1),
                contentDescription = "Splash Logo"
            )
            Text(
                text = "Dailoz.",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth() ,
                color = LightPurple ,
                textAlign =  TextAlign.Center
            )
            Text(
                text = "Plan what you will do to be more organized for today, tomorrow and beyond",
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {navController.navigate(Screens.Authentication.Login.rout)},
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally)
                    .size(50.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
            ) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Sign in",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clickable {navController.navigate(Screens.Authentication.SignUp.rout) }
                    .align(Alignment.CenterHorizontally)
            )
        }

        }
    }
