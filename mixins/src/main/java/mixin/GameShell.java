package mixin;

import net.runelite.api.Callbacks;
import net.runelite.api.events.DrawFinished;
import net.runelite.api.events.ErrorGame;
import net.runelite.api.mixins.Inject;
import net.runelite.api.mixins.MethodHook;
import net.runelite.api.mixins.Mixin;
import net.runelite.api.mixins.Shadow;
import net.runelite.rs.api.RSClient;
import net.runelite.rs.api.RSGameShell;
import net.runelite.rs.api.RSJavaKeyboardProvider;
import net.runelite.rs.api.RSJavaMouseProvider;

import java.awt.event.KeyEvent;

@SuppressWarnings("ALL")
@Mixin(RSGameShell.class)
abstract class GameShell implements RSGameShell {
    @Shadow("client")
    public static RSClient client;

    @Inject
    @MethodHook("error")
    public void error$tail(String type) {
        client.getCallbacks().post(new ErrorGame(type));
    }
}
