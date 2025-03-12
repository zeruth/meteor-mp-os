package jagex3.dash3d;

import deob.ObfuscatedName;
import jagex3.config.ObjType;

// jag::ObjStackEntity
@ObfuscatedName("fy")
public class ObjStackEntity extends Entity {

	@ObfuscatedName("fy.j")
	public int field2600;

	@ObfuscatedName("fy.z")
	public int field2601;

	@ObfuscatedName("fy.g(I)Lfo;")
	public final ModelUnlit getModel() {
		return ObjType.get(this.field2600).getModel(this.field2601);
	}
}
