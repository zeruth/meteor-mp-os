package meteor.platform.desktop.ui

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateSetOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import meteor.platform.desktop.Game.gameImage
import meteor.platform.desktop.Game.loadingImage
import meteor.platform.common.ui.components.panel.PanelComposables.Panel
import meteor.platform.common.ui.UI.panelOpen
import meteor.platform.common.ui.components.sidebar.SidebarButton
import meteor.platform.common.ui.components.sidebar.SidebarComposables
import meteor.platform.desktop.ui.GameView.GameViewContainer
import meteor.platform.desktop.ui.GameView.focusRequester
import meteor.platform.desktop.ui.GameView.stretchedMode
import meteor.platform.desktop.ui.buttons.CloseMeteorButton
import meteor.platform.desktop.ui.buttons.FpsDisplayButton
import meteor.platform.desktop.ui.buttons.FullscreenToggleButton
import meteor.platform.desktop.ui.buttons.StretchToggleButton
import java.awt.Dimension

object MeteorWindow {
    val sidebarWidth = mutableStateOf(40.dp)
    val configWidth = mutableStateOf(300.dp)
    var fixedWindowSize = Dimension(765 + sidebarWidth.value.value.toInt(), 503)
    var fixedState = mutableStateOf(false)
    val floatingState = WindowState(
        position = WindowPosition(Alignment.Center),
        placement = WindowPlacement.Floating)
    var fullscreenState = WindowState(
        position = WindowPosition(Alignment.Center),
        placement = WindowPlacement.Maximized)
    val windowState = mutableStateOf(floatingState)
    lateinit var windowInstance: ComposeWindow

    var panelWasOpen = false

    fun resetWindowSize() {
        windowInstance.minimumSize = Dimension(fixedWindowSize.width, fixedWindowSize.height)
        val resetBounds = !stretchedMode.value
        val height = if (resetBounds) fixedWindowSize.height else windowInstance.height
        var width = if (resetBounds) fixedWindowSize.width else windowInstance.width

        if (panelWasOpen != panelOpen.value) {
            if (panelOpen.value) {
                width += configWidth.value.value.toInt()
            } else {
                width -= configWidth.value.value.toInt()
            }
            panelWasOpen = panelOpen.value
        }

        if (windowState.value == fullscreenState) {
            windowState.value = fullscreenState
        } else {
            windowInstance.size = Dimension(width, height)
        }

    }

    val closeMeteorButton = CloseMeteorButton()
    val fullscreenToggleButton = FullscreenToggleButton()
    val stretchToggleButton = StretchToggleButton()
    val fpsButton = FpsDisplayButton()
    val density = mutableFloatStateOf(1f)
    val pendingResize = mutableStateOf(false)

    val platformButtons = mutableStateSetOf(fpsButton, fullscreenToggleButton)

    @Composable
    fun ApplicationScope.MeteorWindow() {
        key(windowState.value) {
            density.floatValue = LocalDensity.current.density
            var resizable = pendingResize.value || !fixedState.value || (stretchedMode.value && windowState.value != fullscreenState)
            Window(
                onCloseRequest = ::exitApplication,
                title = "Meteor 225 (2.1.0)",
                state = windowState.value,
                undecorated = windowState.value == fullscreenState,
                resizable = if (windowState.value == fullscreenState) false else resizable,
                icon = painterResource("Meteor.ico")
            ) {
                if (pendingResize.value)
                    pendingResize.value = false
                windowInstance = this.window
                val finalImage = if (gameImage.value != null) gameImage else loadingImage
                Row(modifier = Modifier.focusable().focusRequester(focusRequester)) {
                    finalImage.value?.let {
                        GameViewContainer(it)
                    }
                    if (panelOpen.value) {
                        Box(Modifier.fillMaxHeight().width(configWidth.value)) {
                            Panel()
                        }
                    }

                    if (density.floatValue == 1f) {
                        if (windowState.value != fullscreenState)
                            platformButtons.add(stretchToggleButton)
                        else
                            platformButtons.remove(stretchToggleButton)
                    }

                    if (windowState.value != fullscreenState) {
                        platformButtons.remove(closeMeteorButton)
                    } else {
                        platformButtons.add(closeMeteorButton)
                    }

                    SidebarComposables.Sidebar(*platformButtons.toTypedArray())
                }
            }

            LaunchedEffect(Unit) {
                resetWindowSize()
            }
        }
    }
}