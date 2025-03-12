package jagex3.client.applet;

import deob.ObfuscatedName;
import jagex3.client.SignLink;
import jagex3.jstring.Cp1252;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@ObfuscatedName("az")
public class JavaKeyboardProvider implements KeyListener, FocusListener {

	@ObfuscatedName("az.r")
	public static JavaKeyboardProvider field460 = new JavaKeyboardProvider();

	@ObfuscatedName("az.cu")
	public static boolean[] actionKey = new boolean[112];

	@ObfuscatedName("ca.cc")
	public static char field1162;

	@ObfuscatedName("n.cm")
	public static int field114;

	@ObfuscatedName("az.cw")
	public static int[] field474 = new int[128];

	@ObfuscatedName("az.cz")
	public static int field445 = 0;

	@ObfuscatedName("az.cv")
	public static int field419 = 0;

	@ObfuscatedName("az.ct")
	public static char[] field477 = new char[128];

	@ObfuscatedName("az.ck")
	public static int[] field478 = new int[128];

	@ObfuscatedName("az.cy")
	public static int field479 = 0;

	@ObfuscatedName("az.cq")
	public static int field480 = 0;

	@ObfuscatedName("az.cd")
	public static int field424 = 0;

	@ObfuscatedName("az.cx")
	public static volatile int idleCycles = 0;

	@ObfuscatedName("az.cn")
	public static int[] field476 = new int[] { -1, -1, -1, -1, -1, -1, -1, -1, 85, 80, 84, -1, 91, -1, -1, -1, 81, 82, 86, -1, -1, -1, -1, -1, -1, -1, -1, 13, -1, -1, -1, -1, 83, 104, 105, 103, 102, 96, 98, 97, 99, -1, -1, -1, -1, -1, -1, -1, 25, 16, 17, 18, 19, 20, 21, 22, 23, 24, -1, -1, -1, -1, -1, -1, -1, 48, 68, 66, 50, 34, 51, 52, 53, 39, 54, 55, 56, 70, 69, 40, 41, 32, 35, 49, 36, 38, 67, 33, 65, 37, 64, -1, -1, -1, -1, -1, 228, 231, 227, 233, 224, 219, 225, 230, 226, 232, 89, 87, -1, 88, 229, 90, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, -1, -1, -1, 101, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 100, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };

	@ObfuscatedName("n.r(Ljava/awt/Component;I)V")
	public static void method53(Component arg0) {
		arg0.setFocusTraversalKeysEnabled(false);
		arg0.addKeyListener(field460);
		arg0.addFocusListener(field460);
	}

	@ObfuscatedName("cw.d(Ljava/awt/Component;B)V")
	public static void method1143(Component arg0) {
		arg0.removeKeyListener(field460);
		arg0.removeFocusListener(field460);
		field419 = -1;
	}

	@ObfuscatedName("dw.l(I)V")
	public static void method1502() {
		if (field460 != null) {
			JavaKeyboardProvider var0 = field460;
			synchronized (var0) {
				field460 = null;
			}
		}
	}

	public static void imethod4() {
		JavaKeyboardProvider var4 = field460;
		synchronized (var4) {
			idleCycles++;
			field479 = field424;
			if (field419 >= 0) {
				while (field445 != field419) {
					int var6 = field474[field445];
					field445 = field445 + 1 & 0x7F;
					if (var6 < 0) {
						actionKey[~var6] = false;
					} else {
						actionKey[var6] = true;
					}
				}
			} else {
				for (int var5 = 0; var5 < 112; var5++) {
					actionKey[var5] = false;
				}
				field419 = field445;
			}
			field424 = field480;
		}
	}

	public final synchronized void keyPressed(KeyEvent arg0) {
		if (field460 == null) {
			return;
		}
		idleCycles = 0;
		int var2 = arg0.getKeyCode();
		int var3;
		if (var2 >= 0 && var2 < field476.length) {
			var3 = field476[var2];
			if ((var3 & 0x80) != 0) {
				var3 = -1;
			}
		} else {
			var3 = -1;
		}
		if (field419 >= 0 && var3 >= 0) {
			field474[field419] = var3;
			field419 = field419 + 1 & 0x7F;
			if (field445 == field419) {
				field419 = -1;
			}
		}
		if (var3 >= 0) {
			int var4 = field480 + 1 & 0x7F;
			if (field479 != var4) {
				field478[field480] = var3;
				field477[field480] = 0;
				field480 = var4;
			}
		}
		int var5 = arg0.getModifiers();
		if ((var5 & 0xA) != 0 || var3 == 85 || var3 == 10) {
			arg0.consume();
		}
	}

	public final synchronized void keyReleased(KeyEvent arg0) {
		if (field460 != null) {
			idleCycles = 0;
			int var2 = arg0.getKeyCode();
			int var3;
			if (var2 >= 0 && var2 < field476.length) {
				var3 = field476[var2] & 0xFFFFFF7F;
			} else {
				var3 = -1;
			}
			if (field419 >= 0 && var3 >= 0) {
				field474[field419] = ~var3;
				field419 = field419 + 1 & 0x7F;
				if (field445 == field419) {
					field419 = -1;
				}
			}
		}
		arg0.consume();
	}

	public final void keyTyped(KeyEvent arg0) {
		if (field460 != null) {
			char var2 = arg0.getKeyChar();
			if (var2 != 0 && var2 != 65535 && Cp1252.method767(var2)) {
				int var3 = field480 + 1 & 0x7F;
				if (field479 != var3) {
					field478[field480] = -1;
					field477[field480] = var2;
					field480 = var3;
				}
			}
		}
		arg0.consume();
	}

	public final void focusGained(FocusEvent arg0) {
	}

	public final synchronized void focusLost(FocusEvent arg0) {
		if (field460 != null) {
			field419 = -1;
		}
	}

	public static void imethod1() {
		if (SignLink.javaVendor.toLowerCase().indexOf("microsoft") == -1) {
			field476[44] = 71;
			field476[45] = 26;
			field476[46] = 72;
			field476[47] = 73;
			field476[59] = 57;
			field476[61] = 27;
			field476[91] = 42;
			field476[92] = 74;
			field476[93] = 43;
			field476[192] = 28;
			field476[222] = 58;
			field476[520] = 59;
		} else {
			field476[186] = 57;
			field476[187] = 27;
			field476[188] = 71;
			field476[189] = 26;
			field476[190] = 72;
			field476[191] = 73;
			field476[192] = 58;
			field476[219] = 42;
			field476[220] = 74;
			field476[221] = 43;
			field476[222] = 59;
			field476[223] = 28;
		}
	}

	public static boolean imethod2() {
		JavaKeyboardProvider var446 = field460;
		synchronized (var446) {
			if (field479 == field424) {
				return false;
			} else {
				field114 = field478[field479];
				field1162 = field477[field479];
				field479 = field479 + 1 & 0x7F;
				return true;
			}
		}
	}

	public static int imethod3() {
		// todo: 468 incremented this, osrs 1 does not? idle timer has also changed
		return idleCycles;
	}
}
