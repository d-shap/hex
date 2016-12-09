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
 * Class to perform bytes to hex and hex to bytes conversions.
 *
 * @author Dmitry Shapovalov
 */
public final class HexHelper {

    private HexHelper() {
        super();
    }

    /**
     * Convert byte array to hex string. Upper case symbols are used for hex representation.
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
            return "";
        }

        int[] hexUpperSymbols;
        int[] hexLowerSymbols;
        if (upperCase) {
            hexUpperSymbols = Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL;
            hexLowerSymbols = Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL;
        } else {
            hexUpperSymbols = Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL;
            hexLowerSymbols = Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL;
        }

        StringBuilder buffer = new StringBuilder(2 * bytes.length);
        for (byte value : bytes) {
            int idx = value & 0xFF;
            int upperByte = hexUpperSymbols[idx];
            buffer.append((char) upperByte);
            int lowerByte = hexLowerSymbols[idx];
            buffer.append((char) lowerByte);
        }
        return buffer.toString();
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
            throw new HexRuntimeException(ExceptionMessageHelper.createWrongHexStringSizeMessage(hexLength));
        }
        int resultLength = hexLength / 2;
        if (result.length < resultLength) {
            throw new HexRuntimeException(ExceptionMessageHelper.createWrongResultArrayMessage(resultLength, result.length));
        }

        convertToBytes(hex, result);
        return resultLength;
    }

    /**
     * Convert hex string to byte array.
     *
     * @param hex hex string.
     * @return new byte array with conversion result.
     */
    public static byte[] toBytes(final String hex) {
        if (hex == null) {
            return new byte[0];
        }
        if ("".equals(hex)) {
            return new byte[0];
        }

        int hexLength = hex.length();
        if (hexLength % 2 != 0) {
            throw new HexRuntimeException(ExceptionMessageHelper.createWrongHexStringSizeMessage(hexLength));
        }
        int resultLength = hexLength / 2;
        byte[] result = new byte[resultLength];

        convertToBytes(hex, result);
        return result;
    }

    private static void convertToBytes(final String hex, final byte[] result) {
        int hexLength = hex.length();

        int hexIndex = 0;
        int symbol1;
        int symbol2;
        int resultIndex = 0;
        while (hexIndex < hexLength) {
            symbol1 = hex.charAt(hexIndex);
            if (!isHexSymbolValid(symbol1)) {
                throw new HexRuntimeException(ExceptionMessageHelper.createWrongHexSymbol(symbol1));
            }
            hexIndex++;

            symbol2 = hex.charAt(hexIndex);
            if (!isHexSymbolValid(symbol2)) {
                throw new HexRuntimeException(ExceptionMessageHelper.createWrongHexSymbol(symbol2));
            }
            hexIndex++;

            result[resultIndex] = (byte) (Consts.FROM_HEX_UPPER_BYTE[symbol1] + Consts.FROM_HEX_LOWER_BYTE[symbol2]);
            resultIndex++;
        }
    }

    static boolean isHexSymbolValid(final int symbol) {
        return symbol >= 0 && symbol < Consts.FROM_HEX.length && Consts.FROM_HEX[symbol] >= 0;
    }

    /**
     * Define, whether input string contains only hex symbols or not. String must contain an even number of symbols.
     *
     * @param hex hex string.
     * @return true, if hex string contains only hex symbols.
     */
    public static boolean isHexString(final String hex) {
        return isHexString(hex, true);
    }

    /**
     * Define, whether input string contains only hex symbols or not.
     *
     * @param hex       hex string.
     * @param evenCheck is hex string contains even number of symbols or not.
     * @return true, if input string contains only hex symbols.
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
            if (!isHexSymbolValid(hex.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
