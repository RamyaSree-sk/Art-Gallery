package uk.ac.tees.mad.artgallery.ui.authentication

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.artgallery.firebaseauth.viewmodel.AuthViewModel

@Composable
fun AuthenticationNav(){

    val authViewModel = AuthViewModel()

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "on_boarding"){
        composable("on_boarding"){
            OnBoardingScreen(navController)
        }
        composable("login_screen"){
            LoginScreen(navController, authViewModel)
        }
        composable("signup_screen"){
            SignUpScreen(navController, authViewModel)
        }
    }
}