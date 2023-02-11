@file:Suppress("KotlinConstantConditions")

package presentation.screen.library

import R
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
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
import data.model.LibraryType
import dev.icerock.moko.resources.compose.stringResource
import org.jellyfin.sdk.model.UUID
import org.koin.core.component.inject
import presentation.App
import presentation.component.fluent.collections.ListItem
import presentation.component.jellyflix.ImagePlaceholder
import presentation.component.plain.AsyncImage
import presentation.component.plain.loadImageBitmap
import presentation.screen.universal.ErrorComposable
import presentation.screen.universal.LoadingComposable
import presentation.utils.Screen
import presentation.utils.getJellyfinPrimaryImage

class LibraryScreen(
    private val parentID: UUID,
    private val libraryType: LibraryType,
    private val libraryName: String = ""
) : Screen() {

    override val viewModel: LibraryViewModel by inject()

    @Composable
    override fun content() {
        val scope = rememberCoroutineScope()
        val jellyfinRepository = rememberSaveable { App().jellyfinRepository }

        viewModel.init(scope, jellyfinRepository)
        viewModel.loadItems(
            parentId = parentID,
            libraryType = libraryType
        )

        val uiState by viewModel.uiState.collectAsState()

        when (uiState) {
            is LibraryViewModel.UiState.Error -> ErrorComposable(exception = (uiState as LibraryViewModel.UiState.Error).error)
            is LibraryViewModel.UiState.Loading -> LoadingComposable()
            is LibraryViewModel.UiState.Normal -> {
                // Prevent Error
                if (uiState is LibraryViewModel.UiState.Normal) {
                    val data = (uiState as LibraryViewModel.UiState.Normal).libraryItems
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        columns = GridCells.Adaptive(minSize = 160.dp)
                    ) {
                        item(span = { GridItemSpan(this.maxLineSpan) }) {
                            Text(
                                text = if (libraryName.isNotEmpty())
                                    libraryName
                                else stringResource(R.strings.universal_libraries),
                                style = MaterialTheme.typography.h2
                            )
                        }
                        items(data) {
                            ListItem(padding = 0.dp, onClick = {}) {
                                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                    Box(Modifier.clip(RoundedCornerShape(4.dp)).fillMaxWidth().height(240.dp)) {
                                        ImagePlaceholder(it.name!!)
                                        AsyncImage(
                                            load = { loadImageBitmap(getJellyfinPrimaryImage(it, true)) },
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