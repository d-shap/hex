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
 * Output stream to write hex representation of bytes.
 *
 * @author Dmitry Shapovalov
 */
public final class HexOutputStream extends OutputStream {

    private final OutputStream _outputStream;

    private final int[] _hexUpperSymbols;

    private final int[] _hexLowerSymbols;

    /**
     * Create new object. Upper case symbols are used for the hex representation.
     *
     * @param outputStream output stream to write bytes.
     */
    public HexOutputStream(final OutputStream outputStream) {
        this(outputStream, true);
    }

    /**
     * Create new object.
     *
     * @param outputStream output stream to write bytes.
     * @param upperCase    use upper case symbols for the hex representation or not.
     */
    public HexOutputStream(final OutputStream outputStream, final boolean upperCase) {
        super();
        _outputStream = outputStream;
        if (upperCase) {
            _hexUpperSymbols = Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL;
            _hexLowerSymbols = Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL;
        } else {
            _hexUpperSymbols = Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL;
            _hexLowerSymbols = Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL;
        }
    }

    @Override
    public void write(final int value) throws IOException {
        int idx = value & 0xFF;
        int upperByte = _hexUpperSymbols[idx];
        _outputStream.write(upperByte);
        int lowerByte = _hexLowerSymbols[idx];
        _outputStream.write(lowerByte);
    }

    @Override
    public void close() throws IOException {
        _outputStream.close();
    }

}
