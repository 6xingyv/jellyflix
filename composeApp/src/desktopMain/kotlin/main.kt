import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.mayakapps.compose.windowstyler.WindowBackdrop
import com.mayakapps.compose.windowstyler.WindowStyle
import com.simonscholz.jellyflix.App
import com.simonscholz.jellyflix.Res
import com.simonscholz.jellyflix.theme.AppTheme
import com.simonscholz.jellyflix.theme.onMica
import com.simonscholz.jellyflix.utils.window.TitleBar

fun main() = application {
    val state = rememberWindowState(position = WindowPosition.Aligned(Alignment.Center))
    var menuState by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    Window(
        state = state,
        title = Res.string.universal_app_name,
        onCloseRequest = ::exitApplication,
    ) {
        WindowStyle(
            isDarkTheme = isSystemInDarkTheme(), backdropType = WindowBackdrop.Mica
        )
        AppTheme {
            Surface(
                color = Color.Transparent, contentColor = MaterialTheme.colorScheme.onMica
            ) {
                Column {
                    TitleBar {
                        Row(
                            modifier = Modifier.fillMaxWidth().background(Color.Transparent)
                                .padding(16.dp, 0.dp, 8.dp, 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(Res.string.universal_app_name)
                            }

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                TextButton(
                                    onClick = {
//                                        state.isMinimized = !state.isMinimized
                                        menuState = !menuState
                                    }, colors = ButtonDefaults.textButtonColors(
                                        contentColor = MaterialTheme.colorScheme.onMica
                                    )
                                ) {
                                    Icon(Icons.Rounded.Menu, null)
                                }
                                TextButton(
                                    ::exitApplication, colors = ButtonDefaults.textButtonColors(
                                        contentColor = MaterialTheme.colorScheme.onMica
                                    )
                                ) {
                                    Icon(Icons.Rounded.Close, null)
                                }
                            }
                        }
                    }
//                    Box(Modifier.fillMaxWidth().height(1.dp).background(Color.DarkGray))
                    App()
                }
            }
        }
    }
}

