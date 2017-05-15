///////////////////////////////////////////////////////////////////////////////////////////////////
// Hex library converts bytes to hex representation and vice versa.
// Copyright (C) 2016 Dmitry Shapovalov.
//
// This file is part of hex library.
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
        int symbol1 = _inputStream.read();
        if (symbol1 < 0) {
            return -1;
        }
        if (!HexHelper.isHexSymbolValid(symbol1)) {
            throw new IOException(ExceptionMessageHelper.createWrongHexSymbol(symbol1));
        }

        int symbol2 = _inputStream.read();
        if (symbol2 < 0) {
            throw new IOException(ExceptionMessageHelper.createEndOfStreamMessage());
        }
        if (!HexHelper.isHexSymbolValid(symbol2)) {
            throw new IOException(ExceptionMessageHelper.createWrongHexSymbol(symbol2));
        }

        return Consts.FROM_HEX_UPPER_BYTE[symbol1] + Consts.FROM_HEX_LOWER_BYTE[symbol2];
    }

    @Override
    public void close() throws IOException {
        super.close();
        _inputStream.close();
    }

}
