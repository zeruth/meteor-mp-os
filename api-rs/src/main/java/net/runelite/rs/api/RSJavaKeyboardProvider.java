package net.runelite.rs.api;

import net.runelite.api.PixMap;
import net.runelite.mapping.Import;

import java.awt.event.KeyEvent;

public interface RSJavaKeyboardProvider {

    @Import("keyPressed")
    void keyPressed$api(KeyEvent e);

    @Import("keyReleased")
    void keyReleased$api(KeyEvent e);

    @Import("keyPressed")
    void keyPressed$api(int i);

    @Import("keyReleased")
    void keyReleased$api(int i);

    @Import("keyTyped")
    void keyTyped$api(int i);
}
