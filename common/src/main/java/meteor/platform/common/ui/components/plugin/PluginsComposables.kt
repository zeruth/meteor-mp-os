package meteor.platform.common.ui.components.plugin

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.LineAwesomeIcons
import compose.icons.lineawesomeicons.CogSolid
import compose.icons.lineawesomeicons.StarSolid
import meteor.platform.common.ui.components.config.ConfigComposables.ConfigPanel
import meteor.platform.common.config.ConfigManager
import meteor.platform.common.plugin.Plugin
import meteor.platform.common.ui.components.panel.PanelComposables
import meteor.platform.common.plugin.PluginManager.plugins
import meteor.platform.common.plugin.PluginManager.runningMap
import meteor.platform.common.plugin.meteor.MeteorPlugin
import meteor.platform.common.ui.Colors
import meteor.platform.common.ui.GeneralComposables.SidedNode
import meteor.platform.common.ui.UI.favoritesMap

object PluginsComposables {

    fun PluginList() = @Composable {
        Column(Modifier.fillMaxSize()) {
            val meteorPlugin = plugins.filterIsInstance<MeteorPlugin>().first()
            val nonHiddenPlugins = plugins.filter { it != meteorPlugin && !it.hidden }
            val favorites : List<Plugin> = nonHiddenPlugins.filter {
                if (!favoritesMap.containsKey(it)) {
                    favoritesMap[it] = ConfigManager.get<Boolean>("plugin.${it.name}.isFavorite", false)
                }
                favoritesMap[it] == true
            }.sortedBy { it.name }
            val nonFavorites : List<Plugin> = nonHiddenPlugins.filter { favoritesMap[it] == false }
            PluginNode(meteorPlugin)
            Spacer(Modifier.height(2.dp))
            for (plugin in favorites) {
                PluginNode(plugin)
                Spacer(Modifier.height(2.dp))
            }
            for (plugin in nonFavorites) {
                PluginNode(plugin)
                Spacer(Modifier.height(2.dp))
            }
        }
    }

    @Composable
    fun PluginNode(plugin: Plugin) {
        SidedNode(30,
            left = @Composable {
                Box(Modifier.padding(all = 2.dp).size(30.dp)) {
                    favoritesMap.putIfAbsent(
                        plugin,
                        ConfigManager.get<Boolean>("plugin.${plugin.name}.isFavorite", false)
                    )
                    val isFavorite = favoritesMap[plugin]!!
                    if (isFavorite)
                        Image(
                            LineAwesomeIcons.StarSolid,
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Colors.secondary.value),
                            modifier = Modifier.align(Alignment.Center).clickable {
                                favoritesMap[plugin] = !favoritesMap[plugin]!!
                                ConfigManager.set("plugin.${plugin.name}.isFavorite", favoritesMap[plugin]!!)
                            })
                    else
                        Image(
                            LineAwesomeIcons.StarSolid,
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Colors.surfaceDark.value),
                            modifier = Modifier.align(Alignment.Center).clickable {
                                favoritesMap[plugin] = !favoritesMap[plugin]!!
                                ConfigManager.set("plugin.${plugin.name}.isFavorite", favoritesMap[plugin]!!)
                            })
                }
                Text(
                    plugin.name, Modifier.align(Alignment.CenterVertically),
                    style = TextStyle(color = Colors.secondary.value, fontSize = 18.sp)
                )
            },
            right = @Composable {
                Box(Modifier.padding(all = 2.dp).size(30.dp)) {
                    if (plugin.configuration != null) {
                        Image(
                            LineAwesomeIcons.CogSolid,
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(Colors.secondary.value),
                            modifier = Modifier.align(Alignment.Center).clickable {
                                PanelComposables.secondaryContent.value = { ConfigPanel(plugin.configuration!!) }
                            })
                    }
                }
                runningMap.putIfAbsent(plugin, plugin.running)
                if (!plugin.cantDisable) {
                    Switch(
                        runningMap[plugin]!!,
                        modifier = Modifier.align(Alignment.CenterVertically),
                        onCheckedChange = {
                            ConfigManager.set("plugin.${plugin.name}.enabled", !runningMap[plugin]!!)
                            if (plugin.running) {
                                plugin.stop()
                            } else
                                plugin.start()
                        },
                        colors = SwitchDefaults.colors(
                            uncheckedThumbColor = Colors.surfaceDark.value,
                            checkedThumbColor = Colors.secondary.value
                        )
                    )
                } else {
                    Spacer(modifier = Modifier.width(48.dp))
                }
            })
    }
}