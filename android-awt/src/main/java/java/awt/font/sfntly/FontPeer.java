package java.awt.font.sfntly;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.typography.font.sfntly.Font;
import com.google.typography.font.sfntly.FontFactory;
import com.google.typography.font.sfntly.Tag;
import com.google.typography.font.sfntly.table.core.*;
import com.google.typography.font.sfntly.table.core.CMapTable.CMapId;
import com.google.typography.font.sfntly.table.truetype.GlyphTable;
import com.google.typography.font.sfntly.table.truetype.LocaTable;
import org.apache.harmony.awt.gl.font.FontExtraMetrics;
import org.apache.harmony.awt.gl.font.FontPeerImpl;
import org.apache.harmony.awt.gl.font.Glyph;
import org.apache.harmony.awt.gl.font.LineMetricsImpl;

import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.InputStream;

public class FontPeer extends FontPeerImpl {

    private Font font;
    private float unitsPerEm;
    private int yMin;
    private int yMax;

    public static Context androidContext;

    public FontPeer(String name, int style, int size) {
        this.name = name;
        this.style = style;
        this.size = size;

        try {
            InputStream ttfInput;
            if (androidContext != null) {
                AssetManager assetManager = androidContext.getAssets();
                ttfInput = assetManager.open(name + ".ttf");
            } else {
                ttfInput = ClassLoader.getSystemClassLoader().getResourceAsStream(name + ".ttf");
            }
            if (ttfInput == null) {
                throw new RuntimeException("Couldn't open the font file: " + name + ".ttf");
            }
            font = FontFactory.getInstance().loadFonts(ttfInput)[0];
        } catch (IOException e) {
            throw new RuntimeException("Could not load font: " + name, e);
        }

        FontHeaderTable head = font.getTable(Tag.head);
        unitsPerEm = head.unitsPerEm();
        yMin = head.yMin();
        yMax = head.yMax();
    }

    @Override
    public FontExtraMetrics getExtraMetrics() {
        return new FontExtraMetrics();
    }

    @Override
    public LineMetrics getLineMetrics(String str, FontRenderContext frc,
                                      AffineTransform at) {

        HorizontalHeaderTable hhead = font.getTable(Tag.hhea);
        int numChars = str.length();
        return new LineMetricsImpl(
                numChars,
                0, // baseline index
                new float[numChars], // baseline offsets
                0, // underline thickness,
                0, //_underlineOffset
                0, //_strikethroughThickness,
                0, //_strikethroughOffset,
                toPixels(hhead.lineGap()),
                toPixels(hhead.lineGap() + hhead.ascender() + hhead.descender()),
                toPixels(hhead.ascender()),
                toPixels(hhead.descender()),
                toPixels(0)); // _maxCharWidth
    }

    private float toPixels(double sizeInUnits) {
        return (float) (sizeInUnits / unitsPerEm * size);
    }

    @Override
    public String getPSName() {
        return psName;
    }

    @Override
    public int getMissingGlyphCode() {
        return (int) '?';
    }

    @Override
    public Glyph getGlyph(char ch) {
        int glyphIndex = lookupGlyphIndex(ch);
        com.google.typography.font.sfntly.table.truetype.Glyph glyph = getGlyph(glyphIndex);


        HorizontalMetricsTable hmtx = font.getTable(Tag.hmtx);
        double w = 1f;
        try {
            w = toPixels(glyph.xMax() - glyph.xMin());
        } catch (Exception e) {

        }
        double h = 1f;
        try {
            h = toPixels(glyph.yMax() - glyph.yMin());
        } catch (Exception e) {

        }
        double a = 1f;
        try {
            a = toPixels(hmtx.advanceWidth(glyphIndex));
        } catch (Exception e) {

        }
        double width = w;
        double height = h;
        double advance = a;

        SfntlyGlyph sfntlyGlyph = new SfntlyGlyph(advance, width, height, glyph);
        sfntlyGlyph.setScale(size / unitsPerEm);
        sfntlyGlyph.setYBounds(yMin, yMax);
        sfntlyGlyph.setFontSize(size);
        sfntlyGlyph.setChar(ch);
        return sfntlyGlyph;
    }

    private com.google.typography.font.sfntly.table.truetype.Glyph getGlyph(int glyphId) {
        LocaTable locaTable = font.getTable(Tag.loca);
        GlyphTable glyphTable = font.getTable(Tag.glyf);
        int offset = locaTable.glyphOffset(glyphId);
        int length = locaTable.glyphLength(glyphId);

        com.google.typography.font.sfntly.table.truetype.Glyph glyph = glyphTable.glyph(offset, length);
        return glyph;
    }

    private int lookupGlyphIndex(char ch) {
        CMapTable cmapTable = font.getTable(Tag.cmap);
        CMap cmap = cmapTable.cmap(CMapId.WINDOWS_BMP);
        int glyphId = cmap.glyphId(ch);
        return glyphId;
    }

    @Override
    public void dispose() {
    }

    @Override
    public Glyph getDefaultGlyph() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean canDisplay(char c) {
        return lookupGlyphIndex(c) != CMapTable.NOTDEF;
    }

}
