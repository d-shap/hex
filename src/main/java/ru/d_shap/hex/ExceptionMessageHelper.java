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

/**
 * Messages for all exceptions.
 *
 * @author Dmitry Shapovalov
 */
final class ExceptionMessageHelper {

    private ExceptionMessageHelper() {
        super();
    }

    static String createWrongByteArrayIndexMessage(final int index) {
        return "Wrong byte array index (" + index + ")";
    }

    static String createWrongByteArrayLengthMessage(final int length) {
        return "Wrong byte array length (" + length + ")";
    }

    static String createWrongByteArrayLengthMessage(final int actual, final int expected) {
        return "Wrong byte array length (" + actual + "), expected length is (" + expected + ")";
    }

    static String createWrongHexStringIndexMessage(final int index) {
        return "Wrong hex string index (" + index + ")";
    }

    static String createWrongHexStringLengthMessage(final int length) {
        return "Wrong hex string length (" + length + ")";
    }

    static String createWrongHexStringCharacterMessage(final int character) {
        return "Wrong character obtained ('" + (char) character + "', " + character + ")";
    }

    static String createEndOfStreamMessage() {
        return "Unexpected end of stream";
    }

}
