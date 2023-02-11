@file:Suppress("KotlinConstantConditions")

package presentation.screen.home

import R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
import data.model.LibraryType
import dev.icerock.moko.resources.StringResource
import dev.icerock.moko.resources.compose.stringResource
import org.jellyfin.sdk.model.UUID
import org.jellyfin.sdk.model.api.BaseItemDto
import org.koin.core.component.inject
import presentation.App
import presentation.component.fluent.collections.ListItem
import presentation.component.jellyflix.ImagePlaceholder
import presentation.component.plain.AsyncImage
import presentation.component.plain.loadImageBitmap
import presentation.screen.library.LibraryScreen
import presentation.screen.universal.ErrorComposable
import presentation.screen.universal.LoadingComposable
import presentation.utils.Screen
import presentation.utils.getJellyfinBackdropImage

class HomeScreen : Screen() {
    override val viewModel: HomeViewModel by inject()

    @Composable
    override fun content() {
        val scope = rememberCoroutineScope()
        val jellyfinRepository = rememberSaveable { App().jellyfinRepository }

        viewModel.init(scope, jellyfinRepository)
        viewModel.loadData()
        val uiState by viewModel.uiState.collectAsState()

        when (uiState) {
            is HomeViewModel.UiState.Error -> ErrorComposable(
                exception = (uiState as HomeViewModel.UiState.Error).error
            )

            HomeViewModel.UiState.Loading -> LoadingComposable()
            is HomeViewModel.UiState.Normal -> {
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    columns = GridCells.Adaptive(minSize = 280.dp)
                ) {
                    item(span = { GridItemSpan(this.maxLineSpan) }) {
                        Text(
                            text = stringResource(R.strings.universal_home),
                            style = MaterialTheme.typography.h2
                        )
                    }
                    // Prevent Error
                    if (uiState is HomeViewModel.UiState.Normal) {
                        for (item in (uiState as HomeViewModel.UiState.Normal).homeItems) {
                            lateinit var sectionTitle: StringResource
                            lateinit var sectionItems: List<BaseItemDto>
                            lateinit var onClick: (id: UUID, libraryName: String, index: Int) -> Unit

                            when (item) {
                                is HomeItem.Section -> {
                                    sectionTitle = item.homeSection.name
                                    sectionItems = item.homeSection.items
                                    onClick = { uuid: UUID, _: String, _: Int ->
                                        println("Clicked item with id: $uuid")
                                    }
                                }

                                is HomeItem.Libraries -> {
                                    sectionTitle = item.section.name
                                    sectionItems = item.section.items
                                    onClick = { uuid: UUID, s: String, index: Int ->
                                        val libraryType = when (item.section.items[index].collectionType) {
                                            "movies" -> LibraryType.MOVIES
                                            "tvshows" -> LibraryType.SERIES
                                            else -> LibraryType.IDK
                                        }
                                        navigator?.navigate(
                                            LibraryScreen(
                                                libraryType = libraryType,
                                                parentID = uuid,
                                                libraryName = s
                                            )
                                        )
                                    }
                                }

                                else -> {
                                    sectionTitle = R.strings.default_string
                                    sectionItems = emptyList()
                                    onClick = { _: UUID, _: String, _: Int ->

                                    }
                                }
                            }
                            item(span = { GridItemSpan(this.maxLineSpan) }) {
                                Text(
                                    text = stringResource(sectionTitle),
                                    style = MaterialTheme.typography.h3
                                )
                            }
                            itemsIndexed(sectionItems) { index: Int, it: BaseItemDto ->
                                ListItem(padding = 0.dp, onClick = {
                                    val libraryName = if (it.collectionType != null) it.name!! else ""
                                    onClick(it.id, libraryName, index)
                                }) {
                                    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                        Box(Modifier.clip(RoundedCornerShape(4.dp)).fillMaxWidth().height(168.dp)) {
                                            ImagePlaceholder(it.name!!)
                                            AsyncImage(
                                                load = { loadImageBitmap(getJellyfinBackdropImage(it, true)) },
                                                painterFor = { BitmapPainter(it) },
                                                contentDescription = it.name!!,
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier.fillMaxSize()
                                            )
                                            if (it.userData?.playedPercentage != null) {
                                                LinearProgressIndicator(
                                                    progress = it.userData!!.playedPercentage?.toFloat()?.div(100)
                                                        ?: 0f,
                                                    modifier = Modifier.align(Alignment.BottomStart).fillMaxWidth()
                                                )
                                            }
                                        }
                                        if (it.seasonName != null && it.seriesName != null) {
                                            Text(text = "${it.seriesName}", style = MaterialTheme.typography.body1)
                                            Text(
                                                text = "${it.seasonName} - ${it.name}",
                                                style = MaterialTheme.typography.caption,
                                                color = LocalContentColor.current.copy(0.6f)
                                            )
                                        } else {
                                            Text(text = "${it.name}", style = MaterialTheme.typography.body1)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}