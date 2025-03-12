package jagex2.client;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.awt.*;

// name taken from rsc
@OriginalClass("client.client!b")
public class ViewBoxMapView extends Frame {

	@OriginalMember(owner = "client.client!b", name = "b", descriptor = "Lclient!a;")
	private final GameShellMapView shell;

	public Insets insets;

	@OriginalMember(owner = "client.client!b", name = "<init>", descriptor = "(IILclient!a;I)V")
	public ViewBoxMapView(@OriginalArg(2) GameShellMapView shell, @OriginalArg(3) int width, @OriginalArg(0) int height) {
		this.shell = shell;
		this.setTitle("Jagex");
		this.setResizable(false);
		//this.show();
		this.toFront();
		this.insets = this.getInsets();
		this.resize(width, height);
	}

	@OriginalMember(owner = "client.client!b", name = "getGraphics", descriptor = "()Ljava/awt/Graphics;")
	@Override
	public Graphics getGraphics() {
		@Pc(2) Graphics g = super.getGraphics();
		if (Configuration.INTERCEPT_GRAPHICS)
			g = GameShellMapView.image.getGraphics();
/*        if (this.insets != null) {
		    g.translate(this.insets.left, this.insets.top);
        }*/
		return g;
	}

	@OriginalMember(owner = "client.client!b", name = "update", descriptor = "(Ljava/awt/Graphics;)V")
	@Override
	public void update(@OriginalArg(0) Graphics g) {
		this.shell.update(g);
	}

	@OriginalMember(owner = "client.client!b", name = "paint", descriptor = "(Ljava/awt/Graphics;)V")
	@Override
	public void paint(@OriginalArg(0) Graphics g) {
		this.shell.paint(g);
	}
}
