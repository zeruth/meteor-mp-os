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

import java.io.IOException;
import java.io.InputStream;

class WhiteSpaceReader {
    private final InputStream is;

    public WhiteSpaceReader(final InputStream is) {
        this.is = is;
    }

    private char read() throws IOException {
        final int result = is.read();
        if (result < 0) {
            throw new IOException("PNM: Unexpected EOF");
        }
        return (char) result;
    }

    public char nextChar() throws IOException {
        char c = read();

        if (c == '#') {
            while ((c != '\n') && (c != '\r')) {
                c = read();
            }
        }
        return c;
    }

    public String readtoWhiteSpace() throws IOException {
        char c = nextChar();

        while (Character.isWhitespace(c)) {
            c = nextChar();
        }

        final StringBuilder buffer = new StringBuilder();

        while (!Character.isWhitespace(c)) {
            buffer.append(c);
            c = nextChar();
        }

        return buffer.toString();
    }

    public String readLine() throws IOException {
        final StringBuilder buffer = new StringBuilder();
        for (char c = read(); (c != '\n') && (c != '\r'); c = read()) {
            buffer.append(c);
        }
        return buffer.length() > 0 ? buffer.toString() : null;
    }
}
