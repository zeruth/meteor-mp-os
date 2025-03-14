/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.imaging.formats.pnm;

import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.common.ImageBuilder;

import java.io.IOException;
import java.io.InputStream;

abstract class FileInfo {
    protected final int width;
    protected final int height;
    protected final boolean rawbits;

    public FileInfo(final int width, final int height, final boolean rawbits) {
        this.width = width;
        this.height = height;
        this.rawbits = rawbits;
    }

    protected static int readSample(final InputStream is, final int bytesPerSample) throws IOException {
        int sample = 0;
        for (int i = 0; i < bytesPerSample; i++) {
            final int nextByte = is.read();
            if (nextByte < 0) {
                throw new IOException("PNM: Unexpected EOF");
            }
            sample <<= 8;
            sample |= nextByte;
        }
        return sample;
    }

    protected static int scaleSample(int sample, final float scale, final int max) throws IOException {
        if (sample < 0) {
            // Even netpbm tools break for files like this
            throw new IOException("Negative pixel values are invalid in PNM files");
        } else if (sample > max) {
            // invalid values -> black
            sample = 0;
        }
        return (int) ((sample * scale / max) + 0.5f);
    }

    public abstract boolean hasAlpha();

    public abstract int getNumComponents();

    public abstract int getBitDepth();

    public abstract ImageFormat getImageType();

    public abstract String getImageTypeDescription();

    public abstract String getMIMEType();

    public abstract int getColorType();

    public abstract int getRGB(WhiteSpaceReader wsr) throws IOException;

    public abstract int getRGB(InputStream is) throws IOException;

    protected void newline() {
        // do nothing by default.
    }

    public void readImage(final ImageBuilder imageBuilder, final InputStream is)
            throws IOException {
        // is = new BufferedInputStream(is);
        // int count = 0;
        //
        // try
        // {

        if (!rawbits) {
            final WhiteSpaceReader wsr = new WhiteSpaceReader(is);

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    final int rgb = getRGB(wsr);

                    imageBuilder.setRGB(x, y, rgb);
                    // count++;
                }
                newline();
            }
        } else {
            for (int y = 0; y < height; y++) {
                // System.out.println("y: " + y);
                for (int x = 0; x < width; x++) {
                    final int rgb = getRGB(is);
                    imageBuilder.setRGB(x, y, rgb);
                    // count++;
                }
                newline();
            }
        }
        // }
        // finally
        // {
        // System.out.println("count: " + count);
        // dump();
        // }
    }
}
