package app.suhasdissa.foode.ui.screens

import android.content.ClipData
import android.view.SoundEffectConstants
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material.icons.filled.SentimentNeutral
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import app.suhasdissa.foode.R
import app.suhasdissa.foode.backend.database.entities.AdditivesEntity
import app.suhasdissa.foode.backend.viewmodels.AdditiveDetailViewModel
import app.suhasdissa.foode.backend.viewmodels.states.TranslationState
import app.suhasdissa.foode.ui.components.TranslationStateDialog
import app.suhasdissa.foode.utils.openBrowser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdditiveDetailScreen(
    id: Int,
    additiveViewModel: AdditiveDetailViewModel = viewModel(
        factory = AdditiveDetailViewModel.Factory
    )
) {
    val view = LocalView.current
    LaunchedEffect(id) {
        if (id != 0) additiveViewModel.getAdditive(id)
    }
    var showTranslationState by remember { mutableStateOf(false) }
    if (additiveViewModel.additive == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(stringResource(R.string.select_an_additive))
        }
    }

    if (showTranslationState) {
        TranslationStateDialog(
            state = additiveViewModel.translationState,
            onDismissRequest = { showTranslationState = false }
        )
    }

    additiveViewModel.additive?.let { additive ->
        Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
            TopAppBar(title = {
                Text(
                    additive.eCode,
                    overflow = TextOverflow.Ellipsis
                )
            }, actions = {
                if (additiveViewModel.translationState != TranslationState.NotTranslated) {
                    IconButton(onClick = {
                        view.playSoundEffect(SoundEffectConstants.CLICK)
                        showTranslationState = true
                    }) {
                        Icon(
                            imageVector = Icons.Default.Translate,
                            contentDescription = stringResource(
                                id = R.string.translation_state
                            )
                        )
                    }
                }
            })
        }, floatingActionButton = {
            Column {
                var isFavourite by remember {
                    mutableIntStateOf(additive.isFavourite)
                }
                FloatingActionButton(onClick = {
                    view.playSoundEffect(SoundEffectConstants.CLICK)
                    val copyText =
                        "${additive.eCode} : ${additive.title}\n\nHalal Status : ${additive.halalStatus}\n\n${additive.info}"
                    val clip: ClipData = ClipData.newPlainText("Additive Details", copyText)
                    additiveViewModel.getClipboard().setPrimaryClip(clip)
                }) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = stringResource(R.string.copy_text_to_clipboard)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                FloatingActionButton(onClick = {
                    view.playSoundEffect(SoundEffectConstants.CLICK)
                    when (isFavourite) {
                        0 -> {
                            isFavourite = 1
                            additiveViewModel.setFavourite(1)
                        }

                        else -> {
                            isFavourite = 0
                            additiveViewModel.setFavourite(0)
                        }
                    }
                }) {
                    when (isFavourite) {
                        0 -> Icon(
                            imageVector = Icons.Filled.StarOutline,
                            contentDescription = stringResource(R.string.add_to_favourite)
                        )

                        else -> Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = stringResource(R.string.remove_from_favourite)
                        )
                    }
                }
            }
        }) { innerPadding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                if (additiveViewModel.translationState is TranslationState.Loading) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
                AdditiveDetailBox(
                    additive = additive
                )
            }
        }
    }
}

@Composable
fun AdditiveDetailBox(
    modifier: Modifier = Modifier,
    additive: AdditivesEntity
) {
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
                    Text(
                        additive.title,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.titleLarge.merge(
                            textDirection = TextDirection.ContentOrLtr
                        )
                    )
                    Text(additive.eCode, style = MaterialTheme.typography.titleSmall)
                }
            }
        }
        if (additive.halalStatus == 1) {
            item {
                Card(
                    modifier
                        .fillMaxWidth()
                ) {
                    Column {
                        var isExpanded by remember { mutableStateOf(false) }
                        Row(
                            modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .clickable { isExpanded = !isExpanded },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                stringResource(R.string.halal_status),
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.weight(1f))
                            Image(
                                modifier = Modifier.size(64.dp),
                                painter = painterResource(id = R.drawable.halal_certified_stamp),
                                contentDescription = stringResource(
                                    id = R.string.halal_status_halal
                                )
                            )
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = null
                            )
                        }
                        AnimatedVisibility(
                            isExpanded
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                val context = LocalContext.current
                                Text(
                                    stringResource(R.string.halal_description),
                                    style = MaterialTheme.typography.bodySmall,
                                    fontStyle = FontStyle.Italic
                                )
                                OutlinedButton(onClick = {
                                    openBrowser(
                                        context,
                                        "https://en.wikipedia.org/wiki/Halal"
                                    )
                                }) {
                                    Text(
                                        stringResource(R.string.learn_more_button),
                                        style = MaterialTheme.typography.bodySmall
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
        if (additive.healthRating != 0) {
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
                        Text(
                            stringResource(R.string.health_rating),
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )

                        when (additive.healthRating) {
                            1 -> Icon(
                                imageVector = Icons.Filled.SentimentDissatisfied,
                                contentDescription = stringResource(R.string.health_rating_bad)
                            )

                            2 -> Icon(
                                imageVector = Icons.Filled.SentimentNeutral,
                                contentDescription = stringResource(R.string.health_rating_normal)
                            )

                            3 -> Icon(
                                imageVector = Icons.Filled.SentimentSatisfied,
                                contentDescription = stringResource(R.string.health_rating_good)
                            )

                            else -> Icon(
                                imageVector = Icons.Filled.QuestionMark,
                                contentDescription = stringResource(R.string.health_rating_unknown)
                            )
                        }
                    }
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
                        Text(
                            additive.info,
                            style = MaterialTheme.typography.bodyLarge.merge(
                                textDirection = TextDirection.ContentOrLtr
                            )
                        )
                    }
                }
            }
        }
        item {
            Spacer(Modifier.height(200.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdditiveDetailBoxPreview() {
    AdditiveDetailBox(
        additive = AdditivesEntity(
            id = 0,
            eCode = "E100",
            eType = "Test",
            title = "Test Additive",
            info = "lorem ipsum fewa gewa gewar ywhg ",
            halalStatus = 1,
            isFavourite = 0,
            healthRating = 0
        )
    )
}
