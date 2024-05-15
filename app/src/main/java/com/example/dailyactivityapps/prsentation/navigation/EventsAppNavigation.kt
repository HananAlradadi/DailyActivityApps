package com.example.dailyactivityapps.prsentation.navigation

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.dailyactivityapps.prsentation.Screens.HomeScreen
import com.example.dailyactivityapps.prsentation.Screens.auth.AuthViewModel
import com.example.dailyactivityapps.prsentation.Screens.auth.LoginScreen
import com.example.dailyactivityapps.prsentation.Screens.auth.SignUpScreen
import com.example.dailyactivityapps.prsentation.Screens.auth.SplashScreen
import com.example.dailyactivityapps.prsentation.Screens.task.TaskViewModel

@Composable
fun EventsAppNavigation(
    authViewModel : AuthViewModel ,
    navController : NavHostController
){
    val contest = LocalContext.current
    NavHost(navController = navController, startDestination = authViewModel.isSignedIn.value, ){
        authNavigation(navController,authViewModel)
        mainAppNavigation(navController){
           authViewModel.logout(contest)
        }
    }
}
fun NavGraphBuilder.authNavigation(
    navController : NavHostController ,
    authViewModel : AuthViewModel ,
){
    navigation(
        startDestination = Screens.Authentication.Splash.rout,
        route = Screens.Authentication.rout,
    ){
        composable(Screens.Authentication.Splash.rout){
            SplashScreen(navController)
        }
        composable(Screens.Authentication.SignUp.rout){
           SignUpScreen(navController,authViewModel)
        }
        composable(Screens.Authentication.Login.rout){
           LoginScreen(navController,authViewModel)
            //LoginScreen()
        }
    }
}
fun NavGraphBuilder.mainAppNavigation(
    navController : NavHostController ,
    logout: () -> Unit
){
    navigation(
        startDestination = Screens.MainApp.Home.rout,
        route = Screens.MainApp.rout,
    )
    {
        composable(Screens.MainApp.Home.rout){
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue)) {
                
            }
        }
        composable(Screens.MainApp.TaskByDate.rout){
            val viewmodel:TaskViewModel = hiltViewModel()
            HomeScreen(viewmodel,navController)


        }
        composable(Screens.MainApp.CategoryScreen.rout){
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)) {
Button(onClick = { /*TODO*/ 
logout.invoke()
}) {
    Text(text = "SignOut")
}
            }
        }
        composable(Screens.MainApp.AddScreen.rout){
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.Magenta)) {

            }
        }
        composable(Screens.MainApp.StaticScreen.rout){
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.Green)) {

            }
        }
    }
}
fun NavOptionsBuilder.popUpToTop(navController: NavController) {
    popUpTo(navController.currentBackStackEntry?.destination?.route ?: return) {
        inclusive =  true
        saveState = true
    }
}