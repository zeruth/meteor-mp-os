/*
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package org.apache.commons.imaging.formats.jpeg.decoder;

import org.apache.commons.imaging.ImageReadException;
import org.apache.commons.imaging.formats.jpeg.JpegConstants;

import java.io.IOException;
import java.io.InputStream;

class JpegInputStream {
    // Figure F.18, F.2.2.5, page 111 of ITU-T T.81
    private final InputStream is;
    private int cnt;
    private int b;

    public JpegInputStream(final InputStream is) {
        this.is = is;
    }

    public int nextBit() throws IOException, ImageReadException {
        if (cnt == 0) {
            b = is.read();
            if (b < 0) {
                throw new ImageReadException("Premature End of File");
            }
            cnt = 8;
            if (b == 0xff) {
                final int b2 = is.read();
                if (b2 < 0) {
                    throw new ImageReadException("Premature End of File");
                }
                if (b2 != 0) {
                    if (b2 == (0xff & JpegConstants.DNL_MARKER)) {
                        throw new ImageReadException("DNL not yet supported");
                    }
                    throw new ImageReadException("Invalid marker found "
                            + "in entropy data");
                }
            }
        }
        final int bit = (b >> 7) & 0x1;
        cnt--;
        b <<= 1;
        return bit;
    }
}
