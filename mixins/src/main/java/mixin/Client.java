package mixin;

import net.runelite.api.Callbacks;
import net.runelite.api.events.DrawFinished;
import net.runelite.api.mixins.*;
import net.runelite.rs.api.RSClient;
import net.runelite.rs.api.RSJavaKeyboardProvider;
import net.runelite.rs.api.RSJavaMouseProvider;

import java.awt.event.KeyEvent;

@SuppressWarnings("ALL")
@Mixin(RSClient.class)
abstract class Client implements RSClient {
    @Shadow("client")
    public static RSClient client;

    @Shadow("keyboardProvider")
    public static RSJavaKeyboardProvider keyboardProvider;

    @Shadow("mouseProvider")
    public static RSJavaMouseProvider mouseProvider;

    @Inject
    public Callbacks callbacks;

    @Inject
    @Override
    public Callbacks getCallbacks() {
        return callbacks;
    }

    @Inject
    @Override
    public void setCallbacks(Callbacks callbacks) {
        this.callbacks = callbacks;
    }

    @Inject
    @MethodHook("mainredraw")
    public void mainredraw$post() {
        callbacks.post(DrawFinished.INSTANCE);
    }

    @Inject
    @Override
    public void mouseMoved(int x, int y) {
        mouseProvider.mouseMoved$api(x, y);
    }

    @Inject
    @Override
    public void mousePressed(int x, int y, boolean isMetaDown) {
        mouseProvider.mousePressed$api(x, y, isMetaDown);
    }

    @Inject
    @Override
    public void mouseReleased() {
        mouseProvider.mouseReleased$api();
    }

    @Inject
    @Override
    public void keyPressed(KeyEvent key) {
        keyboardProvider.keyPressed$api(key);
    }

    @Inject
    @Override
    public void keyReleased(KeyEvent key) {
        keyboardProvider.keyReleased$api(key);
    }
}
