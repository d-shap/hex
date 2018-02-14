///////////////////////////////////////////////////////////////////////////////////////////////////
// Hex library converts bytes to the hex representation and vice versa.
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
 * Input stream to read the hex representation of the bytes.
 *
 * @author Dmitry Shapovalov
 */
public final class HexInputStream extends InputStream {

    private final InputStream _inputStream;

    /**
     * Create new object.
     *
     * @param inputStream input stream with the hex representation of the bytes.
     */
    public HexInputStream(final InputStream inputStream) {
        super();
        _inputStream = inputStream;
    }

    @Override
    public int read() throws IOException {
        int character1 = _inputStream.read();
        if (character1 < 0) {
            return -1;
        }
        if (!HexHelper.isHexCharacterValid(character1)) {
            throw new IOException(ExceptionMessageHelper.createWrongHexStringCharacterMessage(character1));
        }

        int character2 = _inputStream.read();
        if (character2 < 0) {
            throw new IOException(ExceptionMessageHelper.createEndOfStreamMessage());
        }
        if (!HexHelper.isHexCharacterValid(character2)) {
            throw new IOException(ExceptionMessageHelper.createWrongHexStringCharacterMessage(character2));
        }

        return Consts.FROM_HEX_UPPER_BYTE[character1] + Consts.FROM_HEX_LOWER_BYTE[character2];
    }

    @Override
    public long skip(final long count) throws IOException {
        if (count < 0) {
            return -1;
        } else {
            long byteCount = count * 2;
            long result = _inputStream.skip(byteCount);
            return result / 2;
        }
    }

    @Override
    public int available() throws IOException {
        int result = _inputStream.available();
        return result / 2;
    }

    @Override
    public void close() throws IOException {
        _inputStream.close();
    }

}
