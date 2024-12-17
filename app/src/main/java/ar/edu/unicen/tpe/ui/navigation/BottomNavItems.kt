package ar.edu.unicen.tpe.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import ar.edu.unicen.tpe.R

/**
 * Clase sellada que representa un elemento de la barra de navegación inferior.
 *
 * Cada elemento de la barra de navegación tiene una ruta asociada, un icono y un título.
 *
 * @property route La ruta de navegación asociada a este elemento.
 * @property icon El icono que representa visualmente el elemento de navegación.
 * @property title El recurso de cadena que representa el título del elemento.
 */
sealed class BottomNavItem(
  val route: String,
  val icon: ImageVector,
  val title: Int
) {
  /**
   * Elemento de navegación para la pantalla de inicio.
   */
  data object Home : BottomNavItem("home", Icons.Default.Home, R.string.nav_home)

  /**
   * Elemento de navegación para la pantalla de tendencias.
   */
  data object Trending : BottomNavItem("trending/week", Icons.Default.Star, R.string.nav_trending)
}
