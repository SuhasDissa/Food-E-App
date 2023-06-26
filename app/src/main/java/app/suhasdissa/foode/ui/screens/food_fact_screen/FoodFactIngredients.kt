package app.suhasdissa.foode.ui.screens.food_fact_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import app.suhasdissa.foode.R
import app.suhasdissa.foode.backend.models.Product
import app.suhasdissa.foode.ui.components.ItemCard

@Composable
fun FoodFactIngredients(product: Product) {
    LazyColumn(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 24.dp, horizontal = 16.dp)
    ) {
        product.ingredientsTextWithAllergens?.let { text ->
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
                        val boldRegex = Regex("<[^</>]+>([^</>]+)</[^</>]+>")
                        var results: MatchResult? = boldRegex.find(text)
                        val keywords = mutableListOf<String>()
                        val finalText = text.replace(Regex("<[^<>]+>"), "")
                        while (results != null) {
                            keywords.add(results.groupValues[1])
                            results = results.next()
                        }
                        val annotatedString = buildAnnotatedString {
                            append(finalText)
                            keywords.forEach { keyword ->
                                val indexOf = finalText.indexOf(keyword)
                                addStyle(
                                    style = SpanStyle(fontWeight = FontWeight.Bold),
                                    start = indexOf,
                                    end = indexOf + keyword.length
                                )
                            }
                        }
                        Text(
                            annotatedString,
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
                    subtitle = it.replace("en:", "")
                )
            }
        }
        product.additivesTags.let {
            item {
                ItemCard(
                    title = stringResource(R.string.additives),
                    subtitle = it.joinToString(", ").replace("en:", "")
                )
            }
        }
    }
}