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
 * Class to perform bytes to hex and hex to bytes transformations.
 *
 * @author Dmitry Shapovalov
 */
public final class HexHelper {

    private HexHelper() {
        super();
    }

    /**
     * Convert byte array to hex string.
     *
     * @param bytes byte array.
     * @return hex string.
     */
    public static String toHex(final byte[] bytes) {
        return toHex(bytes, true);
    }

    /**
     * Convert byte array to hex string.
     *
     * @param bytes     byte array.
     * @param upperCase use upper case symbols or not.
     * @return hex string.
     */
    public static String toHex(final byte[] bytes, final boolean upperCase) {
        if (bytes == null) {
            return null;
        }

        char[] hex;
        if (upperCase) {
            hex = Consts.TO_UPPDERCASE_HEX;
        } else {
            hex = Consts.TO_LOWERCASE_HEX;
        }

        StringBuilder buffer = new StringBuilder(2 * bytes.length);
        for (byte value : bytes) {
            char upperByte = hex[getUpperByte(value)];
            buffer.append(upperByte);
            char lowerByte = hex[getLowerByte(value)];
            buffer.append(lowerByte);
        }
        return buffer.toString();
    }

    static int getUpperByte(final int value) {
        return (value & 0xF0) >> 4;
    }

    static int getLowerByte(final int value) {
        return value & 0x0F;
    }

    /**
     * Convert hex string to byte array.
     *
     * @param hex    hex string.
     * @param result byte array to write result.
     * @return number of bytes affected in byte array.
     */
    public static int toBytes(final String hex, final byte[] result) {
        if (hex == null) {
            return 0;
        }
        if ("".equals(hex)) {
            return 0;
        }

        int hexLength = hex.length();
        if (hexLength % 2 != 0) {
            throw new HexRuntimeException("Hex string must contain an even number of characters");
        }
        int arrLength = hexLength / 2;
        if (result.length < arrLength) {
            throw new HexRuntimeException("Byte array is too small for hex string");
        }

        convertToBytes(hex, result);
        return arrLength;
    }

    /**
     * Convert hex string to byte array.
     *
     * @param hex hex string.
     * @return new byte array with conversion result.
     */
    public static byte[] toBytes(final String hex) {
        if (hex == null) {
            return null;
        }

        int hexLength = hex.length();
        if (hexLength % 2 != 0) {
            throw new HexRuntimeException("Hex string must contain an even number of characters");
        }
        int arrLength = hexLength / 2;
        byte[] result = new byte[arrLength];

        convertToBytes(hex, result);
        return result;
    }

    private static void convertToBytes(final String hex, final byte[] result) {
        int hexIndex = 0;
        int resultIndex = 0;
        int hexLength = hex.length();

        int symbol1;
        int upperByte;
        int symbol2;
        int lowerByte;

        while (hexIndex < hexLength) {
            symbol1 = hex.charAt(hexIndex);
            upperByte = convertToBytePart(symbol1);
            if (upperByte < 0) {
                throw new HexRuntimeException("Wrong symbol obtained: '" + (char) symbol1 + "' (" + symbol1 + ")");
            }

            symbol2 = hex.charAt(hexIndex + 1);
            lowerByte = convertToBytePart(symbol2);
            if (lowerByte < 0) {
                throw new HexRuntimeException("Wrong symbol obtained: '" + (char) symbol2 + "' (" + symbol2 + ")");
            }

            result[resultIndex] = (byte) getFullByte(upperByte, lowerByte);
            hexIndex += 2;
            resultIndex++;
        }
    }

    static int convertToBytePart(final int value) {
        if (value >= 0 && value < Consts.FROM_HEX.length && Consts.FROM_HEX[value] >= 0) {
            return (byte) Consts.FROM_HEX[value];
        } else {
            return -1;
        }
    }

    static int getFullByte(final int upperByte, final int lowerByte) {
        return (upperByte << 4) + lowerByte;
    }

    /**
     * Define, whether input strign contains only hex symbols or not.
     *
     * @param hex hex string.
     * @return true, if hex string contains only hex symbols.
     */
    public static boolean isHexString(final String hex) {
        return isHexString(hex, true);
    }

    /**
     * Define, whether input strign contains only hex symbols or not.
     *
     * @param hex       hex string.
     * @param evenCheck is hex string contains even number of symbols or not.
     * @return true, if hex string contains only hex symbols.
     */
    public static boolean isHexString(final String hex, final boolean evenCheck) {
        if (hex == null) {
            return false;
        }
        if ("".equals(hex)) {
            return false;
        }

        int hexLength = hex.length();
        if (evenCheck && hexLength % 2 != 0) {
            return false;
        }
        for (int i = 0; i < hexLength; i++) {
            if (convertToBytePart(hex.charAt(i)) < 0) {
                return false;
            }
        }
        return true;
    }

}
