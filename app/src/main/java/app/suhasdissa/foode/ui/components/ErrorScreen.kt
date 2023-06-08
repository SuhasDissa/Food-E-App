package app.suhasdissa.foode.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
fun ErrorScreen(
    barcode: String, modifier: Modifier = Modifier
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(id = R.string.something_went_wrong),
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.alpha(0.5f)
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxWidth()
                .height(350.dp)
                .alpha(0.3f)
        ) {
            Image(
                modifier = Modifier.size(350.dp),
                painter = painterResource(id = R.drawable.blob),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.errorContainer)
            )
            Image(
                modifier = Modifier.size(250.dp),
                painter = painterResource(id = R.drawable.broken_egg_icon),
                contentDescription = stringResource(id = R.string.product_not_found),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onErrorContainer)
            )
        }

        Text("Barcode: $barcode", style = MaterialTheme.typography.titleMedium)
    }
}

@Composable
@Preview(showBackground = true)
private fun ErrorScreenPreview() {
    ErrorScreen("03985096362")
}