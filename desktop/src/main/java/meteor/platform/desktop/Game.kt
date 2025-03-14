package meteor.platform.desktop

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageBitmapConfig
import androidx.compose.ui.graphics.toComposeImageBitmap
import jagex3.client.Client
import jagex3.client.applet.GameShell
import meteor.platform.common.Common.clientInstance
import meteor.platform.common.Common.eventbus
import meteor.platform.common.world.WorldsCommon.worlds
import net.runelite.api.events.DrawFinished
import nulled.Configuration
import java.awt.image.BufferedImage
import java.net.InetAddress

/**
 * [gameImage]
 *  Compose format image where final images are drawn
 * [loadingImage]
 *  Game draws loading screens outside of draw() in a busy loop
 *  directly to parent containers graphics
 *  Hooking that would require code changes in deob, so we do it this way:
 *  While loading, the current image is drawn every 1ms
 *  Then the loading draw thread is discarded when draw() events fire off naturally
 */
object Game {
    val gameImage = mutableStateOf<ImageBitmap?>(null)
    val loadingImage = mutableStateOf<ImageBitmap?>(null)

    lateinit var bufferedImage : BufferedImage

    init {
        eventbus.subscribe<DrawFinished> {
            gameImage.value = GameShell.drawArea.image.toComposeImageBitmap()
        }
    }

    fun init() {
        Configuration.initializeFrame = false
        clientInstance = Client() as net.runelite.api.Client
        clientInstance.setCallbacks(Hooks)
/*        Client.nodeId = 10
        Client.portOffset = 0
        Client.setHighMemory()
        Client.members = false
        signlink.startpriv(InetAddress.getByName("localhost"))
        Configuration.INTERCEPT_GRAPHICS = true*/
        clientInstance.`initApplication$api`(765, 503, 1)
    }
}