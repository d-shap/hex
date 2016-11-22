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
 * Auxilary class to perform hex-to-byte conversions.
 *
 * @author Dmitry Shapovalov
 */
public final class HexHelper {

    private HexHelper() {
        super();
    }

    public static String toHex(final byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        StringBuilder buffer = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            char upperByte = Consts.TO_UPPDERCASE_HEX[getUpperByte(b)];
            buffer.append(upperByte);
            char lowerByte = Consts.TO_UPPDERCASE_HEX[getLowerByte(b)];
            buffer.append(lowerByte);
        }
        return buffer.toString();
    }

    static byte getUpperByte(final int value) {
        return (byte) ((value & 0xF0) >> 4);
    }

    static byte getLowerByte(final int value) {
        return (byte) (value & 0x0F);
    }

    public static int toBytes(final String hex, final byte[] result) {
        if (hex == null) {
            return 0;
        }
        if ("".equals(hex)) {
            return 0;
        }

        int hexLength = hex.length();
        if (hexLength % 2 != 0) {
            throw new RuntimeException("Input string must contain an even number of characters");
        }
        int arrLength = hexLength / 2;
        if (result.length < arrLength) {
            throw new RuntimeException("Byte array is too small for hex string");
        }

        convertHexStrToBytes(hex, result, hexLength);
        return arrLength;
    }

    public static byte[] toBytes(final String hex) {
        if (hex == null) {
            return null;
        }

        int hexLength = hex.length();
        if (hexLength % 2 != 0) {
            throw new RuntimeException("Input string must contain an even number of characters");
        }
        int arrLength = hexLength / 2;

        byte[] result = new byte[arrLength];
        convertHexStrToBytes(hex, result, hexLength);
        return result;
    }

    private static void convertHexStrToBytes(final String hex, final byte[] result, final int hexLength) {
        int i = 0;
        int j = 0;
        int symbol1;
        int symbol2;
        while (i < hexLength) {
            symbol1 = convertHexToByte(hex.charAt(i));
            if (symbol1 < 0) {
                throw new RuntimeException("Wrong hex symbol found: " + hex.charAt(i));
            }
            symbol2 = convertHexToByte(hex.charAt(i + 1));
            if (symbol2 < 0) {
                throw new RuntimeException("Wrong hex symbol found: " + hex.charAt(i + 1));
            }
            result[j] = (byte) getByte(symbol1, symbol2);
            i += 2;
            j++;
        }
    }

    static int convertHexToByte(final int value) {
        if (value >= 0 && value < Consts.FROM_HEX.length && Consts.FROM_HEX[value] >= 0) {
            return (byte) Consts.FROM_HEX[value];
        } else {
            return -1;
        }
    }

    static int getByte(final int upperByte, final int lowerByte) {
        return (upperByte << 4) + lowerByte;
    }

    public static boolean isHexString(final String str) {
        if (str == null) {
            return false;
        }
        int lng = str.length();
        for (int i = 0; i < lng; i++) {
            if (convertHexToByte(str.charAt(i)) < 0) {
                return false;
            }
        }
        return true;
    }

}
