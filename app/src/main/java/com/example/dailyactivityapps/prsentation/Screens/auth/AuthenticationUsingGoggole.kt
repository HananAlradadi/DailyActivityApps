package com.example.dailyactivityapps.prsentation.Screens.auth

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dailyactivityapps.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun loginUsingGoogel(navController : NavHostController){

    var user by remember {
        mutableStateOf((Firebase.auth.currentUser))
    }
    val luancher = RememberFirebaseAuthLauncher(onAuthComplete = { result ->
        user = result.user

    },
        onAuthError = {
            user = null
        })


    val tokan = "439528301545-0d4ht4j82huhsvk2lmjm7l7vm2fs25gv.apps.googleusercontent.com"

    val context = LocalContext.current
    if (user == null) {
        Image(modifier = Modifier
            .padding(4.dp)
            .clickable {
                val gso =
                    GoogleSignInOptions
                        .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(tokan)
                        .requestEmail()
                        .build()
                val gooleSignInClint = GoogleSignIn.getClient(context, gso)
                luancher.launch(gooleSignInClint.signInIntent)
            },painter = painterResource(id = R.drawable.ic_google) , contentDescription = "google")
    }

}

// A surface container using the 'background' color from the theme


@Composable
fun RememberFirebaseAuthLauncher(
    onAuthComplete : (AuthResult) -> Unit,
    onAuthError : (ApiException) -> Unit,
) : ManagedActivityResultLauncher<Intent, ActivityResult> {

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
