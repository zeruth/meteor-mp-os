package net.runelite.rs.api;

import net.runelite.api.PixMap;
import net.runelite.mapping.Import;

import java.awt.event.KeyEvent;

public interface RSJavaMouseProvider {
    @Import("mouseReleased")
    void mouseReleased$api();

    @Import("mousePressed")
    void mousePressed$api(int x, int y, boolean isMetaDown);

    @Import("mouseMoved")
    void mouseMoved$api(int x, int y);
}
