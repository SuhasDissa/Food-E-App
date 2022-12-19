package app.suhasdissa.foode.ui.screens.songreader

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.foode.backend.viewmodels.AdditiveDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongView(
    id: Int,
    modifier: Modifier = Modifier,
    additiveViewModel: AdditiveDetailViewModel = viewModel(factory = AdditiveDetailViewModel.Factory)
) {
    val additive = additiveViewModel.additive
    LaunchedEffect(Unit) {
        additiveViewModel.getSong(id)
    }
    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text(additive.eCode, overflow = TextOverflow.Ellipsis) })
    }) { innerPadding ->
        LazyColumn(
            modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Card(
                    modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Text(additive.title, style = MaterialTheme.typography.titleLarge)
                        Text(additive.eCode, style = MaterialTheme.typography.titleSmall)
                    }
                }
            }
            item {
                Card(
                    modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        SelectionContainer(modifier.fillMaxWidth()) {
                            Text(additive.info, style = MaterialTheme.typography.bodyLarge)

                        }
                    }
                }
            }
        }
    }
}