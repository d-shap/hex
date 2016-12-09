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

/**
 * Predefined values for byte-to-hex and hex-to-byte conversions.
 *
 * @author Dmitry Shapovalov
 */
final class Consts {

    static final int[] TO_LOWERCASE_HEX;

    static {
        TO_LOWERCASE_HEX = new int[16];

        TO_LOWERCASE_HEX[0] = '0';
        TO_LOWERCASE_HEX[1] = '1';
        TO_LOWERCASE_HEX[2] = '2';
        TO_LOWERCASE_HEX[3] = '3';
        TO_LOWERCASE_HEX[4] = '4';
        TO_LOWERCASE_HEX[5] = '5';
        TO_LOWERCASE_HEX[6] = '6';
        TO_LOWERCASE_HEX[7] = '7';
        TO_LOWERCASE_HEX[8] = '8';
        TO_LOWERCASE_HEX[9] = '9';
        TO_LOWERCASE_HEX[10] = 'a';
        TO_LOWERCASE_HEX[11] = 'b';
        TO_LOWERCASE_HEX[12] = 'c';
        TO_LOWERCASE_HEX[13] = 'd';
        TO_LOWERCASE_HEX[14] = 'e';
        TO_LOWERCASE_HEX[15] = 'f';
    }

    static final int[] TO_LOWERCASE_HEX_UPPER_SYMBOL;

    static {
        TO_LOWERCASE_HEX_UPPER_SYMBOL = new int[256];
        for (int i = 0; i < TO_LOWERCASE_HEX_UPPER_SYMBOL.length; i++) {
            TO_LOWERCASE_HEX_UPPER_SYMBOL[i] = TO_LOWERCASE_HEX[(i & 0xF0) >> 4];
        }
    }

    static final int[] TO_LOWERCASE_HEX_LOWER_SYMBOL;

    static {
        TO_LOWERCASE_HEX_LOWER_SYMBOL = new int[256];
        for (int i = 0; i < TO_LOWERCASE_HEX_LOWER_SYMBOL.length; i++) {
            TO_LOWERCASE_HEX_LOWER_SYMBOL[i] = TO_LOWERCASE_HEX[i & 0x0F];
        }
    }

    static final int[] TO_UPPERCASE_HEX;

    static {
        TO_UPPERCASE_HEX = new int[16];

        TO_UPPERCASE_HEX[0] = '0';
        TO_UPPERCASE_HEX[1] = '1';
        TO_UPPERCASE_HEX[2] = '2';
        TO_UPPERCASE_HEX[3] = '3';
        TO_UPPERCASE_HEX[4] = '4';
        TO_UPPERCASE_HEX[5] = '5';
        TO_UPPERCASE_HEX[6] = '6';
        TO_UPPERCASE_HEX[7] = '7';
        TO_UPPERCASE_HEX[8] = '8';
        TO_UPPERCASE_HEX[9] = '9';
        TO_UPPERCASE_HEX[10] = 'A';
        TO_UPPERCASE_HEX[11] = 'B';
        TO_UPPERCASE_HEX[12] = 'C';
        TO_UPPERCASE_HEX[13] = 'D';
        TO_UPPERCASE_HEX[14] = 'E';
        TO_UPPERCASE_HEX[15] = 'F';
    }

    static final int[] TO_UPPERCASE_HEX_UPPER_SYMBOL;

    static {
        TO_UPPERCASE_HEX_UPPER_SYMBOL = new int[256];
        for (int i = 0; i < TO_UPPERCASE_HEX_UPPER_SYMBOL.length; i++) {
            TO_UPPERCASE_HEX_UPPER_SYMBOL[i] = TO_UPPERCASE_HEX[(i & 0xF0) >> 4];
        }
    }

    static final int[] TO_UPPERCASE_HEX_LOWER_SYMBOL;

    static {
        TO_UPPERCASE_HEX_LOWER_SYMBOL = new int[256];
        for (int i = 0; i < TO_UPPERCASE_HEX_LOWER_SYMBOL.length; i++) {
            TO_UPPERCASE_HEX_LOWER_SYMBOL[i] = TO_UPPERCASE_HEX[i & 0x0F];
        }
    }

    static final int[] FROM_HEX;

    static {
        int length = Math.max('f', 'F') + 1;
        FROM_HEX = new int[length];

        for (int i = 0; i < FROM_HEX.length; i++) {
            FROM_HEX[i] = -1;
        }

        FROM_HEX['0'] = 0;
        FROM_HEX['1'] = 1;
        FROM_HEX['2'] = 2;
        FROM_HEX['3'] = 3;
        FROM_HEX['4'] = 4;
        FROM_HEX['5'] = 5;
        FROM_HEX['6'] = 6;
        FROM_HEX['7'] = 7;
        FROM_HEX['8'] = 8;
        FROM_HEX['9'] = 9;

        FROM_HEX['a'] = 10;
        FROM_HEX['A'] = 10;

        FROM_HEX['b'] = 11;
        FROM_HEX['B'] = 11;

        FROM_HEX['c'] = 12;
        FROM_HEX['C'] = 12;

        FROM_HEX['d'] = 13;
        FROM_HEX['D'] = 13;

        FROM_HEX['e'] = 14;
        FROM_HEX['E'] = 14;

        FROM_HEX['f'] = 15;
        FROM_HEX['F'] = 15;
    }

    static final int[] FROM_HEX_UPPER_BYTE;

    static {
        FROM_HEX_UPPER_BYTE = new int[FROM_HEX.length];
        for (int i = 0; i < FROM_HEX_UPPER_BYTE.length; i++) {
            if (FROM_HEX[i] == -1) {
                FROM_HEX_UPPER_BYTE[i] = -1;
            } else {
                FROM_HEX_UPPER_BYTE[i] = FROM_HEX[i] << 4;
            }
        }
    }

    static final int[] FROM_HEX_LOWER_BYTE;

    static {
        FROM_HEX_LOWER_BYTE = new int[FROM_HEX.length];
        for (int i = 0; i < FROM_HEX_LOWER_BYTE.length; i++) {
            FROM_HEX_LOWER_BYTE[i] = FROM_HEX[i];
        }
    }

    private Consts() {
        super();
    }

}
