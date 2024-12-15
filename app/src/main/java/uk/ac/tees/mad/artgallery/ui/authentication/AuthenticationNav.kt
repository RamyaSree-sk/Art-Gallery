package uk.ac.tees.mad.artgallery.ui.authentication

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AuthenticationNav(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "on_boarding"){
        composable("on_boarding"){
            OnBoardingScreen(navController)
        }
        composable("login_screen"){
            LoginScreen(navController)
        }
        composable("signup_screen"){
            SignUpScreen(navController)
        }
    }
}