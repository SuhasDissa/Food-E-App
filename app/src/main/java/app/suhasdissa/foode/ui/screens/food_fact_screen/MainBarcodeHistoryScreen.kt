package app.suhasdissa.foode.ui.screens.food_fact_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import app.suhasdissa.foode.Destination
import app.suhasdissa.foode.R
import app.suhasdissa.foode.ui.screens.home.ScanHistoryScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainBarcodeHistoryScreen(
    onNavigate: (Destination) -> Unit,
    onClickBarcodeCard: (String) -> Unit
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState { 2 }
    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
        FloatingActionButton(onClick = { onNavigate(Destination.FoodFactSearch) }) {
            Icon(
                imageVector = Icons.Filled.Search,
                stringResource(R.string.search_icon_hint)
            )
        }
    }, bottomBar = {
        NavigationBar {
            NavigationBarItem(
                icon = { Icon(imageVector = Icons.Filled.Book, contentDescription = null) },
                label = { Text(stringResource(R.string.food_products)) },
                selected = pagerState.currentPage == 0,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            0
                        )
                    }
                }
            )
            NavigationBarItem(
                icon = { Icon(imageVector = Icons.Filled.Star, contentDescription = null) },
                label = { Text(stringResource(R.string.favourites)) },
                selected = pagerState.currentPage == 1,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(
                            1
                        )
                    }
                }
            )
        }
    }) { innerPadding ->

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) { index ->
            when (index) {
                0 -> ScanHistoryScreen(onClickCard = onClickBarcodeCard)
                1 -> ScanHistoryScreen(onClickCard = onClickBarcodeCard)
            }
        }
    }
}
