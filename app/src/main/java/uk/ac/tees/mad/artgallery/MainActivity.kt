package uk.ac.tees.mad.artgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import uk.ac.tees.mad.artgallery.firebaseauth.viewmodel.AuthViewModel
import uk.ac.tees.mad.artgallery.ui.homeScreen.viewmodel.HomeViewModel
import uk.ac.tees.mad.artgallery.ui.theme.ArtGalleryTheme

class MainActivity : ComponentActivity() {

    private val authViewModel by viewModels<AuthViewModel>()
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen().apply {
            setKeepOnScreenCondition{
                authViewModel.showSplash.value
            }
        }

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            val isAuthenticated = authViewModel.isLoggedIn.collectAsState()
            val darkTheme = homeViewModel.darkTheme.collectAsState()

            homeViewModel.defaultTheme(isSystemInDarkTheme())

            ArtGalleryTheme(darkTheme = darkTheme.value) {
                MainNavigation(
                    navController,
                    isAuthenticated.value,
                    authViewModel,
                    homeViewModel)
            }
        }
    }
}
