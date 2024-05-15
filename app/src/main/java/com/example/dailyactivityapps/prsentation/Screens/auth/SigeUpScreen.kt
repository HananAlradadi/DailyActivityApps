package com.example.dailyactivityapps.prsentation.Screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dailyactivityapps.R
import com.example.dailyactivityapps.prsentation.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController : NavHostController,
                 authViewModel : AuthViewModel,) {

    Column(
        modifier = Modifier.fillMaxSize(),

        ) {
        Text(
            text = "Sign Up",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 50.dp, top = 110.dp, bottom = 50.dp)
        )
        Column(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .padding(30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var passowrd by remember {
                mutableStateOf("")
            }
            var Email by remember {
                mutableStateOf("")
            }

            androidx.compose.material3.TextField(
                value = Email, onValueChange = { Email = it },
                label = { Text(text = "Email", color = Color.Gray) },
                // shape = RoundedCornerShape(10.dp),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.password_icon),
                        contentDescription = ""
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Gray
                )
            )

            androidx.compose.material3.TextField(
                value = passowrd,
                onValueChange = { passowrd = it },
                label = { Text(text = "Password", style = TextStyle(color = Color.Gray)) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.password_icon),
                        contentDescription = "password icon"
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 10.dp, bottom = 50.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Gray
                )

            )
            Button(
                onClick = {
                    authViewModel.signup(Email, passowrd)
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .align(Alignment.CenterHorizontally)
                    .size(50.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)

            ) {
                Text(text = "Create")
            }
            Text(
                text = "or with", modifier = Modifier
                    .padding(top = 50.dp)
                    .align(Alignment.CenterHorizontally),
                color = Color.Gray
            )
            androidx.compose.material3.Divider(
                modifier = Modifier
                    // .padding(top = 50.dp)
                    .fillMaxWidth(0.70f)
                    .align(Alignment.CenterHorizontally),
                color = MaterialTheme.colorScheme.primaryContainer
            )


            Spacer(modifier = Modifier.height(16.dp))

            Row {//loginUsingGoogel(navController)
                loginUsingGoogel(navController)

            }

            Spacer(modifier = Modifier.height(24.dp))

            Row {
                Text("you have an account?")
                Text(
                    text = " Log in",
                    color = Color.Blue,
                    modifier = Modifier.clickable { navController.navigate(Screens.Authentication.Login.rout) }
                )
            }
        }
    }
}


