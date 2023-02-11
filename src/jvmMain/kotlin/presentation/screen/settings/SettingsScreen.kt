package presentation.screen.settings

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import presentation.icons.regular.*
import presentation.screen.universal.ErrorComposable
import presentation.utils.Screen

class SettingsScreen : Screen() {

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    override fun content() {
        ErrorComposable(Exception("Still in development"))
// Todo: Fix bugs
//
//        val surfaceStrokeColor = MaterialTheme.colors.surface.copy(0.05f)
//        val surfaceStroke = BorderStroke(width = 1.dp, color = surfaceStrokeColor)
//
//        navigator?.navigate(GeneralFragment())
//
//        Row {
//            Column(
//                modifier = Modifier.fillMaxHeight().widthIn(max = 280.dp).padding(8.dp),
//                verticalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                // Todo: Find a method to check destination
//                MenuItem(
//                    icon = Icons.Regular.AppGeneric,
//                    label = {
//                        Column {
//                            Text(stringResource(R.strings.settings_general), style = MaterialTheme.typography.body2)
//                            Text(
//                                text = stringResource(R.strings.settings_general_description),
//                                style = MaterialTheme.typography.caption,
//                                color = LocalContentColor.current.copy(0.6f)
//                            )
//                        }
//                    },
//                    modifier = Modifier.fillMaxWidth(),
//                    selected = navigator?.currentFragment() is GeneralFragment,
//                    onClick = {
//                        navigator?.navigate(GeneralFragment())
//                    })
//                MenuItem(
//                    icon = Icons.Regular.LocalLanguage,
//                    label = {
//                        Column {
//                            Text(stringResource(R.strings.settings_language), style = MaterialTheme.typography.body2)
//                            Text(
//                                text = stringResource(R.strings.settings_language_description),
//                                style = MaterialTheme.typography.caption,
//                                color = LocalContentColor.current.copy(0.6f)
//                            )
//                        }
//                    },
//                    modifier = Modifier.fillMaxWidth(),
//                    selected = navigator?.currentFragment() is LanguageFragment,
//                    onClick = {
//                        navigator?.navigate(LanguageFragment())
//                    })
//                MenuItem(
//                    icon = Icons.Regular.Play,
//                    label = {
//                        Column {
//                            Text(stringResource(R.strings.settings_playback), style = MaterialTheme.typography.body2)
//                            Text(
//                                text = stringResource(R.strings.settings_playback_description),
//                                style = MaterialTheme.typography.caption,
//                                color = LocalContentColor.current.copy(0.6f)
//                            )
//                        }
//                    },
//                    modifier = Modifier.fillMaxWidth(),
//                    selected = navigator?.currentFragment() is PlaybackFragment,
//                    onClick = {
//                        navigator?.navigate(PlaybackFragment())
//                    })
//                MenuItem(
//                    icon = Icons.Regular.Info,
//                    label = {
//                        Column {
//                            Text(stringResource(R.strings.settings_about), style = MaterialTheme.typography.body2)
//                            Text(
//                                text = stringResource(R.strings.settings_about_description),
//                                style = MaterialTheme.typography.caption,
//                                color = LocalContentColor.current.copy(0.6f)
//                            )
//                        }
//                    },
//                    modifier = Modifier.fillMaxWidth(),
//                    selected = navigator?.currentFragment() is AboutFragment,
//                    onClick = {
//                        navigator?.navigate(AboutFragment())
//                    })
//            }
//            Spacer(Modifier.fillMaxHeight().width(1.dp).background(surfaceStrokeColor).border(surfaceStroke))
//            Surface(Modifier.fillMaxHeight().weight(1f)) {
//                Box(Modifier.fillMaxSize()) {
//                    navigator?.currentFragment()?.content()
//                }
//            }
//            LaunchedEffect(navigator?.currentFragment()) {
//                println(navigator?.currentFragment())
//            }
    }
}