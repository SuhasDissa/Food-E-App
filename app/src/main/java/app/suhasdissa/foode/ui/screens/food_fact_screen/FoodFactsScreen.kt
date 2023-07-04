package app.suhasdissa.foode.ui.screens.food_fact_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.foode.R
import app.suhasdissa.foode.backend.viewmodels.FoodFactUiState
import app.suhasdissa.foode.backend.viewmodels.FoodFactsViewModel
import app.suhasdissa.foode.ui.components.ErrorScreen
import app.suhasdissa.foode.ui.components.ProductNotFoundScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodFactScreen(
    barcode: String, onCLickAdditiveCard: (Int) -> Unit
) {
    val foodFactsViewModel: FoodFactsViewModel = viewModel(factory = FoodFactsViewModel.Factory)
    LaunchedEffect(Unit) {
        foodFactsViewModel.getProduct(barcode)
    }
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(
            title = { Text(stringResource(R.string.food_product_information)) }
        )
    }) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val state = foodFactsViewModel.foodFactUiState) {
                is FoodFactUiState.ProductNotFound -> {
                    ProductNotFoundScreen(barcode = barcode)
                }

                is FoodFactUiState.Error -> {
                    ErrorScreen(barcode = barcode)
                }

                is FoodFactUiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is FoodFactUiState.Success -> {
                    ProductFactView(product = state.product, onCLickAdditiveCard)
                }
            }
        }
    }
}
