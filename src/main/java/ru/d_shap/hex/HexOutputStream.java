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
import java.io.OutputStream;

/**
 * Output stream to write the hex representation of the bytes.
 *
 * @author Dmitry Shapovalov
 */
public final class HexOutputStream extends OutputStream {

    private final OutputStream _outputStream;

    private final int[] _hexUpperCharacters;

    private final int[] _hexLowerCharacters;

    /**
     * Create new object. Lower case characters are used for the hex representation.
     *
     * @param outputStream output stream with the hex representation of the bytes.
     */
    public HexOutputStream(final OutputStream outputStream) {
        this(outputStream, false);
    }

    /**
     * Create new object.
     *
     * @param outputStream output stream with the hex representation of the bytes.
     * @param upperCase    true to use upper case characters for the hex representation, false otherwise.
     */
    public HexOutputStream(final OutputStream outputStream, final boolean upperCase) {
        super();
        _outputStream = outputStream;
        if (upperCase) {
            _hexUpperCharacters = Consts.TO_UPPERCASE_HEX_UPPER_CHARACTER;
            _hexLowerCharacters = Consts.TO_UPPERCASE_HEX_LOWER_CHARACTER;
        } else {
            _hexUpperCharacters = Consts.TO_LOWERCASE_HEX_UPPER_CHARACTER;
            _hexLowerCharacters = Consts.TO_LOWERCASE_HEX_LOWER_CHARACTER;
        }
    }

    @Override
    public void write(final int value) throws IOException {
        int currentByte = value & 0xFF;
        int upperCharacter = _hexUpperCharacters[currentByte];
        _outputStream.write(upperCharacter);
        int lowerCharacter = _hexLowerCharacters[currentByte];
        _outputStream.write(lowerCharacter);
    }

    @Override
    public void flush() throws IOException {
        _outputStream.flush();
    }

    @Override
    public void close() throws IOException {
        _outputStream.close();
    }

}
