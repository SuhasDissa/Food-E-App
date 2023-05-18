package app.suhasdissa.foode.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.suhasdissa.foode.backend.database.entities.AdditivesEntity
import app.suhasdissa.foode.backend.repositories.data.DetailedAdditive

@Composable
fun CardGrid(
    songs: List<AdditivesEntity>,
    modifier: Modifier = Modifier,
    onClickTextCard: (url: Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(500.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(items = songs) { item ->
            TextCard(
                clickAction = { onClickTextCard(item.id) },
                mainText = item.eCode,
                subText = item.title
            )
        }

    }
}