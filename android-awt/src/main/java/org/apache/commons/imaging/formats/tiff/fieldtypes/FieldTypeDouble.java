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
package org.apache.commons.imaging.formats.tiff.fieldtypes;

import org.apache.commons.imaging.ImageWriteException;
import org.apache.commons.imaging.common.ByteConversions;
import org.apache.commons.imaging.formats.tiff.TiffField;

import java.nio.ByteOrder;

public class FieldTypeDouble extends FieldType {
    public FieldTypeDouble(final int type, final String name) {
        super(type, name, 8);
    }

    @Override
    public Object getValue(final TiffField entry) {
        final byte[] bytes = entry.getByteArrayValue();
        if (entry.getCount() == 1) {
            return ByteConversions.toDouble(bytes,
                    entry.getByteOrder());
        }
        return ByteConversions.toDoubles(bytes, entry.getByteOrder());
    }

    @Override
    public byte[] writeData(final Object o, final ByteOrder byteOrder) throws ImageWriteException {
        if (o instanceof Double) {
            return ByteConversions.toBytes(((Double) o).doubleValue(),
                    byteOrder);
        } else if (o instanceof double[]) {
            final double[] numbers = (double[]) o;
            return ByteConversions.toBytes(numbers, byteOrder);
        } else if (o instanceof Double[]) {
            final Double[] numbers = (Double[]) o;
            final double[] values = new double[numbers.length];
            for (int i = 0; i < values.length; i++) {
                values[i] = numbers[i].doubleValue();
            }
            return ByteConversions.toBytes(values, byteOrder);
        } else {
            throw new ImageWriteException("Invalid data", o);
        }
    }

}
