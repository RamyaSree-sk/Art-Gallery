package uk.ac.tees.mad.artgallery.ui.homeScreen.bottomnavigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigation(navController: NavController){
    val items = listOf(
        BottomNavItems.Home,
        BottomNavItems.Profile
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route

    NavigationBar (
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.primary
    ){
        items.forEach { items->
            NavigationBarItem(
                label = { Text(text = items.title)},
                selected = currentDestination == items.screen_route,
                onClick = {
                    navController.navigate(items.screen_route){
                        popUpTo(items.screen_route){inclusive=true}
                    }
                },
                alwaysShowLabel = true,
                icon = { Icon(imageVector = items.icon, contentDescription = items.title) })
        }
    }
}