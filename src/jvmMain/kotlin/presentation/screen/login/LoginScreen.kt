package presentation.screen.login

import R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.stringResource
import org.koin.core.component.inject
import presentation.App
import presentation.component.fluent.navigation.MenuItem
import presentation.component.plain.AsyncImage
import presentation.component.plain.loadImageBitmap
import presentation.icons.Icons
import presentation.icons.regular.Eye
import presentation.screen.home.HomeScreen
import presentation.screen.universal.ErrorComposable
import presentation.screen.universal.LoadingComposable
import presentation.utils.Screen

class LoginScreen : Screen() {
    override val viewModel: LoginViewModel by inject()

    @Composable
    override fun content() {
        val surfaceStrokeColor = MaterialTheme.colors.surface.copy(0.05f)
        val surfaceStroke = BorderStroke(width = 1.dp, color = surfaceStrokeColor)

        val scope = rememberCoroutineScope()
        val jellyfinApi = rememberSaveable { App().jellyfinApi }
        val jellyfinRepository = rememberSaveable { App().jellyfinRepository }

        viewModel.init(scope, jellyfinRepository, jellyfinApi)
        val uiState by viewModel.uiState.collectAsState()

        when (uiState) {
            is LoginViewModel.UiState.Normal -> {
//                var server by remember { mutableStateOf("http://localhost:8096") }
//                var account by remember { mutableStateOf("阿睿") }
//                var password by remember { mutableStateOf("123456zzr") }
                if (uiState is LoginViewModel.UiState.Normal) {
                    Row {
                        Column(
                            modifier = Modifier.align(Alignment.CenterVertically).widthIn(max = 280.dp).padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            var server by remember { mutableStateOf((uiState as LoginViewModel.UiState.Normal).server.orEmpty()) }
                            var account by remember { mutableStateOf((uiState as LoginViewModel.UiState.Normal).username.orEmpty()) }
                            var password by remember { mutableStateOf((uiState as LoginViewModel.UiState.Normal).password.orEmpty()) }
                            var isPasswordVisualTransformed by remember { mutableStateOf(true) }
                            Image(
                                painter = painterResource("icons/icon_launcher.webp"),
                                contentDescription = stringResource(R.strings.universal_app_name),
                                modifier = Modifier.size(64.dp)
                            )
                            Text(text = stringResource(R.strings.login), style = MaterialTheme.typography.h2)
                            Text(
                                text = stringResource(R.strings.login_description),
                                style = MaterialTheme.typography.body1
                            )
                            Spacer(Modifier.height(16.dp))
                            OutlinedTextField(
                                value = server,
                                onValueChange = { server = it },
                                label = { Text(stringResource(R.strings.login_server)) })
                            OutlinedTextField(
                                value = account,
                                onValueChange = { account = it },
                                label = { Text(stringResource(R.strings.login_username)) })
                            OutlinedTextField(
                                value = password,
                                onValueChange = { password = it },
                                label = { Text(stringResource(R.strings.login_password)) },
                                trailingIcon = {
                                    MenuItem(
                                        icon = Icons.Regular.Eye,
                                        onClick = { isPasswordVisualTransformed = !isPasswordVisualTransformed })
                                },
                                visualTransformation =
                                if (isPasswordVisualTransformed) PasswordVisualTransformation()
                                else VisualTransformation.None
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Button(onClick = {
                                    viewModel.login(server = server, username = account, password = password)
                                }) {
                                    Text(stringResource(R.strings.login))
                                }
                                MenuItem(onClick = {}) {
                                    Text(
                                        text = stringResource(R.strings.login_login_by_pin),
                                        style = MaterialTheme.typography.body2
                                    )
                                }
                            }
                        }
                        Spacer(
                            Modifier.fillMaxHeight().width(1.dp).background(surfaceStrokeColor).border(surfaceStroke)
                        )
                        Box(Modifier.weight(1f).fillMaxHeight()) {
                            AsyncImage(
                                load = { loadImageBitmap("https://api.kdcc.cn/img/") },
                                painterFor = { BitmapPainter(it) },
                                contentDescription = "Bing Image",
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }
                }
            }

            is LoginViewModel.UiState.Error -> ErrorComposable(
                errorMessage = (uiState as LoginViewModel.UiState.Error).error.message.orEmpty()
            )

            is LoginViewModel.UiState.Loading -> LoadingComposable()
            is LoginViewModel.UiState.Logged -> navigator?.navigate(HomeScreen())
        }
    }
}