package com.example.dailyactivityapps.prsentation.Screens.auth

import android.content.Context
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.dailyactivityapps.prsentation.navigation.Screens
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : ViewModel() {
    private val auth = Firebase.auth
    val isSignedIn =
        if (auth.currentUser == null) mutableStateOf(Screens.Authentication.rout) else mutableStateOf(Screens.MainApp.rout)
val error = mutableStateOf("")
    init {
        auth.apply {
            this.addAuthStateListener {
                isSignedIn.value = if (auth.currentUser == null) Screens.Authentication.rout else Screens.MainApp.rout
            }
        }
    }
    fun login(email : String , passsword : String){
        auth.signInWithEmailAndPassword(email,passsword).addOnCompleteListener { task ->
            if (task.isSuccessful){
               // navController.navigate(Screens.MainApp.Home.rout())
            }
            else {
              error.value = task.exception?.message.orEmpty()
            }
        }
    }
    fun logout(context: Context){
        auth.signOut()
        GoogleSignIn.getClient(
            context,
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
        ).signOut()
      //  isSignedIn.value = Screens.Authentication.rout
    }
    fun signup(email : String , passsword : String){
        auth.createUserWithEmailAndPassword(email,passsword).addOnCompleteListener { task ->
            if (task.isSuccessful){

            }
            else {
                error.value = task.exception?.message.orEmpty()
            }
        }
    }
    fun restPassword(email : String ){
        auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful){

            }
            else {
                error.value = task.exception?.message.orEmpty()
            }
        }
    }
}