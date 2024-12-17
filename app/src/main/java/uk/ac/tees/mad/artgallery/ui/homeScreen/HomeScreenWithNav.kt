package uk.ac.tees.mad.artgallery.ui.homeScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.artgallery.ui.homeScreen.bottomnavigation.BottomNavigation


@Preview
@Composable
fun HomeScreenWithNav(
//    navController: NavHostController,
//    authViewModel: AuthViewModel
){

    val navController = rememberNavController()
    Scaffold (
        bottomBar = {
            BottomNavigation(navController)
        }
    ){innerpadding->
        Box(
            modifier = Modifier
                .padding(innerpadding)
        ){
            NavHost(navController = navController, startDestination = "home_screen") {
                composable("home_screen"){
                    HomeScreen()
                }
                composable("profile_screen"){
                    ProfileScreen()
                }
            }
        }
    }
}