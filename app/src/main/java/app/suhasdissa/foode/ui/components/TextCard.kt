package app.suhasdissa.foode.ui.components

import android.view.SoundEffectConstants
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextCard(
    modifier: Modifier = Modifier,
    clickAction: () -> Unit,
    mainText: String,
    subText: String
) {
    val view = LocalView.current
    ElevatedCard(
        onClick = {
            view.playSoundEffect(SoundEffectConstants.CLICK)
            clickAction()
        },
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()

    ) {
        Column(
            modifier.padding(8.dp)
        ) {
            Text(
                text = mainText,
                maxLines = 1,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier.height(8.dp))
            Text(
                text = subText,
                maxLines = 1,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
