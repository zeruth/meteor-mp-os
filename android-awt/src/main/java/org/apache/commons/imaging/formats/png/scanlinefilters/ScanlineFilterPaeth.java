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
package org.apache.commons.imaging.formats.png.scanlinefilters;

import org.apache.commons.imaging.ImageReadException;

import java.io.IOException;

public class ScanlineFilterPaeth extends ScanlineFilter {
    private final int bytesPerPixel;

    public ScanlineFilterPaeth(final int bytesPerPixel) {
        this.bytesPerPixel = bytesPerPixel;
    }

    private int paethPredictor(final int a, final int b, final int c) {
        // ; a = left, b = above, c = upper left
        final int p = a + b - c; // ; initial estimate
        final int pa = Math.abs(p - a); // ; distances to a, b, c
        final int pb = Math.abs(p - b);
        final int pc = Math.abs(p - c);
        // ; return nearest of a,b,c,
        // ; breaking ties in order a,b,c.
        if ((pa <= pb) && (pa <= pc)) {
            return a;
        } else if (pb <= pc) {
            return b;
        } else {
            return c;
        }
    }

    @Override
    public void unfilter(final byte[] src, final byte[] dst, final byte[] up)
            throws ImageReadException, IOException {
        for (int i = 0; i < src.length; i++) {
            int left = 0;
            final int prevIndex = i - bytesPerPixel;
            if (prevIndex >= 0) {
                left = dst[prevIndex];
            }

            int above = 0;
            if (up != null) {
                above = up[i];
            }
            // above = 255;

            int upperleft = 0;
            if ((prevIndex >= 0) && (up != null)) {
                upperleft = up[prevIndex];
            }
            // upperleft = 255;

            int paethPredictor = paethPredictor(0xff & left, 0xff & above, 0xff & upperleft);

            dst[i] = (byte) ((src[i] + paethPredictor) % 256);
            // dst[i] = (byte) ((src[i] + paethPredictor) );
            // dst[i] = src[i];

            // dst[i] = (byte) 0;
        }
    }
}
