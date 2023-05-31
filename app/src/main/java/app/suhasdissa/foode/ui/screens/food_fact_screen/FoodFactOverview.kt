package app.suhasdissa.foode.ui.screens.food_fact_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.suhasdissa.foode.R
import app.suhasdissa.foode.backend.models.Product
import app.suhasdissa.foode.ui.components.ItemCard
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun FoodFactOverview(product: Product) {
    LazyColumn(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 24.dp, horizontal = 16.dp)
    ) {
        item {
            ElevatedCard {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(product.imageUrl)
                            .crossfade(true).build(),
                        contentDescription = stringResource(R.string.product_image),
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(8.dp)
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = product.productName
                                ?: stringResource(R.string.unknown_product_name),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            style = MaterialTheme.typography.titleLarge
                        )
                        product.brands?.let { brand ->
                            Text(
                                text = brand,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp)
                            )
                        }
                        product.netWeightValue?.let { weight ->
                            Text(
                                text = weight + (product.netWeightUnit ?: ""),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 8.dp)
                            )
                        }
                    }

                }
            }
        }
        item {
            Text(
                text = stringResource(R.string.details),
                style = MaterialTheme.typography.titleMedium
            )
        }
        product.labels?.let {
            item {
                ItemCard(title = stringResource(R.string.labels), subtitle = it)
            }
        }
        product.origins?.let {
            item {
                ItemCard(title = stringResource(R.string.ingredients_origin), subtitle = it)
            }
        }
        product.categories?.let {
            item {
                ItemCard(title = stringResource(R.string.categories), subtitle = it)
            }
        }
        product.packaging?.let {
            item {
                ItemCard(title = stringResource(R.string.packaging), subtitle = it)
            }
        }
        product.countries?.let {
            item {
                ItemCard(title = stringResource(R.string.country_of_sale), subtitle = it)
            }
        }
        item {
            Text(
                text = stringResource(R.string.about_barcode),
                style = MaterialTheme.typography.titleMedium
            )
        }
        product.id?.let {
            item {
                ItemCard(title = stringResource(R.string.barcode_content), subtitle = it)
            }
        }
    }
}