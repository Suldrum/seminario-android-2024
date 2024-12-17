package ar.edu.unicen.tpe.ui.screen

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unicen.tpe.ui.viewmodel.TrendingViewModel

@Composable
fun TrendingScreen(
    viewModel: TrendingViewModel = hiltViewModel(),
    toDetails: (Int) -> Unit
) {
    BaseListScreen(
        viewModel = viewModel,
        toDetails = toDetails
    )
}
