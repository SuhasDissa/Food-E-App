package app.suhasdissa.foode.ui.screens.home.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.foode.R
import app.suhasdissa.foode.backend.viewmodels.SearchViewModel
import app.suhasdissa.foode.backend.viewmodels.states.SearchState
import app.suhasdissa.foode.ui.components.CardGrid
import app.suhasdissa.foode.ui.components.MessageScreen
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    searchViewModel: SearchViewModel = viewModel(factory = SearchViewModel.Factory),
    onClickTextCard: (url: Int) -> Unit
) {
    val search = remember { mutableStateOf(TextFieldValue("")) }
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current
    LaunchedEffect(focusRequester) {
        focusRequester.requestFocus()
        delay(100)
        keyboard?.show()
    }

    Column(modifier.fillMaxSize()) {
        Row(modifier.padding(14.dp), horizontalArrangement = Arrangement.Center) {
            TextField(
                value = search.value,
                onValueChange = {
                    search.value = it
                    if (search.value.text.length >= 3) {
                        searchViewModel.searchSongs(search.value.text)
                    }
                },
                modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
                singleLine = true,
                placeholder = { Text("Search") },
                shape = CircleShape
            )
        }

        when (val searchState = searchViewModel.searchState) {
            is SearchState.Empty -> MessageScreen(R.string.search_for_songs, modifier)
            is SearchState.Success -> if (searchState.additives.isEmpty()) {
                MessageScreen(R.string.no_results, modifier)
            } else {
                CardGrid(
                    searchState.additives, modifier, onClickTextCard
                )
            }
        }
    }
}