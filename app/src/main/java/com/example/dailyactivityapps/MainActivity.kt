package com.example.dailyactivityapps

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.dailyactivityapps.prsentation.Screens.BottomBar
import com.example.dailyactivityapps.prsentation.Screens.auth.AuthViewModel
import com.example.dailyactivityapps.prsentation.Screens.auth.LoginScreen
import com.example.dailyactivityapps.prsentation.Screens.auth.SplashScreen
import com.example.dailyactivityapps.prsentation.navigation.EventsAppNavigation
import com.example.dailyactivityapps.prsentation.navigation.Screens
import com.example.dailyactivityapps.ui.theme.DailyActivityAppsTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp((this))

        setContent {
            DailyActivityAppsTheme {
                val  authViewModel : AuthViewModel = hiltViewModel()
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                var showBottombar by rememberSaveable {
                    mutableStateOf(false)
                }
                showBottombar = when(navBackStackEntry?.destination?.route){
                    Screens.MainApp.Home.rout -> true
                    Screens.MainApp.AddScreen.rout -> true
                    Screens.MainApp.TaskByDate.rout -> true
                    Screens.MainApp.CategoryScreen.rout -> true
                    Screens.MainApp.StaticScreen.rout -> true
                    else -> false
                }
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .semantics {
                            contentDescription = "MyScreen"
                        }
                ) {
                        paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)) {

                        if (authViewModel.error.value.isNotEmpty()){
                            Snackbar(modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                                containerColor = Color.Red.copy(0.5f) ,
                            ){
                                Text(text = authViewModel.error.value)
                            }
                        }
                        EventsAppNavigation(authViewModel = authViewModel, navController = navController )
                    }
                    if (showBottombar){
                        BottomBar(navController)
                    }
                }

            }
        }
    }
}

            /*
            val  authViewModel : AuthViewModel = hiltViewModel()

                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                var showBottombar by rememberSaveable {
                    mutableStateOf(false)
                }
                showBottombar = when(navBackStackEntry?.destination?.route){
                    Screens.MainApp.Home.rout -> true
                    Screens.MainApp.AddScreen.rout -> true
                    Screens.MainApp.TaskByDate.rout -> true
                    Screens.MainApp.CategoryScreen.rout -> true
                    Screens.MainApp.StaticScreen.rout -> true
                    else -> false
                }
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .semantics {
                            contentDescription = "MyScreen"
                        }
                ) {
                    paddingValues -> 
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)) {
                        
                        if (authViewModel.error.value.isNotEmpty()){
                            Snackbar(modifier = Modifier
                                .padding(16.dp)
                                .fillMaxSize(),
                                containerColor = Color.Red.copy(0.5f) ,
                            ){
                                Text(text = authViewModel.error.value)
                            } 
                        }
                        EventsAppNavigation(authViewModel = authViewModel, navController = navController )
                    }
                    if (showBottombar){
                        BottomBar(navController)
                    }
                }

            }
        }
    }
}
               /*
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
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
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
}
*/
*/
