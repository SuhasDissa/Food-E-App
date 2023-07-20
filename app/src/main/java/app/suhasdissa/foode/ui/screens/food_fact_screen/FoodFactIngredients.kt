package app.suhasdissa.foode.ui.screens.food_fact_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.foode.R
import app.suhasdissa.foode.backend.models.Product
import app.suhasdissa.foode.backend.viewmodels.FoodFactsViewModel
import app.suhasdissa.foode.ui.components.ItemCard

@Composable
fun FoodFactIngredients(product: Product, onCLickAdditiveCard: (Int) -> Unit) {
    val foodFactsViewModel: FoodFactsViewModel = viewModel(factory = FoodFactsViewModel.Factory)
    LazyColumn(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 24.dp, horizontal = 16.dp)
    ) {
        foodFactsViewModel.ingredientsWithBoldAllergens?.let { text ->
            item {
                ElevatedCard(
                    Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            stringResource(R.string.ingredients),
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
        product.allergens?.let {
            item {
                ItemCard(
                    title = stringResource(R.string.allergens),
                    subtitle = it.replace(Regex("[a-z]{2}:"), "")
                )
            }
        }
        if (foodFactsViewModel.eachAdditive.isNotEmpty()) {
            item {
                var showMoreInfoDialog by remember { mutableStateOf(false) }
                ElevatedCard(
                    Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                stringResource(R.string.additives),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            IconButton(onClick = { showMoreInfoDialog = true }) {
                                Icon(Icons.Outlined.Info, stringResource(R.string.show_more_info))
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        foodFactsViewModel.eachAdditive.forEach { pair ->
                            if (pair.second != null) {
                                with(pair.second!!) {
                                    AdditiveCardStack(
                                        this.eCode,
                                        this.title,
                                        onClick = { onCLickAdditiveCard(this.id) }
                                    )
                                }
                            } else {
                                AdditiveCardStack(mainText = pair.first.uppercase(), subText = "")
                            }
                        }
                    }
                }
                if (showMoreInfoDialog) {
                    Dialog(onDismissRequest = { showMoreInfoDialog = false }) {
                        Card {
                            Column(
                                Modifier
                                    .width(200.dp)
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = product.additivesTags.joinToString(", ")
                                        .replace(Regex("[enfrs]{2}:"), ""),
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AdditiveCardStack(mainText: String, subText: String, onClick: () -> Unit = {}) {
    Column(
        Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Text(
            text = mainText,
            maxLines = 1,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = subText,
            maxLines = 1,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AdditiveCardStackPreview() {
    AdditiveCardStack(mainText = "Additive", subText = "Details")
}
