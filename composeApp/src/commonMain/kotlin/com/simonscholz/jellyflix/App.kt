package com.simonscholz.jellyflix

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.component.ComponentRegistryBuilder
import com.simonscholz.jellyflix.theme.mica
import okio.Path

@Composable
fun App() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Transparent
    ) {
        Row {
            Column(Modifier.width(300.dp)) {

            }
            LazyVerticalGrid(
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .clip(RoundedCornerShape(topStart = 8.dp))
//                    .background(MaterialTheme.colorScheme.mica()),
                contentPadding = PaddingValues(16.dp, 0.dp, 16.dp, 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                columns = GridCells.Adaptive(600.dp)
            ) {
                items(20) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .border(1.dp, Color.DarkGray, MaterialTheme.shapes.medium)
                            .clip(MaterialTheme.shapes.medium)
                            .background(MaterialTheme.colorScheme.mica())
                            .clickable { }
                    )
                }
            }
        }

    }
}

internal expect fun ComponentRegistryBuilder.setupComponents()

internal expect fun getImageCacheDirectoryPath(): Path