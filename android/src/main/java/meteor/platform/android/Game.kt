package meteor.platform.android

import android.graphics.Bitmap
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import ext.awt.BufferedImageExt.getPixels
import jagex3.client.Client
import meteor.platform.common.Common
import meteor.platform.common.Common.clientInstance
import meteor.platform.common.Common.eventbus
import net.runelite.api.events.DrawFinished
import nulled.Configuration

object Game {
    var receivedDraw = false
    var fps = mutableIntStateOf(0)
    var recentDraws = ArrayList<Long>()
    var image = mutableStateOf<ImageBitmap?>(null)

    //Draws when game reports a frame
    init {
        eventbus.subscribe<DrawFinished> {
            receivedDraw = true
            updateGameImage()
        }
    }

    fun start() {
        Common.isAndroid = true
        Configuration.initializeFrame = false
        clientInstance = Client() as net.runelite.api.Client
        clientInstance.callbacks = Hooks
        clientInstance.`initApplication$api`(789, 532, 1)
    }

    fun updateGameImage() {
        try {
            image.value = Bitmap.createBitmap(Client.drawArea.image.getPixels(), Client.drawArea.image.width, Client.drawArea.image.height, Bitmap.Config.RGB_565).asImageBitmap()
            recentDraws += System.currentTimeMillis()
            val expiredTimes = ArrayList<Long>()
            for (renderTime in recentDraws) {
                if (renderTime < (System.currentTimeMillis() - 1000))
                    expiredTimes += renderTime
            }
            for (expiredTime in expiredTimes)
                recentDraws.remove(expiredTime)
            fps.intValue = recentDraws.size

        } catch (_: Exception) {}
    }
}

