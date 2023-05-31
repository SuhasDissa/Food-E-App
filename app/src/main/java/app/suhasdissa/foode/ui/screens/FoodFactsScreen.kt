package app.suhasdissa.foode.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.foode.R
import app.suhasdissa.foode.backend.models.Product
import app.suhasdissa.foode.backend.viewmodels.FoodFactUiState
import app.suhasdissa.foode.backend.viewmodels.FoodFactsViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun FoodFactScreen(barcode: String) {
    val foodFactsViewModel: FoodFactsViewModel = viewModel(factory = FoodFactsViewModel.Factory)
    LaunchedEffect(Unit) {
        foodFactsViewModel.getProduct(barcode)
    }
    when (val state = foodFactsViewModel.foodFactUiState) {
        is FoodFactUiState.Error -> {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(state.error, color = MaterialTheme.colorScheme.error)
                Text("Barcode: $barcode")
            }
        }

        is FoodFactUiState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        is FoodFactUiState.Success -> {
            ProductFactView(state.product)
        }
    }
}

@Composable
fun ProductFactView(product: Product) {
    LazyColumn(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        item {
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = product.productName ?: "Unknown Product Name",
                        modifier = Modifier.padding(horizontal = 8.dp),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    AsyncImage(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(product.imageUrl)
                            .crossfade(true).build(),
                        contentDescription = stringResource(R.string.product_image),
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
        item {
            Card(
                Modifier.fillMaxWidth()
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        "Barcode",
                        style = MaterialTheme.typography.titleLarge
                    )
                    Text(
                        product.id ?: "Unknown Product id",
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
        }
    }
}
