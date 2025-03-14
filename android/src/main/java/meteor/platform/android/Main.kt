package meteor.platform.android

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import ext.android.ComponentActivityExt.setupActivity
import meteor.platform.common.Common
import meteor.platform.common.Common.eventbus
import meteor.platform.common.plugin.PluginManager
import meteor.platform.android.input.KeyboardController.keyboardController
import meteor.platform.android.events.BatteryLevelChanged
import meteor.platform.android.ui.Window.MeteorViewBox
import meteor.platform.android.ui.batteryfps.BatteryReceiver
import meteor.platform.common.Configuration
import java.awt.font.sfntly.FontPeer


class Main : ComponentActivity() {

    private val mainHandler = Handler(Looper.getMainLooper())
    var preventReplay = true

    private lateinit var batteryReceiver: BatteryReceiver

    companion object {
        fun onlyPlayJingles(): Boolean {
            return false
        }

        var lastSong: String? = null
        var muteLoginMusic = false
    }

    init {
/*        *//**
         * Subscribe to sounds here so we have access to context
         *//*
        KEVENT.subscribe<WavePlay> {
            Thread {
                preventReplay = true
                val bytes = it.data.soundStream.use {
                    val arr = ArrayList<Byte>()
                    while (it.available() > 0)
                        arr.add(it.read().toByte())
                    arr.toByteArray()
                }
                mainHandler.post {
                    Log.d("AUDIO", "PlaySound")
                    SoundPlayer.lastWaveBytes = bytes
                    SoundPlayer(bytes, 0, applicationContext).play()
                }
                preventReplay = false
            }.start()
        }
        KEVENT.subscribe<WaveReplay> {
            Thread {
                if (preventReplay) {
                    while (preventReplay) {
                        Thread.sleep(1)
                    }
                }
                mainHandler.post {
                    Log.d("AUDIO", "PlaySound")
                    SoundPlayer(SoundPlayer.lastWaveBytes, 0, applicationContext).play()
                }
            }.start()
        }
        KEVENT.subscribe<MidiPlay> {
            mainHandler.post {
                var prevSong = lastSong
                lastSong = it.data.name
                if (it.data.name == "scape_main") {
                    if (muteLoginMusic || (clientInstance.inGame() && onlyPlayJingles()) || prevSong == "scape_main") {
                        return@post
                    }
                }
                if (clientInstance.inGame()) {
                    if (!onlyPlayJingles()) {
                        songPlayer?.release()
                        songPlayer = SongPlayer(it.data.name, applicationContext)
                    }
                } else {
                    Log.d("AUDIO", "PlaySong")
                    songPlayer?.release()
                    songPlayer = SongPlayer(it.data.name, applicationContext)
                }
            }
        }
        KEVENT.subscribe<MidiStop> {
            mainHandler.post {
                songPlayer?.release()
            }
        }
        KEVENT.subscribe<MidiJinglePlay> {
            mainHandler.post {
                try {
                    songPlayer?.release()
                    jinglePlayer?.release()
                    jinglePlayer = JinglePlayer(it.data, applicationContext)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }*/
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FontPeer.androidContext = applicationContext
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        window.attributes.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;

        batteryReceiver = BatteryReceiver { batteryLevel ->
            eventbus.post(BatteryLevelChanged(batteryLevel))
        }

        loadMeteor()
        setContent {
            keyboardController = LocalSoftwareKeyboardController.current!!
            val focusRequester = remember { FocusRequester() }
            Box(modifier = Modifier
                .fillMaxSize()
                .focusRequester(focusRequester)) {
                MeteorViewBox()
            }
        }
    }

    override fun onStart() {
        super.onStart()

        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batteryReceiver, filter)
    }

    private fun loadMeteor() {
        setupActivity()
        setupMeteor()
        Game.start()
        PluginManager.start()
    }

    private fun setupMeteor() {
        Common.isAndroid = true
        Configuration.init(applicationContext)
    }
}

