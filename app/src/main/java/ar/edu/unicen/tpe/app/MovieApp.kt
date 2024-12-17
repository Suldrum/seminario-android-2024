package ar.edu.unicen.tpe.app

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ar.edu.unicen.tpe.ui.navigation.BottomNavBar
import ar.edu.unicen.tpe.ui.navigation.BottomNavItem
import ar.edu.unicen.tpe.ui.navigation.NavGraph

@Composable
fun MovieApp() {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Trending
    )
    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            if (currentRoute != null && items.any { it.route == currentRoute }){
                BottomNavBar(navController = navController, items = items)
            }
        }
    ) { innerPadding ->
        NavGraph(navController = navController, paddingValues = innerPadding)
    }

}
