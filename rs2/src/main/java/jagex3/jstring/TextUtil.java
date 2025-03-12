package jagex3.jstring;

import deob.ObfuscatedName;

@ObfuscatedName("r")
public class TextUtil {

	@ObfuscatedName("r.r")
	public static String truthy = "true";

	@ObfuscatedName("r.d")
	public static String comma = ",";

	@ObfuscatedName("r.l")
	public static String pipe = "|";

	@ObfuscatedName("r.m")
	public static String openParen = " (";

	@ObfuscatedName("r.c")
	public static String closeParen = ")";

	@ObfuscatedName("r.n")
	public static String arrow = "->";

	@ObfuscatedName("r.j")
	public static String br = "<br>";

	@ObfuscatedName("r.z")
	public static String colEnd = "</col>";

	public TextUtil() throws Throwable {
		throw new Error();
	}

	@ObfuscatedName("j.r(IS)Ljava/lang/String;")
	public static String imgTag(int arg0) {
		return "<img=" + arg0 + ">";
	}

	@ObfuscatedName("i.d(II)Ljava/lang/String;")
	public static String colTag(int arg0) {
		return "<col=" + Integer.toHexString(arg0) + ">";
	}
}
