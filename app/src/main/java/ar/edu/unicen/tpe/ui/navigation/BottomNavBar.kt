package ar.edu.unicen.tpe.ui.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.unit.dp

/**
 * Composable que representa la barra de navegación inferior.
 *
 * @param navController Controlador de navegación para gestionar la navegación entre pantallas.
 * @param items Lista de elementos de navegación que se mostrarán en la barra.
 */
@Composable
fun BottomNavBar(navController: NavController, items: List<BottomNavItem>) {
    // Se establece la barra de navegación con un color de fondo blanco y una elevación tonal de 5 dp.
    NavigationBar(containerColor = Color.White, tonalElevation = 5.dp) {
        // Se obtiene la entrada de la pila de navegación actual.
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        // Se determina la ruta actual de la navegación.
        val currentRoute = navBackStackEntry?.destination?.route

        // Se verifica si la ruta actual no es nula y si está en la lista de elementos.
        if (currentRoute != null && items.any { it.route == currentRoute }) {
            // Se itera sobre cada elemento en la lista de navegación.
            items.forEach { item ->
                NavigationBarItem(
                    // Se establece el ícono y la etiqueta para el elemento de navegación.
                    icon = { Icon(item.icon, contentDescription = stringResource(item.title)) },
                    label = { Text(stringResource(item.title)) },
                    // Se marca el elemento como seleccionado si coincide con la ruta actual.
                    selected = currentRoute == item.route,
                    onClick = {
                        // Si el elemento no está seleccionado, se navega a su ruta correspondiente.
                        if (currentRoute != item.route) {
                            navController.navigate(item.route) {
                                // Se evita crear múltiples instancias de la misma pantalla.
                                launchSingleTop = true
                                // Se limpia la pila de navegación hasta el destino inicial.
                                popUpTo(0)
                            }
                        }
                    },
                    // Se definen los colores para los íconos y textos seleccionados y no seleccionados.
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Black,
                        selectedTextColor = Color.Black,
                        unselectedIconColor = Color.Gray,
                        unselectedTextColor = Color.Gray
                    )
                )
            }
        }
    }
}

/**
 * Vista previa para el componente BottomNavBar.
 */
@Preview(showBackground = true)
@Composable
fun BottomNavBarPreview() {
    // Se crea un controlador de navegación simulado.
    val navController = rememberNavController()
    // Se define una lista de elementos de navegación que se mostrarán en la vista previa.
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Trending
    )
    // Se muestra la barra de navegación inferior con los elementos definidos.
    BottomNavBar(navController, items)
}
