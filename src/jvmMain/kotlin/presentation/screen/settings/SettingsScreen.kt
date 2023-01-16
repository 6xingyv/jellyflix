package presentation.screen.settings

import R
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.model.SettingsItem
import dev.icerock.moko.resources.compose.stringResource
import presentation.component.fluent.navigation.MenuItem
import presentation.icons.Icons
import presentation.icons.regular.*
import presentation.utils.Screen

class SettingsScreen : Screen() {

    @OptIn(ExperimentalAnimationApi::class)
    @Composable
    override fun content() {
        val surfaceStrokeColor = MaterialTheme.colors.surface.copy(0.05f)
        val surfaceStroke = BorderStroke(width = 1.dp, color = surfaceStrokeColor)

        val settingsList = listOf(
            SettingsItem(
                Icons.Regular.AppGeneric,
                R.strings.settings_general,
                R.strings.settings_general_description,
                GeneralFragment()
            ),
            SettingsItem(
                Icons.Regular.LocalLanguage,
                R.strings.settings_language,
                R.strings.settings_language_description,
                LanguageFragment()
            ),
            SettingsItem(
                Icons.Regular.Play,
                R.strings.settings_playback,
                R.strings.settings_playback_description,
                PlaybackFragment()
            ),
            SettingsItem(
                Icons.Regular.Info,
                R.strings.settings_about,
                R.strings.settings_about_description,
                AboutFragment()
            ),
        )
        var selectedSettingsItem by remember { mutableStateOf(settingsList[0]) }

        selectedSettingsItem.fragment?.let { navigator?.navigate(it) }

        Row {
            Column(
                modifier = Modifier.fillMaxHeight().widthIn(max = 280.dp).padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                settingsList.forEach {
                    MenuItem(icon = it.icon,
                        label = {
                            Column {
                                Text(stringResource(it.label), style = MaterialTheme.typography.body2)
                                Text(
                                    text = stringResource(it.description),
                                    style = MaterialTheme.typography.caption,
                                    color = LocalContentColor.current.copy(0.6f)
                                )
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        selected = selectedSettingsItem.label == it.label,
                        onClick = {
                            selectedSettingsItem = it
                            it.fragment?.let { it1 -> navigator?.navigate(it1) }
                        })
                }
            }
            Spacer(Modifier.fillMaxHeight().width(1.dp).background(surfaceStrokeColor).border(surfaceStroke))
            Surface(Modifier.fillMaxHeight().weight(1f)) {
                Box(Modifier.fillMaxSize()) {
                    AnimatedContent(
                        modifier = Modifier.fillMaxSize(),
                        targetState = navigator?.currentFragment(),
                        transitionSpec = {
                            fadeIn(animationSpec = tween(200)) with
                                    fadeOut(animationSpec = tween(200))
                        }
                    ) {
                        it?.content()
                    }
                }
            }
        }
    }
}