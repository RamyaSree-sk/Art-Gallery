package uk.ac.tees.mad.artgallery

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navigation
import uk.ac.tees.mad.artgallery.firebaseauth.viewmodel.AuthViewModel
import uk.ac.tees.mad.artgallery.ui.authentication.LoginScreen
import uk.ac.tees.mad.artgallery.ui.authentication.OnBoardingScreen
import uk.ac.tees.mad.artgallery.ui.authentication.SignUpScreen
import uk.ac.tees.mad.artgallery.ui.homeScreen.HomeScreenWithNav
import uk.ac.tees.mad.artgallery.ui.homeScreen.viewmodel.HomeViewModel

@Composable
fun MainNavigation (
    navController: NavHostController,
    isAuthenticated: Boolean,
    authViewModel: AuthViewModel,
    homeViewModel: HomeViewModel
){

    NavHost(
        navController = navController,
        startDestination = if (isAuthenticated) "home_graph" else "auth_graph"
    ) {
        // Auth graph
        navigation(startDestination = "on_boarding", route = "auth_graph") {
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

        // Home graph
        navigation(startDestination = "home", route = "home_graph") {
            composable("home") {
                HomeScreenWithNav(
                    navController,
                    authViewModel,
                    homeViewModel)
            }
        }
    }
}