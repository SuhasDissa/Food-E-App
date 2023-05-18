package app.suhasdissa.foode.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.foode.R
import app.suhasdissa.foode.backend.database.entities.AdditivesEntity
import app.suhasdissa.foode.backend.repositories.data.DetailedAdditive
import app.suhasdissa.foode.backend.viewmodels.AdditiveDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdditiveDetailScreen(
    id: Int,
    additiveViewModel: AdditiveDetailViewModel = viewModel(factory = AdditiveDetailViewModel.Factory)
) {
    LaunchedEffect(id) {
        if (id != 0) additiveViewModel.getAdditive(id)
    }
    if (additiveViewModel.additive.id == 0) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Select an Additive")
        }
    } else {
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            TopAppBar(title = {
                Text(
                    additiveViewModel.additive.eCode,
                    overflow = TextOverflow.Ellipsis
                )
            })
        }, floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Star, contentDescription = "Search")
            }
        }) { innerPadding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {

                AdditiveDetailBox(additive = additiveViewModel.additive)
            }
        }
    }
}

@Composable
fun AdditiveDetailBox(modifier: Modifier = Modifier, additive: AdditivesEntity) {
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
                Row(
                    modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(stringResource(R.string.halal_status), style = MaterialTheme.typography.bodyLarge)
                    Text(additive.halalStatus, style = MaterialTheme.typography.bodyLarge)
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
        additive = AdditivesEntity(
            id = 0,
            eCode = "E100",
            eType = "Test",
            title = "Test Additive",
            info = "lorem ipsum fewa gewa gewar ywhg wrqa bhewar aewrab awegad awtaweg f awega ga werqwetaw ",
            halalStatus = "Unknown"
        )
    )
}