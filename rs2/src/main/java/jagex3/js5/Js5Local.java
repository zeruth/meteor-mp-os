package jagex3.js5;

import deob.ObfuscatedName;
import jagex3.client.applet.GameShell;
import jagex3.datastruct.ByteArrayCopier;
import jagex3.datastruct.LinkList;
import jagex3.io.FileStream;
import jagex3.io.Packet;

import java.util.zip.CRC32;

@ObfuscatedName("dq")
public class Js5Local extends Js5Index {

	@ObfuscatedName("dq.f")
	public FileStream index;

	@ObfuscatedName("dq.k")
	public FileStream masterIndex;

	@ObfuscatedName("dq.o")
	public int archive;

	@ObfuscatedName("dq.a")
	public volatile boolean field1575 = false;

	@ObfuscatedName("dq.h")
	public boolean field1576 = false;

	@ObfuscatedName("dq.x")
	public volatile boolean[] field1572;

	@ObfuscatedName("dq.p")
	public static CRC32 crc32 = new CRC32();

	@ObfuscatedName("dq.ad")
	public int field1580;

	@ObfuscatedName("dq.ac")
	public int field1577;

	@ObfuscatedName("dq.aa")
	public int field1581 = -1;

	public Js5Local(FileStream index, FileStream masterIndex, int archive, boolean discardPacked, boolean discardUnpacked, boolean arg5) {
		super(discardPacked, discardUnpacked);

		this.index = index;
		this.masterIndex = masterIndex;
		this.archive = archive;
		this.field1576 = arg5;

		int var8 = this.archive;
		if (Js5Remote.masterIndexBuffer == null) {
			Js5Remote.queueRequest(null, 255, 255, 0, (byte) 0, true);
			Js5Remote.field1200[var8] = this;
		} else {
			Js5Remote.masterIndexBuffer.pos = var8 * 8 + 5;
			int var9 = Js5Remote.masterIndexBuffer.g4();
			int var10 = Js5Remote.masterIndexBuffer.g4();
			this.method1476(var9, var10);
		}
	}

	@ObfuscatedName("dq.bo(B)I")
	public int method1483() {
		if (this.field1575) {
			return 100;
		} else if (this.packed == null) {
			int var1 = Js5Remote.transferProgress(255, this.archive);
			if (var1 >= 100) {
				var1 = 99;
			}
			return var1;
		} else {
			return 99;
		}
	}

	@ObfuscatedName("dq.d(IB)V")
	public void download(int arg0) {
		Js5Remote.prioritizeRequest(this.archive, arg0);
	}

	@ObfuscatedName("dq.i(IB)V")
	public void fetchGroup(int arg0) {
		if (this.index == null || this.field1572 == null || !this.field1572[arg0]) {
			Js5Remote.queueRequest(this, this.archive, arg0, this.groupChecksums[arg0], (byte) 2, true);
		} else {
			Js5RemoteThread.method1122(arg0, this.index, this);
		}
	}

	@ObfuscatedName("dq.bq(III)V")
	public void method1476(int arg0, int arg1) {
		this.field1580 = arg0;
		this.field1577 = arg1;
		if (this.masterIndex == null) {
			Js5Remote.queueRequest(this, 255, this.archive, this.field1580, (byte) 0, true);
		} else {
			Js5RemoteThread.method1122(this.archive, this.masterIndex, this);
		}
	}

	@ObfuscatedName("dq.bj(I[BZZB)V")
	public void method1467(int arg0, byte[] arg1, boolean arg2, boolean arg3) {
		if (!arg2) {
			arg1[arg1.length - 2] = (byte) (this.groupVersions[arg0] >> 8);
			arg1[arg1.length - 1] = (byte) this.groupVersions[arg0];
			if (this.index != null) {
				FileStream var12 = this.index;
				Js5LocalRequest var13 = new Js5LocalRequest();
				var13.field1772 = 0;
				var13.nodeId = arg0;
				var13.field1771 = arg1;
				var13.field1770 = var12;
				LinkList var14 = Js5RemoteThread.field1208;
				synchronized (Js5RemoteThread.field1208) {
					Js5RemoteThread.field1208.push(var13);
				}
				Object var16 = Js5RemoteThread.field1207;
				synchronized (Js5RemoteThread.field1207) {
					if (Js5RemoteThread.field1205 == 0) {
						GameShell.signlink.startThread(new Js5RemoteThread(), 5);
					}
					Js5RemoteThread.field1205 = 600;
				}
				this.field1572[arg0] = true;
			}
			if (arg3) {
				this.packed[arg0] = ByteArrayCopier.method1131(arg1, false);
			}
			return;
		}
		if (this.field1575) {
			throw new RuntimeException();
		}
		if (this.masterIndex != null) {
			int var5 = this.archive;
			FileStream var6 = this.masterIndex;
			Js5LocalRequest var7 = new Js5LocalRequest();
			var7.field1772 = 0;
			var7.nodeId = var5;
			var7.field1771 = arg1;
			var7.field1770 = var6;
			LinkList var8 = Js5RemoteThread.field1208;
			synchronized (Js5RemoteThread.field1208) {
				Js5RemoteThread.field1208.push(var7);
			}
			Object var10 = Js5RemoteThread.field1207;
			synchronized (Js5RemoteThread.field1207) {
				if (Js5RemoteThread.field1205 == 0) {
					GameShell.signlink.startThread(new Js5RemoteThread(), 5);
				}
				Js5RemoteThread.field1205 = 600;
			}
		}
		this.decode(arg1);
		this.method1469();
	}

	@ObfuscatedName("dq.bz(Lap;I[BZI)V")
	public void method1468(FileStream arg0, int arg1, byte[] arg2, boolean arg3) {
		if (this.masterIndex != arg0) {
			if (!arg3 && this.field1581 == arg1) {
				this.field1575 = true;
			}
			if (arg2 == null || arg2.length <= 2) {
				this.field1572[arg1] = false;
				if (this.field1576 || arg3) {
					Js5Remote.queueRequest(this, this.archive, arg1, this.groupChecksums[arg1], (byte) 2, arg3);
				}
				return;
			}
			crc32.reset();
			crc32.update(arg2, 0, arg2.length - 2);
			int var9 = (int) crc32.getValue();
			int var10 = ((arg2[arg2.length - 2] & 0xFF) << 8) + (arg2[arg2.length - 1] & 0xFF);
			if (this.groupChecksums[arg1] != var9 || this.groupVersions[arg1] != var10) {
				this.field1572[arg1] = false;
				if (this.field1576 || arg3) {
					Js5Remote.queueRequest(this, this.archive, arg1, this.groupChecksums[arg1], (byte) 2, arg3);
				}
				return;
			}
			this.field1572[arg1] = true;
			if (arg3) {
				this.packed[arg1] = ByteArrayCopier.method1131(arg2, false);
			}
			return;
		}
		if (this.field1575) {
			throw new RuntimeException();
		}
		if (arg2 == null) {
			Js5Remote.queueRequest(this, 255, this.archive, this.field1580, (byte) 0, true);
			return;
		}
		crc32.reset();
		crc32.update(arg2, 0, arg2.length);
		int var5 = (int) crc32.getValue();
		Packet var6 = new Packet(Js5Index.decompress(arg2));
		int var7 = var6.g1();
		if (var7 != 5 && var7 != 6) {
			throw new RuntimeException("");
		}
		int var8 = 0;
		if (var7 >= 6) {
			var8 = var6.g4();
		}
		if (this.field1580 != var5 || this.field1577 != var8) {
			Js5Remote.queueRequest(this, 255, this.archive, this.field1580, (byte) 0, true);
			return;
		}
		this.decode(arg2);
		this.method1469();
	}

	@ObfuscatedName("dq.bm(S)V")
	public void method1469() {
		this.field1572 = new boolean[this.packed.length];
		for (int var1 = 0; var1 < this.field1572.length; var1++) {
			this.field1572[var1] = false;
		}
		if (this.index == null) {
			this.field1575 = true;
			return;
		}
		this.field1581 = -1;
		for (int var2 = 0; var2 < this.field1572.length; var2++) {
			if (this.groupSizes[var2] > 0) {
				FileStream var3 = this.index;
				Js5LocalRequest var5 = new Js5LocalRequest();
				var5.field1772 = 1;
				var5.nodeId = var2;
				var5.field1770 = var3;
				var5.field1773 = this;
				LinkList var6 = Js5RemoteThread.field1208;
				synchronized (Js5RemoteThread.field1208) {
					Js5RemoteThread.field1208.push(var5);
				}
				Object var8 = Js5RemoteThread.field1207;
				synchronized (Js5RemoteThread.field1207) {
					if (Js5RemoteThread.field1205 == 0) {
						GameShell.signlink.startThread(new Js5RemoteThread(), 5);
					}
					Js5RemoteThread.field1205 = 600;
				}
				this.field1581 = var2;
			}
		}
		if (this.field1581 == -1) {
			this.field1575 = true;
		}
	}

	@ObfuscatedName("dq.bn(II)I")
	public int method1472(int arg0) {
		if (this.packed[arg0] == null) {
			return this.field1572[arg0] ? 100 : Js5Remote.transferProgress(this.archive, arg0);
		} else {
			return 100;
		}
	}

	@ObfuscatedName("dq.be(I)I")
	public int method1470() {
		int var1 = 0;
		int var2 = 0;
		for (int var3 = 0; var3 < this.packed.length; var3++) {
			if (this.groupSizes[var3] > 0) {
				var1 += 100;
				var2 += this.method1472(var3);
			}
		}
		if (var1 == 0) {
			return 100;
		} else {
			return var2 * 100 / var1;
		}
	}
}
