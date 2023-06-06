package app.suhasdissa.foode.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.suhasdissa.foode.R

@Composable
fun IllustratedMessageScreen(
    @DrawableRes image: Int, @StringRes contentDescription: Int, modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center, modifier = modifier.fillMaxSize().alpha(0.3f)
    ) {
        Image(
            modifier = Modifier.size(350.dp),
            painter = painterResource(id = R.drawable.blob),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.tertiaryContainer)
        )
        Image(
            modifier = Modifier.size(250.dp),
            painter = painterResource(id = image),
            contentDescription = stringResource(id = contentDescription),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onTertiaryContainer)
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun IllustratedMsgScreenPreview() {
    IllustratedMessageScreen(
        image = R.drawable.empty_barcode_list_icon, contentDescription = R.string.list_is_empty
    )
}