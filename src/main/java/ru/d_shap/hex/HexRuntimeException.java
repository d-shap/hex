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
 * Exception thrown during the bytes-to-hex and the hex-to-bytes conversions.
 *
 * @author Dmitry Shapovalov
 */
public final class HexRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Create new object.
     *
     * @param message excepton message.
     */
    HexRuntimeException(final String message) {
        super(message);
    }

}
