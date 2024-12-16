package uk.ac.tees.mad.artgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import uk.ac.tees.mad.artgallery.firebaseauth.viewmodel.AuthViewModel
import uk.ac.tees.mad.artgallery.ui.authentication.AuthenticationNav
import uk.ac.tees.mad.artgallery.ui.theme.ArtGalleryTheme

class MainActivity : ComponentActivity() {

    private val authViewModel by viewModels<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {

        var desti = "home"

        installSplashScreen().apply {
            setKeepOnScreenCondition{
                authViewModel.showSplash.value
            }
            if (authViewModel.isLoggedIn.value) {
                desti="home"
            }
            else{
                desti="authentication"
            }
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtGalleryTheme {
                ArtGalleryNav(desti)
            }
        }
    }
}
