package uk.ac.tees.mad.artgallery.ui.homeScreen.bottomnavigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItems(
    val title: String,
    val icon: ImageVector,
    val screen_route: String
) {
    object Profile: BottomNavItems(
        title = "Profile",
        icon = Icons.Default.Person,
        screen_route = "profile_screen"
    )

    object Home: BottomNavItems(
        title = "Home",
        icon = Icons.Filled.Home,
        screen_route = "home_screen"
    )
}