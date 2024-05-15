package com.example.dailyactivityapps.prsentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens (val rout : String) {
    data object MainApp : Screens("mainGraph") {
    data object Home  : Screens("home_screen")
    data object  TaskByDate : Screens("task_by_date_screen")
    data object AddScreen  : Screens("add_screen")
    data object CategoryScreen  : Screens("category_screen")
    data object StaticScreen  : Screens("statics_screen")
    }
    data object Authentication  : Screens("authGraph") {
        data object Splash  : Screens("splash")
        data object SignUp   : Screens("signup_route")
        data object Login  : Screens("login_route")
    }
}
data class BottomNavigationItem(
    val icon : ImageVector = Icons.Filled.Home,
    val route : String = ""
){
    fun bottomNavigationItems() : List<BottomNavigationItem>{
        return listOf(
            BottomNavigationItem(
                icon = Icons.Outlined.Home,
                route = Screens.MainApp.Home.rout
            ),
            BottomNavigationItem(
                icon = Icons.Outlined.List,
                route = Screens.MainApp.TaskByDate.rout
            ) ,
            BottomNavigationItem(
                icon = Icons.Filled.AddCircle,
                route = Screens.MainApp.AddScreen.rout
            ),
            BottomNavigationItem(
                icon = Icons.Outlined.Settings,
                route = Screens.MainApp.CategoryScreen.rout
            ),
            BottomNavigationItem(
                icon = Icons.Outlined.DateRange,
                route = Screens.MainApp.StaticScreen.rout
            ),
        )
    }
}