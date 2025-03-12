package meteor.platform.desktop

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.window.application
import meteor.logger.Logger
import meteor.platform.common.Common
import meteor.platform.common.Common.logger
import meteor.platform.common.Common.startupTime
import meteor.platform.common.plugin.PluginManager
import meteor.platform.desktop.ui.MeteorWindow.MeteorWindow

object Main {
    private var started = false

    private val volatileLogger = Logger()

    init {
        Common.isAndroid = false
/*        Logger.logFile = File(Configuration.dataDir, "log.txt")*/
        Game.init()
        val gameInit = System.currentTimeMillis() - startupTime
        logger.info("Game init @ ${gameInit}ms")
        PluginManager.start()
        val pluginsInit = System.currentTimeMillis() - startupTime
        logger.info("PluginManager init @ ${pluginsInit}ms")
    }

    @JvmStatic
    fun main(args: Array<String>) = application {
        MeteorWindow()

        if (!started) {
            LaunchedEffect(Unit) {
                val composeInit = System.currentTimeMillis() - startupTime
                logger.info("Meteor started @ ${composeInit}ms")
            }
            started = true
        }
    }
}