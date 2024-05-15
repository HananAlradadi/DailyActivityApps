package com.example.dailyactivityapps.prsentation.Screens.auth
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dailyactivityapps.R
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.example.dailyactivityapps.prsentation.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
// navController.navigate(Screens.Authentication.SignUpScreen.rout)
fun LoginScreen(navController : NavHostController , authViewModel: AuthViewModel) {


    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top , horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Login",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(bottom = 80.dp, top = 10.dp)
                .align(Alignment.CenterHorizontally)
        )
        var userEmail by remember {
            mutableStateOf("")
        }
        var userPassword by remember {
            mutableStateOf("")
        }
        var chake by remember {
            mutableStateOf(false)
        }
        androidx.compose.material3.TextField(
            value = userEmail,
            onValueChange = { userEmail = it },
            label = {
                Text(
                    text = "Email ID or Username",
                    style = TextStyle(color = Color.Gray),
                    textAlign = TextAlign.Start
                )
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.message),
                    contentDescription = "login icon "
                )
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 80.dp)
                .fillMaxWidth(0.90f)
                .background(Color.White),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Gray
            )
        )
        androidx.compose.material3.TextField(
            value = userPassword,
            onValueChange = { userPassword = it },
            label = { Text(text = "Password", style = TextStyle(color = Color.Gray)) },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.password_icon),
                    contentDescription = "password icon"
                )
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp)
                .fillMaxWidth(0.90f)
                .background(Color.White),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Gray
            )

        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "forget password?", style = TextStyle(Color.Blue), modifier = Modifier
            .padding(start = 230.dp, bottom = 80.dp)
            .clickable {  })

        androidx.compose.material3.Button(
            onClick = {
                authViewModel.login(userEmail,userPassword)

            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .align(Alignment.CenterHorizontally)
                .size(50.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)

        ) {
            Text(text = "Login")
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
            Text("Don’t have an account?")
            Text(
                text = " Sign Up",
                color = Color.Blue,
                modifier = Modifier.clickable {navController.navigate(Screens.Authentication.SignUp.rout) }
            )
        }
    }
}

//        Icon(
//            painter = painterResource(id = R.drawable.google_icon),
//            contentDescription = "google icon",
//            tint = Color.Unspecified,
//            modifier = Modifier
//                .align(Alignment.CenterHorizontally)
//                .clickable {  }
//        )


/*
@Composable



Column {

val tokan = "439528301545-0d4ht4j82huhsvk2lmjm7l7vm2fs25gv.apps.googleusercontent.com"

    var user by remember { mutableStateOf(Firebase.auth.currentUser) }
    val launcher = RememberFirebaseAuthLauncher(
        onAuthComplete = { result ->
            user = result.user
        } ,
        onAuthError = {
            user = null
            println(it.message)
        }
    )

    val context = LocalContext.current
    Column {
        if (user == null){
            authViewModel.isSignedIn.value = Screens.Authentication.rout
            Image(modifier = Modifier.padding(4.dp).clickable {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(tokan).requestEmail().build()

            } )
            Text(text = "Not logged in")
            Button(onClick = { /*TODO*/
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(tokan).requestEmail().build()
                val googleSignInClient = GoogleSignIn.getClient(context,gso)
                launcher.launch(googleSignInClient.signInIntent)

            }) {
                Text(text = "Sign in via Google")
            }
        }

   else{
       Text(text = "Welcome ${user?.displayName}")
            AsyncImage(
                model = user?.photoUrl,
                contentDescription = null,
                Modifier
                    .clip(CircleShape)
                    .size(45.dp)

            )
            Button(onClick = {
                Firebase.auth.signOut()
                user = null
            }) {
                Text(text = "Sign out")
            }
        }
   }
}


// A surface container using the 'background' color from the theme

}
@Composable
fun RememberFirebaseAuthLauncher(
    onAuthComplete : (AuthResult) -> Unit ,
    onAuthError : (ApiException) -> Unit ,
) : ManagedActivityResultLauncher<Intent, androidx.activity.result.ActivityResult> {

    val scope = rememberCoroutineScope()
    return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
            scope.launch {
                val authResult = Firebase.auth.signInWithCredential(credential).await()
            }
        } catch (e: ApiException) {
            onAuthError(e)
        }

    }
}*/