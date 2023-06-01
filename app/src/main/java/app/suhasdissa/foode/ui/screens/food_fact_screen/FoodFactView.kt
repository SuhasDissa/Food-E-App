package app.suhasdissa.foode.ui.screens.food_fact_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.suhasdissa.foode.R
import app.suhasdissa.foode.backend.models.Product
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductFactView(product: Product) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    TabRow(selectedTabIndex = pagerState.currentPage, Modifier.fillMaxWidth()) {
        Tab(
            selected = (pagerState.currentPage == 0),
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(
                        0
                    )
                }
            }) {
            Text(
                stringResource(R.string.overview),
                Modifier.padding(10.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
        Tab(
            selected = (pagerState.currentPage == 1),
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(
                        1
                    )
                }
            }) {
            Text(
                stringResource(R.string.ingredients),
                Modifier.padding(10.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
        Tab(
            selected = (pagerState.currentPage == 2),
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(
                        2
                    )
                }
            }) {
            Text(
                stringResource(R.string.nutrients),
                Modifier.padding(10.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
    HorizontalPager(
        pageCount = 3,
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { index ->
        when (index) {
            0 -> FoodFactOverview(product = product)
            1 -> FoodFactIngredients(product = product)
            2 -> FoodFactNutrition(product = product)
        }
    }
}