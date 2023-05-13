package app.suhasdissa.foode.ui.screens.additivedetails

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.foode.backend.repositories.data.DetailedAdditive
import app.suhasdissa.foode.backend.viewmodels.AdditiveDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdditiveDetailScreen(
    id: Int,
    modifier: Modifier = Modifier,
    additiveViewModel: AdditiveDetailViewModel = viewModel(factory = AdditiveDetailViewModel.Factory)
) {
    val additive = additiveViewModel.additive
    LaunchedEffect(Unit) {
        additiveViewModel.getAdditive(id)
    }
    Scaffold(modifier = modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text(additive.eCode, overflow = TextOverflow.Ellipsis) })
    }) { innerPadding ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AdditiveDetailBox(additive = additive)
        }
    }
}

@Composable
fun AdditiveDetailView(
    id: Int,
    additiveViewModel: AdditiveDetailViewModel = viewModel(factory = AdditiveDetailViewModel.Factory)
) {
    LaunchedEffect(id) {
        if (id != 0) additiveViewModel.getAdditive(id)
    }
    if (id == 0) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Select an Additive")
        }
    } else {
        AdditiveDetailBox(additive = additiveViewModel.additive)
    }

}

@Composable
fun AdditiveDetailBox(modifier: Modifier = Modifier, additive: DetailedAdditive) {
    LazyColumn(
        modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .padding(top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Card(
                modifier.fillMaxWidth()
            ) {
                Column(
                    modifier
                        .fillMaxWidth()
                        .padding(16.dp)
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
                        .padding(16.dp)
                ) {
                    SelectionContainer(modifier.fillMaxWidth()) {
                        Text(additive.info, style = MaterialTheme.typography.bodyLarge)

                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AdditiveDetailBoxPreview() {
    AdditiveDetailBox(
        additive = DetailedAdditive(
            id = 0,
            eCode = "E100",
            eType = "Test",
            title = "Test Additive",
            info = "lorem ipsum fewa gewa gewar ywhg wrqa bhewar aewrab awegad awtaweg f awega ga werqwetaw "
        )
    )
}