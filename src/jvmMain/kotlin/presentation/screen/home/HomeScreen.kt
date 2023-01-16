package presentation.screen.home

import R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import data.model.HomeItem
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.stringResource
import org.jellyfin.sdk.model.api.BaseItemDto
import org.koin.core.component.inject
import presentation.App
import presentation.component.fluent.collections.ListItem
import presentation.component.plain.AsyncImage
import presentation.component.plain.loadImageBitmap
import presentation.screen.universal.ErrorComposable
import presentation.screen.universal.LoadingComposable
import presentation.utils.Screen
import presentation.utils.getJellyfinBaseItemImage

class HomeScreen : Screen() {
    override val viewModel: HomeViewModel by inject()

    @Composable
    override fun content() {
        val scope = rememberCoroutineScope()
        val jellyfinRepository = rememberSaveable { App().jellyfinRepository }

        viewModel.init(scope, jellyfinRepository)
        viewModel.loadData(true)
        val uiState by viewModel.uiState.collectAsState()

        when (uiState) {
            is HomeViewModel.UiState.Loading -> LoadingComposable()
            is HomeViewModel.UiState.Normal -> {
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    columns = GridCells.Adaptive(minSize = 280.dp)
                ) {
                    item(span = { GridItemSpan(this.maxLineSpan) }) {
                        Text(
                            text = stringResource(R.strings.universal_home),
                            style = MaterialTheme.typography.h2
                        )
                    }
                    if (uiState is HomeViewModel.UiState.Normal) {
                        for (
                        item in (uiState as HomeViewModel.UiState.Normal).homeItems
                        ) {
                            lateinit var sectionTitle: StringResource
                            lateinit var sectionItems: List<BaseItemDto>

                            when (item) {
                                is HomeItem.Section -> {
                                    sectionTitle = item.homeSection.name
                                    sectionItems = item.homeSection.items
                                }
                                is HomeItem.Libraries -> {
                                    sectionTitle = item.section.name
                                    sectionItems = item.section.items
                                }
                                else -> {
                                    sectionTitle = R.strings.default_string
                                    sectionItems = emptyList()
                                }
                            }
                            item(span = { GridItemSpan(this.maxLineSpan) }) {
                                Text(
                                    text = stringResource(sectionTitle),
                                    style = MaterialTheme.typography.h3
                                )
                            }
                            items(sectionItems) {
                                ListItem(padding = 0.dp) {
                                    Column() {
                                        Box(Modifier.clip(RoundedCornerShape(4.dp))) {
                                            AsyncImage(
                                                load = { loadImageBitmap(getJellyfinBaseItemImage(it)!!) },
                                                painterFor = { BitmapPainter(it) },
                                                contentDescription = it.name!!,
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .heightIn(max = 168.dp)
                                                    .fillMaxWidth()
                                            )
                                            if (it.userData?.playedPercentage != null) {
                                                LinearProgressIndicator(
                                                    progress = it.userData!!.playedPercentage?.toFloat()?.div(100) ?: 0f,
                                                    modifier = Modifier.align(Alignment.BottomStart).fillMaxWidth()
                                                )
                                            }
                                        }
                                        Text(text = "${it.name}", style = MaterialTheme.typography.body1)
                                        Text(
                                            text = "${it.seasonName} - ${it.seriesName}",
                                            style = MaterialTheme.typography.caption,
                                            color = LocalContentColor.current.copy(0.6f)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            is HomeViewModel.UiState.Error -> ErrorComposable(
                errorMessage = (uiState as HomeViewModel.UiState.Error).error.message.orEmpty()
            )
        }
    }
}