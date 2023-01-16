package presentation.screen.settings

import R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.stringResource
import presentation.component.fluent.collections.ListItem
import presentation.component.fluent.layout.Expander
import presentation.component.fluent.navigation.BreadcrumbBar
import presentation.icons.Icons
import presentation.icons.regular.Navigation
import presentation.utils.Fragment

class GeneralFragment :Fragment() {
    @Composable
    override fun content() {
        LazyColumn(contentPadding = PaddingValues(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            item {
                BreadcrumbBar(items = listOf(
                    stringResource(R.strings.settings), stringResource(R.strings.settings_general)
                ), style = MaterialTheme.typography.h2, onClick = {})
            }
            val params = listOf(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lacus suspendisse faucibus interdum posuere lorem ipsum dolor. Maecenas sed enim ut sem viverra. Aliquet eget sit amet tellus cras.",
                "Nulla facilisi etiam dignissim diam quis. In arcu cursus euismod quis viverra nibh cras pulvinar mattis. Eget mi proin sed libero enim sed faucibus. Aliquam ultrices sagittis orci a scelerisque purus semper eget duis. Lacinia quis vel eros donec ac odio tempor orci. Amet purus gravida quis blandit. Donec adipiscing tristique risus nec feugiat in fermentum posuere urna. Ac placerat vestibulum lectus mauris. Tincidunt id aliquet risus feugiat in ante. Sed nisi lacus sed viverra tellus in hac habitasse. Cursus eget nunc scelerisque viverra mauris. In nibh mauris cursus mattis molestie. Sed egestas egestas fringilla phasellus faucibus scelerisque. Tortor at auctor urna nunc id cursus. Sed id semper risus in hendrerit gravida rutrum quisque. Porta lorem mollis aliquam ut porttitor leo.",
                "Non nisi est sit ame faucibus a pellentesque. Ridiculus mus mauris vitae ultricies leo. Diam quam nulla porttitor massa. Pellentesque habitant morbi tristique senectus et netus. Enim facilisis gravida neque convallis a cras semper. Vitae et leo duis ut diam quam nulla. Amet porttitor eget dolor morbi non arcu risus quis. Risus nec feugiat in fermentum posuere urna nec tincidunt praesent. Sapien faucibus et molestie ac. Ac placerat vestibulum lectus mauris ultrices eros in cursus. Enim neque volutpat ac tincidunt vitae semper quis lectus nulla. Felis bibendum ut tristique et egestas quis ipsum suspendisse.",
                "Tincidunt lobortis feugiat vivamus at augue eget arcu dictum varius. Ac tortor dignissim convallis aenean et tortor. Consectetur adipiscing elit pellentesque habitant morbi tristique senectus. Enim diam vulputate ut pharetra sit amet. Consequat ac felis donec et odio pellentesque diam volutpat commodo. Lacus laoreet non curabitur gravida arcu ac tortor. Convallis aenean et tortor at. Praesent tristique magna sit amet purus. Eget aliquet nibh praesent tristique magna sit. Eget mauris pharetra et ultrices neque ornare aenean. Enim nec dui nunc mattis enim ut tellus. Malesuada fames ac turpis egestas maecenas pharetra convallis posuere. Etiam erat velit scelerisque in dictum non. Commodo ullamcorper a lacus vestibulum sed arcu. Rhoncus aenean vel elit scelerisque. Enim blandit volutpat maecenas volutpat blandit. Eu scelerisque felis imperdiet proin fermentum leo vel orci porta. Magna eget est lorem ipsum dolor sit amet. Arcu odio ut sem nulla pharetra diam sit amet nisl.",
                "Nulla posuere sollicitudin aliquam ultrices sagittis orci a scelerisque. Consectetur adipiscing elit duis tristique sollicitudin. Lectus quam id leo in vitae turpis. Bibendum est ultricies integer quis auctor elit sed. Fermentum leo vel orci porta non pulvinar neque laoreet. Velit aliquet sagittis id consectetur. Cras ornare arcu dui vivamus arcu felis bibendum. Penatibus et magnis dis parturient montes nascetur ridiculus. Mauris nunc congue nisi vitae suscipit tellus. Duis ut diam quam nulla. Semper eget duis at tellus at urna condimentum mattis pellentesque. Consequat interdum varius sit amet mattis. Eget nunc scelerisque viverra mauris in aliquam. Ultricies leo integer malesuada nunc vel risus commodo viverra maecenas. Ut placerat orci nulla pellentesque dignissim enim sit. Mattis rhoncus urna neque viverra justo nec. Sapien eget mi proin sed libero enim sed faucibus turpis. Nisl condimentum id venenatis a condimentum vitae. Donec massa sapien faucibus et molestie ac feugiat sed. Sit amet nisl suscipit adipiscing bibendum.",
                "At risus viverra adipiscing at. Ullamcorper velit sed ullamcorper morbi. Nulla facilisi morbi tempus iaculis urna. Dignissim cras tincidunt lobortis feugiat vivamus at augue eget. Ultrices eros in cursus turpis massa tincidunt dui ut. Viverra nibh cras pulvinar mattis nunc sed blandit libero volutpat. Eget egestas purus viverra accumsan in nisl nisi scelerisque eu. Viverra adipiscing at in tellus integer. Lobortis mattis aliquam faucibus purus in massa tempor. Aenean euismod elementum nisi quis eleifend quam adipiscing vitae proin. Nisl pretium fusce id velit ut tortor pretium viverra."
            )
            item {
                Expander(modifier = Modifier.fillParentMaxWidth(), header = {
                    Icon(Icons.Regular.Navigation, Icons.Regular.Navigation.name)
                    Text(
                        text = stringResource(R.strings.settings_general_drawer),
                        style = MaterialTheme.typography.body2
                    )
                }) {
                    Column {
                        params.forEach { ListItem(padding = 8.dp) { Text(it) } }
                    }
                }
            }
        }
    }
}