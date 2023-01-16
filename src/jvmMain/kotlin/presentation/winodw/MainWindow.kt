package presentation.winodw

import R
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import com.mayakapps.compose.windowstyler.WindowBackdrop
import com.mayakapps.compose.windowstyler.WindowStyle
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.launch
import org.jetbrains.skiko.OS
import org.jetbrains.skiko.hostOs
import presentation.component.fluent.navigation.MenuItem
import presentation.icons.Icons
import presentation.icons.regular.*
import presentation.navigation.Navigator
import presentation.screen.home.HomeScreen
import presentation.screen.login.LoginScreen
import presentation.screen.settings.SettingsScreen
import presentation.theme.JellyTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
@Preview
fun MainWindow(onCloseRequest: () -> Unit) {
    val state = rememberWindowState(position = WindowPosition.Aligned(Alignment.Center))
    JellyTheme {
        Window(
            state = state,
            onCloseRequest = onCloseRequest,
            title = stringResource(R.strings.universal_app_name),
            icon = painterResource("icons/icon_launcher.webp")
        ) {
            WindowStyle(
                isDarkTheme = isSystemInDarkTheme(),
                backdropType = if (hostOs == OS.Windows) WindowBackdrop.Mica else WindowBackdrop.Solid(MaterialTheme.colors.background)
            )

            val surfaceStrokeColor = MaterialTheme.colors.surface.copy(0.05f)
            val surfaceStroke = BorderStroke(width = 1.dp, color = surfaceStrokeColor)

            var drawerIsOpen by remember { mutableStateOf(true) }

            val frameCornerDp = 8.dp
            val frameShape = RoundedCornerShape(topStart = frameCornerDp)

            val coroutineScope = rememberCoroutineScope()

            val navigator = Navigator(LoginScreen())
            Surface(
                color = Color.Transparent,
                contentColor = MaterialTheme.colors.onSurface
            ) {
                Row(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .widthIn(max = 240.dp)
                            .padding(horizontal = 8.dp).padding(bottom = 8.dp)
                            .animateContentSize(tween(500)),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        MenuItem(
                            icon = Icons.Regular.Navigation,
                            label = "",
                            compact = !drawerIsOpen,
                            onClick = {
                                coroutineScope.launch {
                                    drawerIsOpen = !drawerIsOpen
                                }
                            }
                        )
                        MenuItem(
                            icon = Icons.Regular.Home,
                            label = stringResource(R.strings.universal_home),
                            modifier = Modifier.fillMaxWidth(),
                            selected = navigator.currentScreen() is HomeScreen,
                            compact = !drawerIsOpen,
                            onClick = {
                                coroutineScope.launch {
                                    navigator.navigate(HomeScreen())
                                }
                            }
                        )
                        Spacer(Modifier.weight(1f))
                        MenuItem(
                            icon = Icons.Regular.Person,
                            label = stringResource(R.strings.universal_account),
                            modifier = Modifier.fillMaxWidth(),
                            selected = navigator.currentScreen() is LoginScreen,
                            compact = !drawerIsOpen,
                            onClick = {
                                coroutineScope.launch {
                                    navigator.navigate(LoginScreen())
                                }
                            }
                        )
                        MenuItem(
                            icon = Icons.Regular.Settings,
                            label = stringResource(R.strings.universal_settings),
                            modifier = Modifier.fillMaxWidth(),
                            selected = navigator.currentScreen() is SettingsScreen,
                            compact = !drawerIsOpen,
                            onClick = {
                                coroutineScope.launch {
                                    drawerIsOpen = false
                                    navigator.navigate(SettingsScreen())
                                }
                            }
                        )
                    }
                    Surface(
                        modifier = Modifier.fillMaxHeight().weight(1f).border(surfaceStroke, frameShape),
                        color = MaterialTheme.colors.surface,
                        contentColor = MaterialTheme.colors.onSurface,
                        shape = frameShape
                    ) {
                        Box(Modifier.fillMaxSize()) {
                            AnimatedContent(
                                modifier = Modifier.fillMaxSize(),
                                targetState = navigator.currentScreen(),
                                transitionSpec = {
                                    fadeIn(animationSpec = tween(200)) with
                                            fadeOut(animationSpec = tween(200))
                                }
                            ) {
                                it.content()
                            }
                        }
                    }
                }
            }
        }
    }
}