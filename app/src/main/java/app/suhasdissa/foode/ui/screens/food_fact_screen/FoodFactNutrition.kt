package app.suhasdissa.foode.ui.screens.food_fact_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.suhasdissa.foode.R
import app.suhasdissa.foode.backend.models.Product
import app.suhasdissa.foode.backend.models.getAsTable
import app.suhasdissa.foode.ui.components.NutrientCard
import app.suhasdissa.foode.ui.components.NutritionTable

@Composable
fun FoodFactNutrition(product: Product) {
    LazyColumn(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 24.dp, horizontal = 16.dp)
    ) {
        product.nutriments?.let { nutriments ->
            item {
                Text(
                    text = stringResource(R.string.nutritional_facts),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            item {
                val context = LocalContext.current
                ElevatedCard(
                    Modifier
                        .fillMaxWidth()
                ) {
                    NutritionTable(table = nutriments.getAsTable(context))
                }
            }
        }
        product.nutrientLevels?.let { nutrientLevels ->
            item {
                Text(
                    text = stringResource(R.string.nutrient_level_for_100g),
                    style = MaterialTheme.typography.titleMedium
                )
            }
            nutrientLevels.fat?.let {
                item {
                    NutrientCard(
                        title = stringResource(R.string.fat),
                        nutrientLevel = it,
                        nutrientAmount = product.nutriments?.let {
                            "${it.fat100G} ${it.fatUnit}"
                        } ?: ""
                    )
                }
            }
            nutrientLevels.saturatedFat?.let {
                item {
                    NutrientCard(
                        title = stringResource(R.string.saturated_fat),
                        nutrientLevel = it,
                        nutrientAmount = product.nutriments?.let {
                            "${it.saturatedFat100G} ${it.saturatedFatUnit}"
                        } ?: ""
                    )
                }
            }
            nutrientLevels.salt?.let {
                item {
                    NutrientCard(
                        title = stringResource(R.string.salt),
                        nutrientLevel = it,
                        nutrientAmount = product.nutriments?.let {
                            "${it.salt100G} ${it.saltUnit}"
                        } ?: ""
                    )
                }
            }
            nutrientLevels.sugars?.let {
                item {
                    NutrientCard(
                        title = stringResource(R.string.sugars),
                        nutrientLevel = it,
                        nutrientAmount = product.nutriments?.let {
                            "${it.sugars100G} ${it.sugarsUnit}"
                        } ?: ""
                    )
                }
            }

        }
    }
}