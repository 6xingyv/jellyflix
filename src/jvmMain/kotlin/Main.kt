import androidx.compose.ui.window.application
import di.appModule
import di.viewModelModule
import org.koin.core.context.startKoin
import presentation.winodw.MainWindow

fun main() {
    startKoin {
        modules(appModule, viewModelModule)
        application {
            MainWindow(onCloseRequest = ::exitApplication)
        }
    }
}
