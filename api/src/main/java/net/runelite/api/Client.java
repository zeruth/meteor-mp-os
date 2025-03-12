package net.runelite.api;

import java.awt.event.KeyEvent;

public interface Client extends GameShell {
    Callbacks getCallbacks();

    void setCallbacks(Callbacks callbacks);

    boolean isLoggedIn();

    String getUsername();

    String getPassword();

    void setPassword(String password);

    void setUsername(String username);

    boolean inGame();

    void mouseMoved(int x, int y);
    void mouseReleased();

    void mouseReleased$api(int i, boolean metaDown);
    void mousePressed(int x, int y, boolean isMetaDown);
    void keyPressed(KeyEvent e);
    void keyPressed$api(int i, int k);
    void keyReleased(KeyEvent e);
    void keyReleased$api(int i);
    void keyTyped$api(int i);

    int getSceneState();

    PixMap getAreaViewport();
}
