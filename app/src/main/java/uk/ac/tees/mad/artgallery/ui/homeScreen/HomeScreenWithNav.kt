package uk.ac.tees.mad.artgallery.ui.homeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.artgallery.firebaseauth.viewmodel.AuthViewModel
import uk.ac.tees.mad.artgallery.ui.homeScreen.bottomnavigation.BottomNavigation
import uk.ac.tees.mad.artgallery.ui.homeScreen.viewmodel.HomeViewModel


@Composable
fun HomeScreenWithNav(
    navController: NavHostController,
    authViewModel: AuthViewModel,
    homeViewModel: HomeViewModel
){

    val homenavController = rememberNavController()
    Scaffold (
        bottomBar = {
            BottomNavigation(homenavController)
        }
    ){innerpadding->
        Box(
            modifier = Modifier
                .padding(innerpadding)
        ){
            NavHost(navController = homenavController, startDestination = "home_screen") {
                composable("home_screen"){
                    HomeScreen(
                        homeViewModel
                    )
                }
                composable("profile_screen"){
                    ProfileScreen(
                        navController,
                        authViewModel
                    )
                }
            }
        }
    }
}