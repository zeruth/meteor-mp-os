package jagex3.client.applet;

import deob.ObfuscatedName;
import jagex3.datastruct.MonotonicTime;

import java.awt.*;
import java.awt.event.*;

@ObfuscatedName("an")
public class JavaMouseProvider implements MouseListener, MouseMotionListener, FocusListener {

	@ObfuscatedName("an.r")
	public static JavaMouseProvider mouseProvider = new JavaMouseProvider();

	@ObfuscatedName("an.d")
	public static volatile int idleCycles = 0;

	@ObfuscatedName("an.l")
	public static volatile int field487 = 0;

	@ObfuscatedName("an.m")
	public static volatile int field501 = -1;

	@ObfuscatedName("an.c")
	public static volatile int field493 = -1;

	@ObfuscatedName("an.n")
	public static int mouseButton = 0;

	@ObfuscatedName("an.j")
	public static int mouseX = 0;

	@ObfuscatedName("an.z")
	public static int mouseY = 0;

	@ObfuscatedName("an.g")
	public static volatile int field485 = 0;

	@ObfuscatedName("an.q")
	public static volatile int field494 = 0;

	@ObfuscatedName("an.i")
	public static volatile int field495 = 0;

	@ObfuscatedName("an.s")
	public static volatile long field496 = 0L;

	@ObfuscatedName("an.u")
	public static int mouseClickButton = 0;

	@ObfuscatedName("an.v")
	public static int mouseClickX = 0;

	@ObfuscatedName("an.w")
	public static int mouseClickY = 0;

	@ObfuscatedName("an.e")
	public static long mouseClickTime = 0L;

	@ObfuscatedName("v.r(Ljava/awt/Component;I)V")
	public static void addListeners(Component arg0) {
		arg0.addMouseListener(mouseProvider);
		arg0.addMouseMotionListener(mouseProvider);
		arg0.addFocusListener(mouseProvider);
	}

	@ObfuscatedName("ek.d(II)V")
	public static void setIdleCycles(int arg0) {
		idleCycles = arg0;
	}

	public static void imethod3() {
		JavaMouseProvider var8 = mouseProvider;
		synchronized (var8) {
			mouseButton = field487;
			mouseX = field501;
			mouseY = field493;
			mouseClickButton = field485;
			mouseClickX = field494;
			mouseClickY = field495;
			mouseClickTime = field496;
			field485 = 0;
		}
	}

	public final synchronized void mousePressed(MouseEvent arg0) {
		if (mouseProvider != null) {
			idleCycles = 0;
			field494 = arg0.getX();
			field495 = arg0.getY();
			field496 = MonotonicTime.currentTime();
			if (arg0.isMetaDown()) {
				field485 = 2;
				field487 = 2;
			} else {
				field485 = 1;
				field487 = 1;
			}
		}
		if (arg0.isPopupTrigger()) {
			arg0.consume();
		}
	}

	public final synchronized void mousePressed(int x, int y, boolean isMetaDown) {
		if (mouseProvider != null) {
			idleCycles = 0;
			field494 = x;
			field495 = y;
			field496 = MonotonicTime.currentTime();
			if (isMetaDown) {
				field485 = 2;
				field487 = 2;
			} else {
				field485 = 1;
				field487 = 1;
			}
		}
	}

	public final synchronized void mouseReleased(MouseEvent arg0) {
		if (mouseProvider != null) {
			idleCycles = 0;
			field487 = 0;
		}
		if (arg0.isPopupTrigger()) {
			arg0.consume();
		}
	}

	public final synchronized void mouseReleased() {
		if (mouseProvider != null) {
			idleCycles = 0;
			field487 = 0;
		}
	}

	public final void mouseClicked(MouseEvent arg0) {
		if (arg0.isPopupTrigger()) {
			arg0.consume();
		}
	}

	public final synchronized void mouseEntered(MouseEvent arg0) {
		if (mouseProvider != null) {
			idleCycles = 0;
			field501 = arg0.getX();
			field493 = arg0.getY();
		}
	}

	public final synchronized void mouseExited(MouseEvent arg0) {
		if (mouseProvider != null) {
			idleCycles = 0;
			field501 = -1;
			field493 = -1;
		}
	}

	public final synchronized void mouseDragged(MouseEvent arg0) {
		if (mouseProvider != null) {
			idleCycles = 0;
			field501 = arg0.getX();
			field493 = arg0.getY();
		}
	}

	public final synchronized void mouseMoved(MouseEvent arg0) {
		if (mouseProvider != null) {
			idleCycles = 0;
			field501 = arg0.getX();
			field493 = arg0.getY();
		}
	}

	public final synchronized void mouseMoved(int x, int y) {
		if (mouseProvider != null) {
			idleCycles = 0;
			field501 = x;
			field493 = y;
		}
	}

	public final void focusGained(FocusEvent arg0) {
	}

	public final synchronized void focusLost(FocusEvent arg0) {
		if (mouseProvider != null) {
			field487 = 0;
		}
	}

	public static void removeListeners(Canvas var2) {
		var2.removeMouseListener(mouseProvider);
		var2.removeMouseMotionListener(mouseProvider);
		var2.removeFocusListener(mouseProvider);
		field487 = 0;
	}

	public static void imethod2() {
		if (mouseProvider != null) {
			JavaMouseProvider var1 = mouseProvider;
			synchronized (var1) {
				mouseProvider = null;
			}
		}
	}

	public static int getIdleCycles() {
		return idleCycles++;
	}
}
