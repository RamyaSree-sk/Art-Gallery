package uk.ac.tees.mad.artgallery

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.artgallery.ui.authentication.AuthenticationNav
import uk.ac.tees.mad.artgallery.ui.homeScreen.HomeScreen

@Composable
fun ArtGalleryNav (desti: String){
    val homeNavController = rememberNavController()
    NavHost(navController = homeNavController, startDestination = desti){
        composable("home"){
            HomeScreen()
        }
        composable("authentication"){
            AuthenticationNav()
        }
    }
}