package app.suhasdissa.foode.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import app.suhasdissa.foode.R
import app.suhasdissa.foode.backend.viewmodels.states.TranslationState

@Composable
fun TranslationStateDialog(state: TranslationState, onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest) {
        Surface(shape = RoundedCornerShape(16.dp)) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = stringResource(R.string.translation_state),
                    style = MaterialTheme.typography.titleMedium
                )
                val text = when (state) {
                    TranslationState.Error -> stringResource(R.string.error)
                    TranslationState.Loading -> stringResource(R.string.loading)
                    TranslationState.NotTranslated -> stringResource(R.string.not_translated)
                    is TranslationState.Success -> stringResource(R.string.translation_success)
                    TranslationState.NotSupported -> stringResource(R.string.language_not_supported)
                }
                Text(text = text, style = MaterialTheme.typography.bodyLarge)
                Button(onClick = { onDismissRequest.invoke() }) {
                    Text(text = stringResource(R.string.ok))
                }
            }
        }
    }
}
