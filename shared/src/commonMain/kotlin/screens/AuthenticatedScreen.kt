package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.bottomSheet.LocalBottomSheetNavigator
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import micro.shared.generated.resources.Res
import micro.shared.generated.resources.photo
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import screens.create.TaskSheet
import screens.home.HomeTab
import screens.home.NotificationsTab
import screens.home.SettingsTab
import screens.schedule.ScheduleTab
import utils.AppIconButton
import utils.NoRippleTheme

object AuthenticatedScreen : Screen {

    @Composable
    override fun Content() {
        val bottomSheetNav = LocalBottomSheetNavigator.current
        TabNavigator(HomeTab) {
            Scaffold(
                content = {
                    Box(modifier = Modifier.padding(it)) {
                        CurrentTab()
                    }
                },
                bottomBar = {
                    Box() {
                        BottomNavigation(
                            modifier = Modifier.systemBarsPadding().padding(top = 24.dp)
                                .background(MaterialTheme.colorScheme.primaryContainer).zIndex(3f),
                            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                            elevation = 0.dp
                        ) {
                            TabNavigationItem(HomeTab)
                            TabNavigationItem(ScheduleTab)
                            Spacer(Modifier.width(60.dp))
                            TabNavigationItem(NotificationsTab)
                            TabNavigationItem(SettingsTab)
                        }

                        AppIconButton(modifier = Modifier.size(60.dp).zIndex(4f)
                            .align(Alignment.TopCenter),
                            onClick = {
                                bottomSheetNav.show(TaskSheet())
                            })
                    }
                }
            )
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    val color = if (tabNavigator.current == tab) {
        MaterialTheme.colorScheme.primary.copy(alpha = .3f)
    } else {
        MaterialTheme.colorScheme.primaryContainer
    }

    val ripple = LocalRippleTheme.current
    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        BottomNavigationItem(
            selected = tabNavigator.current == tab,
            onClick = { tabNavigator.current = tab },
            icon = {
                CompositionLocalProvider(LocalRippleTheme provides ripple) {
                    FilledTonalIconButton(
                        modifier = Modifier.width(64.dp),
                        onClick = { tabNavigator.current = tab },
                        colors = IconButtonDefaults.filledTonalIconButtonColors(
                            containerColor = color
                        )
                    ) {
                        Column(Modifier.align(Alignment.CenterVertically)) {
                            Icon(
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                painter = tab.options.icon ?: return@Column,
                                contentDescription = tab.options.title,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        )
    }
}