///////////////////////////////////////////////////////////////////////////////////////////////////
// Hex library converts bytes to hex representation and vice versa.
// Copyright (C) 2016 Dmitry Shapovalov.
//
// This file is part of Hex library.
//
// Hex library is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// Hex library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this program. If not, see <http://www.gnu.org/licenses/>.
///////////////////////////////////////////////////////////////////////////////////////////////////
package ru.d_shap.hex;

import java.io.IOException;
import java.io.InputStream;

/**
 * Input stream to read hex representation of bytes.
 *
 * @author Dmitry Shapovalov
 */
public final class HexInputStream extends InputStream {

    private final InputStream _inputStream;

    /**
     * Create new object.
     *
     * @param inputStream input stream to read bytes.
     */
    public HexInputStream(final InputStream inputStream) {
        super();
        _inputStream = inputStream;
    }

    @Override
    public int read() throws IOException {
        int i1 = _inputStream.read();
        if (i1 < 0) {
            return -1;
        }
        byte b1 = convertHexToByte(i1);

        int i2 = _inputStream.read();
        if (i2 < 0) {
            throw new IOException("Unexpected end of stream");
        }
        byte b2 = convertHexToByte(i2);

        return (b1 << 4) + b2;
    }

    private byte convertHexToByte(final int value) throws IOException {
        if (value < Consts.FROM_HEX.length && Consts.FROM_HEX[value] >= 0) {
            return (byte) Consts.FROM_HEX[value];
        }
        throw new IOException("Wrong symbol obtained: '" + (char) value + "' (" + value + ")");
    }

    @Override
    public void close() throws IOException {
        _inputStream.close();
    }

}
