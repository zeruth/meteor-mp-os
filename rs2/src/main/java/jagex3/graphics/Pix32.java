package jagex3.graphics;

import deob.ObfuscatedName;

import java.awt.*;
import java.awt.image.PixelGrabber;

// jag::oldscape::graphics::Pix32
@ObfuscatedName("fq")
public class Pix32 extends Pix2D {

	@ObfuscatedName("fq.u")
	public int[] field2506;

	@ObfuscatedName("fq.v")
	public int field2508;

	@ObfuscatedName("fq.w")
	public int field2501;

	@ObfuscatedName("fq.e")
	public int field2502;

	@ObfuscatedName("fq.b")
	public int field2499;

	@ObfuscatedName("fq.y")
	public int field2504;

	@ObfuscatedName("fq.t")
	public int field2505;

	public Pix32() {
	}

	public Pix32(int arg0, int arg1) {
		this.field2506 = new int[arg0 * arg1];
		this.field2508 = this.field2504 = arg0;
		this.field2501 = this.field2505 = arg1;
		this.field2499 = 0;
		this.field2502 = 0;
	}

	public Pix32(byte[] arg0, Component arg1) {
		try {
			Image var3 = Toolkit.getDefaultToolkit().createImage(arg0);
			MediaTracker var4 = new MediaTracker(arg1);
			var4.addImage(var3, 0);
			var4.waitForAll();
			this.field2508 = var3.getWidth(arg1);
			this.field2501 = var3.getHeight(arg1);
			this.field2504 = this.field2508;
			this.field2505 = this.field2501;
			this.field2502 = 0;
			this.field2499 = 0;
			this.field2506 = new int[this.field2508 * this.field2501];
			PixelGrabber var5 = new PixelGrabber(var3, 0, 0, this.field2508, this.field2501, this.field2506, 0, this.field2508);
			var5.grabPixels();
		} catch (InterruptedException var7) {
		}
	}

	@ObfuscatedName("fq.bm()Lfq;")
	public Pix32 method2719() {
		Pix32 var1 = new Pix32(this.field2508, this.field2501);
		var1.field2504 = this.field2504;
		var1.field2505 = this.field2505;
		var1.field2502 = this.field2504 - this.field2508 - this.field2502;
		var1.field2499 = this.field2499;
		for (int var2 = 0; var2 < this.field2501; var2++) {
			for (int var3 = 0; var3 < this.field2508; var3++) {
				var1.field2506[this.field2508 * var2 + var3] = this.field2506[this.field2508 * var2 + this.field2508 - 1 - var3];
			}
		}
		return var1;
	}

	@ObfuscatedName("fq.bn()V")
	public void method2662() {
		bind(this.field2506, this.field2508, this.field2501);
	}

	@ObfuscatedName("fq.be(III)V")
	public void method2663(int arg0, int arg1, int arg2) {
		for (int var4 = 0; var4 < this.field2506.length; var4++) {
			int var5 = this.field2506[var4];
			if (var5 != 0) {
				int var6 = var5 >> 16 & 0xFF;
				int var7 = arg0 + var6;
				if (var7 < 1) {
					var7 = 1;
				} else if (var7 > 255) {
					var7 = 255;
				}
				int var8 = var5 >> 8 & 0xFF;
				int var9 = arg1 + var8;
				if (var9 < 1) {
					var9 = 1;
				} else if (var9 > 255) {
					var9 = 255;
				}
				int var10 = var5 & 0xFF;
				int var11 = arg2 + var10;
				if (var11 < 1) {
					var11 = 1;
				} else if (var11 > 255) {
					var11 = 255;
				}
				this.field2506[var4] = (var7 << 16) + (var9 << 8) + var11;
			}
		}
	}

	@ObfuscatedName("fq.bp()V")
	public void method2741() {
		if (this.field2508 == this.field2504 && this.field2505 == this.field2501) {
			return;
		}
		int[] var1 = new int[this.field2505 * this.field2504];
		for (int var2 = 0; var2 < this.field2501; var2++) {
			for (int var3 = 0; var3 < this.field2508; var3++) {
				var1[(this.field2499 + var2) * this.field2504 + this.field2502 + var3] = this.field2506[this.field2508 * var2 + var3];
			}
		}
		this.field2506 = var1;
		this.field2508 = this.field2504;
		this.field2501 = this.field2505;
		this.field2502 = 0;
		this.field2499 = 0;
	}

	@ObfuscatedName("fq.ba(I)V")
	public void method2725(int arg0) {
		if (this.field2508 == this.field2504 && this.field2505 == this.field2501) {
			return;
		}
		int var2 = arg0;
		if (arg0 > this.field2502) {
			var2 = this.field2502;
		}
		int var3 = arg0;
		if (this.field2502 + arg0 + this.field2508 > this.field2504) {
			var3 = this.field2504 - this.field2502 - this.field2508;
		}
		int var4 = arg0;
		if (arg0 > this.field2499) {
			var4 = this.field2499;
		}
		int var5 = arg0;
		if (this.field2499 + arg0 + this.field2501 > this.field2505) {
			var5 = this.field2505 - this.field2499 - this.field2501;
		}
		int var6 = this.field2508 + var2 + var3;
		int var7 = this.field2501 + var4 + var5;
		int[] var8 = new int[var6 * var7];
		for (int var9 = 0; var9 < this.field2501; var9++) {
			for (int var10 = 0; var10 < this.field2508; var10++) {
				var8[(var4 + var9) * var6 + var2 + var10] = this.field2506[this.field2508 * var9 + var10];
			}
		}
		this.field2506 = var8;
		this.field2508 = var6;
		this.field2501 = var7;
		this.field2502 -= var2;
		this.field2499 -= var4;
	}

	@ObfuscatedName("fq.bc()V")
	public void method2666() {
		int[] var1 = new int[this.field2508 * this.field2501];
		int var2 = 0;
		for (int var3 = 0; var3 < this.field2501; var3++) {
			for (int var4 = this.field2508 - 1; var4 >= 0; var4--) {
				var1[var2++] = this.field2506[this.field2508 * var3 + var4];
			}
		}
		this.field2506 = var1;
		this.field2502 = this.field2504 - this.field2508 - this.field2502;
	}

	@ObfuscatedName("fq.br()V")
	public void method2711() {
		int[] var1 = new int[this.field2508 * this.field2501];
		int var2 = 0;
		for (int var3 = this.field2501 - 1; var3 >= 0; var3--) {
			for (int var4 = 0; var4 < this.field2508; var4++) {
				var1[var2++] = this.field2506[this.field2508 * var3 + var4];
			}
		}
		this.field2506 = var1;
		this.field2499 = this.field2505 - this.field2501 - this.field2499;
	}

	@ObfuscatedName("fq.bb(I)V")
	public void method2714(int arg0) {
		int[] var2 = new int[this.field2508 * this.field2501];
		int var3 = 0;
		for (int var4 = 0; var4 < this.field2501; var4++) {
			for (int var5 = 0; var5 < this.field2508; var5++) {
				int var6 = this.field2506[var3];
				if (var6 == 0) {
					if (var5 > 0 && this.field2506[var3 - 1] != 0) {
						var6 = arg0;
					} else if (var4 > 0 && this.field2506[var3 - this.field2508] != 0) {
						var6 = arg0;
					} else if (var5 < this.field2508 - 1 && this.field2506[var3 + 1] != 0) {
						var6 = arg0;
					} else if (var4 < this.field2501 - 1 && this.field2506[this.field2508 + var3] != 0) {
						var6 = arg0;
					}
				}
				var2[var3++] = var6;
			}
		}
		this.field2506 = var2;
	}

	@ObfuscatedName("fq.bd(I)V")
	public void method2669(int arg0) {
		for (int var2 = this.field2501 - 1; var2 > 0; var2--) {
			int var3 = this.field2508 * var2;
			for (int var4 = this.field2508 - 1; var4 > 0; var4--) {
				if (this.field2506[var3 + var4] == 0 && this.field2506[var3 + var4 - 1 - this.field2508] != 0) {
					this.field2506[var3 + var4] = arg0;
				}
			}
		}
	}

	@ObfuscatedName("fq.cr(II)V")
	public void method2667(int arg0, int arg1) {
		int var3 = this.field2502 + arg0;
		int var4 = this.field2499 + arg1;
		int var5 = Pix2D.width2d * var4 + var3;
		int var6 = 0;
		int var7 = this.field2501;
		int var8 = this.field2508;
		int var9 = Pix2D.width2d - var8;
		int var10 = 0;
		if (var4 < top) {
			int var11 = top - var4;
			var7 -= var11;
			var4 = top;
			var6 += var8 * var11;
			var5 += Pix2D.width2d * var11;
		}
		if (var4 + var7 > bottom) {
			var7 -= var4 + var7 - bottom;
		}
		if (var3 < left) {
			int var12 = left - var3;
			var8 -= var12;
			var3 = left;
			var6 += var12;
			var5 += var12;
			var10 += var12;
			var9 += var12;
		}
		if (var3 + var8 > right) {
			int var13 = var3 + var8 - right;
			var8 -= var13;
			var10 += var13;
			var9 += var13;
		}
		if (var8 > 0 && var7 > 0) {
			method2670(Pix2D.data, this.field2506, var6, var5, var8, var7, var9, var10);
		}
	}

	@ObfuscatedName("fq.cs([I[IIIIIII)V")
	public static void method2670(int[] arg0, int[] arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7) {
		for (int var8 = -arg5; var8 < 0; var8++) {
			int var9 = arg3 + arg4 - 3;
			while (arg3 < var9) {
				arg0[arg3++] = arg1[arg2++];
				arg0[arg3++] = arg1[arg2++];
				arg0[arg3++] = arg1[arg2++];
				arg0[arg3++] = arg1[arg2++];
			}
			var9 += 3;
			while (arg3 < var9) {
				arg0[arg3++] = arg1[arg2++];
			}
			arg3 += arg6;
			arg2 += arg7;
		}
	}

	@ObfuscatedName("fq.cj(II)V")
	public void draw(int arg0, int arg1) {
		int var3 = this.field2502 + arg0;
		int var4 = this.field2499 + arg1;
		int var5 = Pix2D.width2d * var4 + var3;
		int var6 = 0;
		int var7 = this.field2501;
		int var8 = this.field2508;
		int var9 = Pix2D.width2d - var8;
		int var10 = 0;
		if (var4 < top) {
			int var11 = top - var4;
			var7 -= var11;
			var4 = top;
			var6 += var8 * var11;
			var5 += Pix2D.width2d * var11;
		}
		if (var4 + var7 > bottom) {
			var7 -= var4 + var7 - bottom;
		}
		if (var3 < left) {
			int var12 = left - var3;
			var8 -= var12;
			var3 = left;
			var6 += var12;
			var5 += var12;
			var10 += var12;
			var9 += var12;
		}
		if (var3 + var8 > right) {
			int var13 = var3 + var8 - right;
			var8 -= var13;
			var10 += var13;
			var9 += var13;
		}
		if (var8 > 0 && var7 > 0) {
			method2732(Pix2D.data, this.field2506, 0, var6, var5, var8, var7, var9, var10);
		}
	}

	@ObfuscatedName("fq.cl([I[IIIIIIII)V")
	public static void method2732(int[] arg0, int[] arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8) {
		int var9 = -(arg5 >> 2);
		int var10 = -(arg5 & 0x3);
		for (int var11 = -arg6; var11 < 0; var11++) {
			for (int var12 = var9; var12 < 0; var12++) {
				int var13 = arg1[arg3++];
				if (var13 == 0) {
					arg4++;
				} else {
					arg0[arg4++] = var13;
				}
				int var14 = arg1[arg3++];
				if (var14 == 0) {
					arg4++;
				} else {
					arg0[arg4++] = var14;
				}
				int var15 = arg1[arg3++];
				if (var15 == 0) {
					arg4++;
				} else {
					arg0[arg4++] = var15;
				}
				int var16 = arg1[arg3++];
				if (var16 == 0) {
					arg4++;
				} else {
					arg0[arg4++] = var16;
				}
			}
			for (int var17 = var10; var17 < 0; var17++) {
				int var18 = arg1[arg3++];
				if (var18 == 0) {
					arg4++;
				} else {
					arg0[arg4++] = var18;
				}
			}
			arg4 += arg7;
			arg3 += arg8;
		}
	}

	@ObfuscatedName("fq.cp(IIII)V")
	public void method2664(int arg0, int arg1, int arg2, int arg3) {
		if (arg2 <= 0 || arg3 <= 0) {
			return;
		}
		int var5 = this.field2508;
		int var6 = this.field2501;
		int var7 = 0;
		int var8 = 0;
		int var9 = this.field2504;
		int var10 = this.field2505;
		int var11 = (var9 << 16) / arg2;
		int var12 = (var10 << 16) / arg3;
		if (this.field2502 > 0) {
			int var13 = ((this.field2502 << 16) + var11 - 1) / var11;
			arg0 += var13;
			var7 += var11 * var13 - (this.field2502 << 16);
		}
		if (this.field2499 > 0) {
			int var14 = ((this.field2499 << 16) + var12 - 1) / var12;
			arg1 += var14;
			var8 += var12 * var14 - (this.field2499 << 16);
		}
		if (var5 < var9) {
			arg2 = ((var5 << 16) - var7 + var11 - 1) / var11;
		}
		if (var6 < var10) {
			arg3 = ((var6 << 16) - var8 + var12 - 1) / var12;
		}
		int var15 = Pix2D.width2d * arg1 + arg0;
		int var16 = Pix2D.width2d - arg2;
		if (arg1 + arg3 > bottom) {
			arg3 -= arg1 + arg3 - bottom;
		}
		if (arg1 < top) {
			int var17 = top - arg1;
			arg3 -= var17;
			var15 += Pix2D.width2d * var17;
			var8 += var12 * var17;
		}
		if (arg0 + arg2 > right) {
			int var18 = arg0 + arg2 - right;
			arg2 -= var18;
			var16 += var18;
		}
		if (arg0 < left) {
			int var19 = left - arg0;
			arg2 -= var19;
			var15 += var19;
			var7 += var11 * var19;
			var16 += var19;
		}
		method2674(Pix2D.data, this.field2506, 0, var7, var8, var15, var16, arg2, arg3, var11, var12, var5);
	}

	@ObfuscatedName("fq.ca([I[IIIIIIIIIII)V")
	public static void method2674(int[] arg0, int[] arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9, int arg10, int arg11) {
		int var12 = arg3;
		for (int var13 = -arg8; var13 < 0; var13++) {
			int var14 = (arg4 >> 16) * arg11;
			for (int var15 = -arg7; var15 < 0; var15++) {
				int var16 = arg1[(arg3 >> 16) + var14];
				if (var16 == 0) {
					arg5++;
				} else {
					arg0[arg5++] = var16;
				}
				arg3 += arg9;
			}
			arg4 += arg10;
			arg3 = var12;
			arg5 += arg6;
		}
	}

	@ObfuscatedName("fq.co(IIII)V")
	public void method2699(int arg0, int arg1, int arg2, int arg3) {
		if (arg2 == 256) {
			this.draw(arg0, arg1);
			return;
		}
		int var5 = this.field2502 + arg0;
		int var6 = this.field2499 + arg1;
		int var7 = Pix2D.width2d * var6 + var5;
		int var8 = 0;
		int var9 = this.field2501;
		int var10 = this.field2508;
		int var11 = Pix2D.width2d - var10;
		int var12 = 0;
		if (var6 < top) {
			int var13 = top - var6;
			var9 -= var13;
			var6 = top;
			var8 += var10 * var13;
			var7 += Pix2D.width2d * var13;
		}
		if (var6 + var9 > bottom) {
			var9 -= var6 + var9 - bottom;
		}
		if (var5 < left) {
			int var14 = left - var5;
			var10 -= var14;
			var5 = left;
			var8 += var14;
			var7 += var14;
			var12 += var14;
			var11 += var14;
		}
		if (var5 + var10 > right) {
			int var15 = var5 + var10 - right;
			var10 -= var15;
			var12 += var15;
			var11 += var15;
		}
		if (var10 > 0 && var9 > 0) {
			method2675(Pix2D.data, this.field2506, 0, var8, var7, var10, var9, var11, var12, arg2, arg3);
		}
	}

	@ObfuscatedName("fq.ch([I[IIIIIIIIII)V")
	public static void method2675(int[] arg0, int[] arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9, int arg10) {
		int var11 = 256 - arg9;
		int var12 = (arg10 & 0xFF00FF) * var11 & 0xFF00FF00;
		int var13 = (arg10 & 0xFF00) * var11 & 0xFF0000;
		int var14 = (var12 | var13) >>> 8;
		for (int var15 = -arg6; var15 < 0; var15++) {
			for (int var16 = -arg5; var16 < 0; var16++) {
				int var17 = arg1[arg3++];
				if (var17 == 0) {
					arg4++;
				} else {
					int var18 = (var17 & 0xFF00FF) * arg9 & 0xFF00FF00;
					int var19 = (var17 & 0xFF00) * arg9 & 0xFF0000;
					arg0[arg4++] = ((var18 | var19) >>> 8) + var14;
				}
			}
			arg4 += arg7;
			arg3 += arg8;
		}
	}

	@ObfuscatedName("fq.cu(III)V")
	public void method2676(int arg0, int arg1, int arg2) {
		int var4 = this.field2502 + arg0;
		int var5 = this.field2499 + arg1;
		int var6 = Pix2D.width2d * var5 + var4;
		int var7 = 0;
		int var8 = this.field2501;
		int var9 = this.field2508;
		int var10 = Pix2D.width2d - var9;
		int var11 = 0;
		if (var5 < top) {
			int var12 = top - var5;
			var8 -= var12;
			var5 = top;
			var7 += var9 * var12;
			var6 += Pix2D.width2d * var12;
		}
		if (var5 + var8 > bottom) {
			var8 -= var5 + var8 - bottom;
		}
		if (var4 < left) {
			int var13 = left - var4;
			var9 -= var13;
			var4 = left;
			var7 += var13;
			var6 += var13;
			var11 += var13;
			var10 += var13;
		}
		if (var4 + var9 > right) {
			int var14 = var4 + var9 - right;
			var9 -= var14;
			var11 += var14;
			var10 += var14;
		}
		if (var9 > 0 && var8 > 0) {
			method2677(Pix2D.data, this.field2506, 0, var7, var6, var9, var8, var10, var11, arg2);
		}
	}

	@ObfuscatedName("fq.cc([I[IIIIIIIII)V")
	public static void method2677(int[] arg0, int[] arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9) {
		int var10 = 256 - arg9;
		for (int var11 = -arg6; var11 < 0; var11++) {
			for (int var12 = -arg5; var12 < 0; var12++) {
				int var13 = arg1[arg3++];
				if (var13 == 0) {
					arg4++;
				} else {
					int var14 = arg0[arg4];
					arg0[arg4++] = ((var13 & 0xFF00FF) * arg9 + (var14 & 0xFF00FF) * var10 & 0xFF00FF00) + ((var13 & 0xFF00) * arg9 + (var14 & 0xFF00) * var10 & 0xFF0000) >> 8;
				}
			}
			arg4 += arg7;
			arg3 += arg8;
		}
	}

	@ObfuscatedName("fq.cm(IIIII)V")
	public void method2678(int arg0, int arg1, int arg2, int arg3, int arg4) {
		if (arg2 <= 0 || arg3 <= 0) {
			return;
		}
		int var6 = this.field2508;
		int var7 = this.field2501;
		int var8 = 0;
		int var9 = 0;
		int var10 = this.field2504;
		int var11 = this.field2505;
		int var12 = (var10 << 16) / arg2;
		int var13 = (var11 << 16) / arg3;
		if (this.field2502 > 0) {
			int var14 = ((this.field2502 << 16) + var12 - 1) / var12;
			arg0 += var14;
			var8 += var12 * var14 - (this.field2502 << 16);
		}
		if (this.field2499 > 0) {
			int var15 = ((this.field2499 << 16) + var13 - 1) / var13;
			arg1 += var15;
			var9 += var13 * var15 - (this.field2499 << 16);
		}
		if (var6 < var10) {
			arg2 = ((var6 << 16) - var8 + var12 - 1) / var12;
		}
		if (var7 < var11) {
			arg3 = ((var7 << 16) - var9 + var13 - 1) / var13;
		}
		int var16 = Pix2D.width2d * arg1 + arg0;
		int var17 = Pix2D.width2d - arg2;
		if (arg1 + arg3 > bottom) {
			arg3 -= arg1 + arg3 - bottom;
		}
		if (arg1 < top) {
			int var18 = top - arg1;
			arg3 -= var18;
			var16 += Pix2D.width2d * var18;
			var9 += var13 * var18;
		}
		if (arg0 + arg2 > right) {
			int var19 = arg0 + arg2 - right;
			arg2 -= var19;
			var17 += var19;
		}
		if (arg0 < left) {
			int var20 = left - arg0;
			arg2 -= var20;
			var16 += var20;
			var8 += var12 * var20;
			var17 += var20;
		}
		method2679(Pix2D.data, this.field2506, 0, var8, var9, var16, var17, arg2, arg3, var12, var13, var6, arg4);
	}

	@ObfuscatedName("fq.cw([I[IIIIIIIIIIII)V")
	public static void method2679(int[] arg0, int[] arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9, int arg10, int arg11, int arg12) {
		int var13 = 256 - arg12;
		int var14 = arg3;
		for (int var15 = -arg8; var15 < 0; var15++) {
			int var16 = (arg4 >> 16) * arg11;
			for (int var17 = -arg7; var17 < 0; var17++) {
				int var18 = arg1[(arg3 >> 16) + var16];
				if (var18 == 0) {
					arg5++;
				} else {
					int var19 = arg0[arg5];
					arg0[arg5++] = ((var18 & 0xFF00FF) * arg12 + (var19 & 0xFF00FF) * var13 & 0xFF00FF00) + ((var18 & 0xFF00) * arg12 + (var19 & 0xFF00) * var13 & 0xFF0000) >> 8;
				}
				arg3 += arg9;
			}
			arg4 += arg10;
			arg3 = var14;
			arg5 += arg6;
		}
	}

	@ObfuscatedName("fq.cz(IIIIIIII[I[I)V")
	public void method2680(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int[] arg8, int[] arg9) {
		try {
			int var11 = -arg2 / 2;
			int var12 = -arg3 / 2;
			int var13 = (int) (Math.sin((double) arg6 / 326.11D) * 65536.0D);
			int var14 = (int) (Math.cos((double) arg6 / 326.11D) * 65536.0D);
			int var15 = arg7 * var13 >> 8;
			int var16 = arg7 * var14 >> 8;
			int var17 = (arg4 << 16) + var11 * var16 + var12 * var15;
			int var18 = (arg5 << 16) + (var12 * var16 - var11 * var15);
			int var19 = Pix2D.width2d * arg1 + arg0;
			for (int var20 = 0; var20 < arg3; var20++) {
				int var21 = arg8[var20];
				int var22 = var19 + var21;
				int var23 = var16 * var21 + var17;
				int var24 = var18 - var15 * var21;
				for (int var25 = -arg9[var20]; var25 < 0; var25++) {
					Pix2D.data[var22++] = this.field2506[(var23 >> 16) + (var24 >> 16) * this.field2508];
					var23 += var16;
					var24 -= var15;
				}
				var17 += var15;
				var18 += var16;
				var19 += Pix2D.width2d;
			}
		} catch (Exception var27) {
		}
	}

	@ObfuscatedName("fq.cv(IIIIIIDI)V")
	public void method2665(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, double arg6, int arg7) {
		try {
			int var10 = -arg2 / 2;
			int var11 = -arg3 / 2;
			int var12 = (int) (Math.sin(arg6) * 65536.0D);
			int var13 = (int) (Math.cos(arg6) * 65536.0D);
			int var14 = arg7 * var12 >> 8;
			int var15 = arg7 * var13 >> 8;
			int var16 = (arg4 << 16) + var10 * var15 + var11 * var14;
			int var17 = (arg5 << 16) + (var11 * var15 - var10 * var14);
			int var18 = Pix2D.width2d * arg1 + arg0;
			for (int var19 = 0; var19 < arg3; var19++) {
				int var20 = var18;
				int var21 = var16;
				int var22 = var17;
				for (int var23 = -arg2; var23 < 0; var23++) {
					int var24 = this.field2506[(var21 >> 16) + (var22 >> 16) * this.field2508];
					if (var24 == 0) {
						var20++;
					} else {
						Pix2D.data[var20++] = var24;
					}
					var21 += var15;
					var22 -= var14;
				}
				var16 += var14;
				var17 += var15;
				var18 += Pix2D.width2d;
			}
		} catch (Exception var26) {
		}
	}

	@ObfuscatedName("fq.ct(IIII)V")
	public void method2685(int arg0, int arg1, int arg2, int arg3) {
		this.method2683(this.field2504 << 3, this.field2505 << 3, arg0 << 4, arg1 << 4, arg2, arg3);
	}

	@ObfuscatedName("fq.ck(IIIIII)V")
	public void method2683(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		if (arg5 == 0) {
			return;
		}
		int var7 = arg0 - (this.field2502 << 4);
		int var8 = arg1 - (this.field2499 << 4);
		double var9 = (double) (arg4 & 0xFFFF) * 9.587379924285257E-5D;
		int var11 = (int) Math.floor(Math.sin(var9) * (double) arg5 + 0.5D);
		int var12 = (int) Math.floor(Math.cos(var9) * (double) arg5 + 0.5D);
		int var13 = -var7 * var12 + -var8 * var11;
		int var14 = var7 * var11 + -var8 * var12;
		int var15 = ((this.field2508 << 4) - var7) * var12 + -var8 * var11;
		int var16 = -((this.field2508 << 4) - var7) * var11 + -var8 * var12;
		int var17 = ((this.field2501 << 4) - var8) * var11 + -var7 * var12;
		int var18 = ((this.field2501 << 4) - var8) * var12 + var7 * var11;
		int var19 = ((this.field2508 << 4) - var7) * var12 + ((this.field2501 << 4) - var8) * var11;
		int var20 = ((this.field2501 << 4) - var8) * var12 + -((this.field2508 << 4) - var7) * var11;
		int var21;
		int var22;
		if (var13 < var15) {
			var21 = var13;
			var22 = var15;
		} else {
			var21 = var15;
			var22 = var13;
		}
		if (var17 < var21) {
			var21 = var17;
		}
		if (var19 < var21) {
			var21 = var19;
		}
		if (var17 > var22) {
			var22 = var17;
		}
		if (var19 > var22) {
			var22 = var19;
		}
		int var23;
		int var24;
		if (var14 < var16) {
			var23 = var14;
			var24 = var16;
		} else {
			var23 = var16;
			var24 = var14;
		}
		if (var18 < var23) {
			var23 = var18;
		}
		if (var20 < var23) {
			var23 = var20;
		}
		if (var18 > var24) {
			var24 = var18;
		}
		if (var20 > var24) {
			var24 = var20;
		}
		int var25 = var21 >> 12;
		int var26 = var22 + 4095 >> 12;
		int var27 = var23 >> 12;
		int var28 = var24 + 4095 >> 12;
		int var29 = arg2 + var25;
		int var30 = arg2 + var26;
		int var31 = arg3 + var27;
		int var32 = arg3 + var28;
		int var33 = var29 >> 4;
		int var34 = var30 + 15 >> 4;
		int var35 = var31 >> 4;
		int var36 = var32 + 15 >> 4;
		if (var33 < left) {
			var33 = left;
		}
		if (var34 > right) {
			var34 = right;
		}
		if (var35 < top) {
			var35 = top;
		}
		if (var36 > bottom) {
			var36 = bottom;
		}
		int var37 = var33 - var34;
		if (var37 >= 0) {
			return;
		}
		int var38 = var35 - var36;
		if (var38 >= 0) {
			return;
		}
		int var39 = Pix2D.width2d * var35 + var33;
		double var40 = 1.6777216E7D / (double) arg5;
		int var42 = (int) Math.floor(Math.sin(var9) * var40 + 0.5D);
		int var43 = (int) Math.floor(Math.cos(var9) * var40 + 0.5D);
		int var44 = (var33 << 4) + 8 - arg2;
		int var45 = (var35 << 4) + 8 - arg3;
		int var46 = (var7 << 8) - (var42 * var45 >> 4);
		int var47 = (var8 << 8) + (var43 * var45 >> 4);
		if (var43 == 0) {
			if (var42 == 0) {
				int var48 = var38;
				while (var48 < 0) {
					int var49 = var39;
					int var50 = var46;
					int var51 = var47;
					int var52 = var37;
					if (var46 >= 0 && var47 >= 0 && var46 - (this.field2508 << 12) < 0 && var47 - (this.field2501 << 12) < 0) {
						while (var52 < 0) {
							int var53 = this.field2506[(var50 >> 12) + (var51 >> 12) * this.field2508];
							if (var53 == 0) {
								var49++;
							} else {
								Pix2D.data[var49++] = var53;
							}
							var52++;
						}
					}
					var48++;
					var39 += Pix2D.width2d;
				}
			} else if (var42 < 0) {
				int var54 = var38;
				while (var54 < 0) {
					int var55 = var39;
					int var56 = var46;
					int var57 = (var42 * var44 >> 4) + var47;
					int var58 = var37;
					if (var46 >= 0 && var46 - (this.field2508 << 12) < 0) {
						int var59;
						if ((var59 = var57 - (this.field2501 << 12)) >= 0) {
							int var60 = (var42 - var59) / var42;
							var58 = var37 + var60;
							var57 += var42 * var60;
							var55 = var39 + var60;
						}
						int var61;
						if ((var61 = (var57 - var42) / var42) > var58) {
							var58 = var61;
						}
						while (var58 < 0) {
							int var62 = this.field2506[(var56 >> 12) + (var57 >> 12) * this.field2508];
							if (var62 == 0) {
								var55++;
							} else {
								Pix2D.data[var55++] = var62;
							}
							var57 += var42;
							var58++;
						}
					}
					var54++;
					var46 -= var42;
					var39 += Pix2D.width2d;
				}
			} else {
				int var63 = var38;
				while (var63 < 0) {
					int var64 = var39;
					int var65 = var46;
					int var66 = (var42 * var44 >> 4) + var47;
					int var67 = var37;
					if (var46 >= 0 && var46 - (this.field2508 << 12) < 0) {
						if (var66 < 0) {
							int var68 = (var42 - 1 - var66) / var42;
							var67 = var37 + var68;
							var66 += var42 * var68;
							var64 = var39 + var68;
						}
						int var69;
						if ((var69 = (var66 + 1 - (this.field2501 << 12) - var42) / var42) > var67) {
							var67 = var69;
						}
						while (var67 < 0) {
							int var70 = this.field2506[(var65 >> 12) + (var66 >> 12) * this.field2508];
							if (var70 == 0) {
								var64++;
							} else {
								Pix2D.data[var64++] = var70;
							}
							var66 += var42;
							var67++;
						}
					}
					var63++;
					var46 -= var42;
					var39 += Pix2D.width2d;
				}
			}
		} else if (var43 < 0) {
			if (var42 == 0) {
				int var71 = var38;
				while (var71 < 0) {
					int var72 = var39;
					int var73 = (var43 * var44 >> 4) + var46;
					int var74 = var47;
					int var75 = var37;
					if (var47 >= 0 && var47 - (this.field2501 << 12) < 0) {
						int var76;
						if ((var76 = var73 - (this.field2508 << 12)) >= 0) {
							int var77 = (var43 - var76) / var43;
							var75 = var37 + var77;
							var73 += var43 * var77;
							var72 = var39 + var77;
						}
						int var78;
						if ((var78 = (var73 - var43) / var43) > var75) {
							var75 = var78;
						}
						while (var75 < 0) {
							int var79 = this.field2506[(var73 >> 12) + (var74 >> 12) * this.field2508];
							if (var79 == 0) {
								var72++;
							} else {
								Pix2D.data[var72++] = var79;
							}
							var73 += var43;
							var75++;
						}
					}
					var71++;
					var47 += var43;
					var39 += Pix2D.width2d;
				}
			} else if (var42 < 0) {
				int var80 = var38;
				while (var80 < 0) {
					int var81 = var39;
					int var82 = (var43 * var44 >> 4) + var46;
					int var83 = (var42 * var44 >> 4) + var47;
					int var84 = var37;
					int var85;
					if ((var85 = var82 - (this.field2508 << 12)) >= 0) {
						int var86 = (var43 - var85) / var43;
						var84 = var37 + var86;
						var82 += var43 * var86;
						var83 += var42 * var86;
						var81 = var39 + var86;
					}
					int var87;
					if ((var87 = (var82 - var43) / var43) > var84) {
						var84 = var87;
					}
					int var88;
					if ((var88 = var83 - (this.field2501 << 12)) >= 0) {
						int var89 = (var42 - var88) / var42;
						var84 += var89;
						var82 += var43 * var89;
						var83 += var42 * var89;
						var81 += var89;
					}
					int var90;
					if ((var90 = (var83 - var42) / var42) > var84) {
						var84 = var90;
					}
					while (var84 < 0) {
						int var91 = this.field2506[(var82 >> 12) + (var83 >> 12) * this.field2508];
						if (var91 == 0) {
							var81++;
						} else {
							Pix2D.data[var81++] = var91;
						}
						var82 += var43;
						var83 += var42;
						var84++;
					}
					var80++;
					var46 -= var42;
					var47 += var43;
					var39 += Pix2D.width2d;
				}
			} else {
				int var92 = var38;
				while (var92 < 0) {
					int var93 = var39;
					int var94 = (var43 * var44 >> 4) + var46;
					int var95 = (var42 * var44 >> 4) + var47;
					int var96 = var37;
					int var97;
					if ((var97 = var94 - (this.field2508 << 12)) >= 0) {
						int var98 = (var43 - var97) / var43;
						var96 = var37 + var98;
						var94 += var43 * var98;
						var95 += var42 * var98;
						var93 = var39 + var98;
					}
					int var99;
					if ((var99 = (var94 - var43) / var43) > var96) {
						var96 = var99;
					}
					if (var95 < 0) {
						int var100 = (var42 - 1 - var95) / var42;
						var96 += var100;
						var94 += var43 * var100;
						var95 += var42 * var100;
						var93 += var100;
					}
					int var101;
					if ((var101 = (var95 + 1 - (this.field2501 << 12) - var42) / var42) > var96) {
						var96 = var101;
					}
					while (var96 < 0) {
						int var102 = this.field2506[(var94 >> 12) + (var95 >> 12) * this.field2508];
						if (var102 == 0) {
							var93++;
						} else {
							Pix2D.data[var93++] = var102;
						}
						var94 += var43;
						var95 += var42;
						var96++;
					}
					var92++;
					var46 -= var42;
					var47 += var43;
					var39 += Pix2D.width2d;
				}
			}
		} else if (var42 == 0) {
			int var103 = var38;
			while (var103 < 0) {
				int var104 = var39;
				int var105 = (var43 * var44 >> 4) + var46;
				int var106 = var47;
				int var107 = var37;
				if (var47 >= 0 && var47 - (this.field2501 << 12) < 0) {
					if (var105 < 0) {
						int var108 = (var43 - 1 - var105) / var43;
						var107 = var37 + var108;
						var105 += var43 * var108;
						var104 = var39 + var108;
					}
					int var109;
					if ((var109 = (var105 + 1 - (this.field2508 << 12) - var43) / var43) > var107) {
						var107 = var109;
					}
					while (var107 < 0) {
						int var110 = this.field2506[(var105 >> 12) + (var106 >> 12) * this.field2508];
						if (var110 == 0) {
							var104++;
						} else {
							Pix2D.data[var104++] = var110;
						}
						var105 += var43;
						var107++;
					}
				}
				var103++;
				var47 += var43;
				var39 += Pix2D.width2d;
			}
		} else if (var42 < 0) {
			int var111 = var38;
			while (var111 < 0) {
				int var112 = var39;
				int var113 = (var43 * var44 >> 4) + var46;
				int var114 = (var42 * var44 >> 4) + var47;
				int var115 = var37;
				if (var113 < 0) {
					int var116 = (var43 - 1 - var113) / var43;
					var115 = var37 + var116;
					var113 += var43 * var116;
					var114 += var42 * var116;
					var112 = var39 + var116;
				}
				int var117;
				if ((var117 = (var113 + 1 - (this.field2508 << 12) - var43) / var43) > var115) {
					var115 = var117;
				}
				int var118;
				if ((var118 = var114 - (this.field2501 << 12)) >= 0) {
					int var119 = (var42 - var118) / var42;
					var115 += var119;
					var113 += var43 * var119;
					var114 += var42 * var119;
					var112 += var119;
				}
				int var120;
				if ((var120 = (var114 - var42) / var42) > var115) {
					var115 = var120;
				}
				while (var115 < 0) {
					int var121 = this.field2506[(var113 >> 12) + (var114 >> 12) * this.field2508];
					if (var121 == 0) {
						var112++;
					} else {
						Pix2D.data[var112++] = var121;
					}
					var113 += var43;
					var114 += var42;
					var115++;
				}
				var111++;
				var46 -= var42;
				var47 += var43;
				var39 += Pix2D.width2d;
			}
		} else {
			int var122 = var38;
			while (var122 < 0) {
				int var123 = var39;
				int var124 = (var43 * var44 >> 4) + var46;
				int var125 = (var42 * var44 >> 4) + var47;
				int var126 = var37;
				if (var124 < 0) {
					int var127 = (var43 - 1 - var124) / var43;
					var126 = var37 + var127;
					var124 += var43 * var127;
					var125 += var42 * var127;
					var123 = var39 + var127;
				}
				int var128;
				if ((var128 = (var124 + 1 - (this.field2508 << 12) - var43) / var43) > var126) {
					var126 = var128;
				}
				if (var125 < 0) {
					int var129 = (var42 - 1 - var125) / var42;
					var126 += var129;
					var124 += var43 * var129;
					var125 += var42 * var129;
					var123 += var129;
				}
				int var130;
				if ((var130 = (var125 + 1 - (this.field2501 << 12) - var42) / var42) > var126) {
					var126 = var130;
				}
				while (var126 < 0) {
					int var131 = this.field2506[(var124 >> 12) + (var125 >> 12) * this.field2508];
					if (var131 == 0) {
						var123++;
					} else {
						Pix2D.data[var123++] = var131;
					}
					var124 += var43;
					var125 += var42;
					var126++;
				}
				var122++;
				var46 -= var42;
				var47 += var43;
				var39 += Pix2D.width2d;
			}
		}
	}

	@ObfuscatedName("fq.cy(Lft;II)V")
	public void method2694(Pix8 arg0, int arg1, int arg2) {
		if (right - left != arg0.field2513 || bottom - top != arg0.field2514) {
			throw new IllegalStateException();
		}
		int var4 = this.field2502 + arg1;
		int var5 = this.field2499 + arg2;
		int var6 = Pix2D.width2d * var5 + var4;
		int var7 = 0;
		int var8 = this.field2501;
		int var9 = this.field2508;
		int var10 = Pix2D.width2d - var9;
		int var11 = 0;
		if (var5 < top) {
			int var12 = top - var5;
			var8 -= var12;
			var5 = top;
			var7 += var9 * var12;
			var6 += Pix2D.width2d * var12;
		}
		if (var5 + var8 > bottom) {
			var8 -= var5 + var8 - bottom;
		}
		if (var4 < left) {
			int var13 = left - var4;
			var9 -= var13;
			var4 = left;
			var7 += var13;
			var6 += var13;
			var11 += var13;
			var10 += var13;
		}
		if (var4 + var9 > right) {
			int var14 = var4 + var9 - right;
			var9 -= var14;
			var11 += var14;
			var10 += var14;
		}
		if (var9 > 0 && var8 > 0) {
			int var15 = (var5 - top) * arg0.field2513 + (var4 - left);
			int var16 = arg0.field2513 - var9;
			method2688(Pix2D.data, this.field2506, 0, var7, var6, var15, var9, var8, var10, var11, var16, arg0.field2511);
		}
	}

	@ObfuscatedName("fq.cq([I[IIIIIIIIII[B)V")
	public static void method2688(int[] arg0, int[] arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9, int arg10, byte[] arg11) {
		int var12 = -(arg6 >> 2);
		int var13 = -(arg6 & 0x3);
		for (int var14 = -arg7; var14 < 0; var14++) {
			for (int var15 = var12; var15 < 0; var15++) {
				int var16 = arg1[arg3++];
				if (var16 != 0 && arg11[arg5] == 0) {
					arg0[arg4++] = var16;
				} else {
					arg4++;
				}
				arg5++;
				int var17 = arg1[arg3++];
				if (var17 != 0 && arg11[arg5] == 0) {
					arg0[arg4++] = var17;
				} else {
					arg4++;
				}
				arg5++;
				int var18 = arg1[arg3++];
				if (var18 != 0 && arg11[arg5] == 0) {
					arg0[arg4++] = var18;
				} else {
					arg4++;
				}
				arg5++;
				int var19 = arg1[arg3++];
				if (var19 != 0 && arg11[arg5] == 0) {
					arg0[arg4++] = var19;
				} else {
					arg4++;
				}
				arg5++;
			}
			for (int var20 = var13; var20 < 0; var20++) {
				int var21 = arg1[arg3++];
				if (var21 != 0 && arg11[arg5] == 0) {
					arg0[arg4++] = var21;
				} else {
					arg4++;
				}
				arg5++;
			}
			arg4 += arg8;
			arg3 += arg9;
			arg5 += arg10;
		}
	}
}
