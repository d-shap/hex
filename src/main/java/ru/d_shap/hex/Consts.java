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
 * Predefined values for byte-to-hex conversion and vice versa.
 *
 * @author Dmitry Shapovalov
 */
final class Consts {

    static final byte[] TO_LOWERCASE_HEX;

    static {
        TO_LOWERCASE_HEX = new byte[16];

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

    static final byte[] TO_UPPDERCASE_HEX;

    static {
        TO_UPPDERCASE_HEX = new byte[16];

        TO_UPPDERCASE_HEX[0] = '0';
        TO_UPPDERCASE_HEX[1] = '1';
        TO_UPPDERCASE_HEX[2] = '2';
        TO_UPPDERCASE_HEX[3] = '3';
        TO_UPPDERCASE_HEX[4] = '4';
        TO_UPPDERCASE_HEX[5] = '5';
        TO_UPPDERCASE_HEX[6] = '6';
        TO_UPPDERCASE_HEX[7] = '7';
        TO_UPPDERCASE_HEX[8] = '8';
        TO_UPPDERCASE_HEX[9] = '9';
        TO_UPPDERCASE_HEX[10] = 'A';
        TO_UPPDERCASE_HEX[11] = 'B';
        TO_UPPDERCASE_HEX[12] = 'C';
        TO_UPPDERCASE_HEX[13] = 'D';
        TO_UPPDERCASE_HEX[14] = 'E';
        TO_UPPDERCASE_HEX[15] = 'F';
    }

    static final int[] FROM_HEX;

    static {
        int length = Math.max('f', 'F');
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

    private Consts() {
        super();
    }

}
