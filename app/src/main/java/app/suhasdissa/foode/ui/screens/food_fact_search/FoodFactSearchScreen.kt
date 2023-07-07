package app.suhasdissa.foode.ui.screens.food_fact_search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.foode.R
import app.suhasdissa.foode.backend.viewmodels.FFSearchViewModel
import app.suhasdissa.foode.backend.viewmodels.states.FFSearchError
import app.suhasdissa.foode.backend.viewmodels.states.FFSearchState
import app.suhasdissa.foode.ui.components.FoodProductCard
import app.suhasdissa.foode.ui.components.IllustratedMessageScreen
import app.suhasdissa.foode.ui.components.LoadingScreen
import kotlinx.coroutines.delay

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FoodFactSearchScreen(
    onClickCard: (String) -> Unit,
    ffSearchViewModel: FFSearchViewModel = viewModel(factory = FFSearchViewModel.Factory)
) {
    var search by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current
    LaunchedEffect(focusRequester) {
        focusRequester.requestFocus()
        delay(100)
        keyboard?.show()
    }
    Column(Modifier.fillMaxSize()) {
        var expanded by remember { mutableStateOf(false) }

        TextField(
            value = search,
            onValueChange = {
                search = it
            },
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
                .focusRequester(focusRequester),

            singleLine = true,
            placeholder = { Text(stringResource(R.string.search_food_product)) },
            shape = CircleShape,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                keyboard?.hide()
                expanded = false
                if (search.isNotEmpty()) {
                    ffSearchViewModel.searchFoodProduct(search)
                }
            }),
            trailingIcon = {
                IconButton(onClick = { search = "" }) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = stringResource(R.string.clear_search)
                    )
                }
            }
        )
        when (val searchState = ffSearchViewModel.state) {
            is FFSearchState.Loading -> {
                LoadingScreen()
            }

            is FFSearchState.Error -> {
                IllustratedMessageScreen(
                    image = R.drawable.broken_egg_icon,
                    message = when (searchState.e) {
                        FFSearchError.Timeout -> R.string.timeout_error
                        FFSearchError.Unknown -> R.string.something_went_wrong
                    }
                )

            }

            is FFSearchState.NotFound -> {
                IllustratedMessageScreen(
                    image = R.drawable.egg_not_found_icon,
                    message = R.string.couldnt_find_product
                )
            }

            is FFSearchState.Success -> {
                LazyColumn(
                    Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(vertical = 24.dp, horizontal = 16.dp)
                ) {
                    items(items = searchState.products) {
                        FoodProductCard(
                            onClick = {
                                ffSearchViewModel.addToHistory(it)
                                onClickCard(it.code)
                            },
                            products = it
                        )
                    }
                }
            }

            is FFSearchState.Empty -> {
                IllustratedMessageScreen(
                    image = R.drawable.ic_launcher_monochrome,
                    message = R.string.search_for_food_product,
                    messageColor = MaterialTheme.colorScheme.tertiary
                )
            }
        }
    }
}