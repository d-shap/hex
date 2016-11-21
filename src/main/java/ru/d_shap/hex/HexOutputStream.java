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
import java.io.OutputStream;

/**
 * Output stream to write hex representation of bytes;
 *
 * @author Dmitry Shapovalov
 */
public final class HexOutputStream extends OutputStream {

    private final OutputStream _outputStream;

    private final byte[] _hexes;

    /**
     * Create new object.
     *
     * @param outputStream output stream to write result.
     */
    public HexOutputStream(final OutputStream outputStream) {
        this(outputStream, true);
    }

    /**
     * Create new object.
     *
     * @param outputStream output stream to write result.
     * @param upperCase    use upper case symbols or not.
     */
    public HexOutputStream(final OutputStream outputStream, final boolean upperCase) {
        super();
        _outputStream = outputStream;
        if (upperCase) {
            _hexes = Consts.TO_UPPDERCASE_HEX;
        } else {
            _hexes = Consts.TO_LOWERCASE_HEX;
        }
    }

    @Override
    public void write(final int value) throws IOException {
        byte b1 = (byte) ((value & 0xF0) >> 4);
        _outputStream.write(_hexes[b1]);
        byte b2 = (byte) (value & 0x0F);
        _outputStream.write(_hexes[b2]);
    }

    @Override
    public void close() throws IOException {
        _outputStream.close();
    }

}
