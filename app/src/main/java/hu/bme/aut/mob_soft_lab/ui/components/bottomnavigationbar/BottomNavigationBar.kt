package hu.bme.aut.mob_soft_lab.ui.components.bottomnavigationbar

import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.filled.*
import hu.bme.aut.mob_soft_lab.navigation.MobSoftAppScreen

@Composable
fun BottomNavigationBar(navController: NavController) {

    BottomNavigation(
        backgroundColor = Color.LightGray
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val currentRoute = navBackStackEntry?.destination?.route

        BottomNavItems.BottomNavItems.forEach { navItem ->

            BottomNavigationItem(
                selected = currentRoute == navItem.route,

                onClick = {
                    navController.navigate(navItem.route){
                        navController.popBackStack()
                        popUpTo("login"){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                },

                label = {
                    Text(text = navItem.label)
                },
                alwaysShowLabel = false
            )
        }
    }
}

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String,
)

object BottomNavItems {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Character List",
            icon = Icons.Filled.List,
            route =  MobSoftAppScreen.CharacterListNav.route
        ),
        BottomNavItem(
            label = "Loved Characters",
            icon = Icons.Filled.Favorite,
            route = MobSoftAppScreen.LovedCharacterListNav.route
        )
    )
}