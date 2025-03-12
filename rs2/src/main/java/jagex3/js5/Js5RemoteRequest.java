package jagex3.js5;

import deob.ObfuscatedName;
import jagex3.datastruct.DoublyLinkable;

@ObfuscatedName("fl")
public class Js5RemoteRequest extends DoublyLinkable {

	@ObfuscatedName("fl.n")
	public Js5Local provider;

	@ObfuscatedName("fl.j")
	public int expectedCrc;

	@ObfuscatedName("fl.z")
	public byte padding;
}
