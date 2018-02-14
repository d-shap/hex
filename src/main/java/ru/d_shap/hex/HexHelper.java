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
 * Class to perform the bytes-to-hex and the hex-to-bytes conversions.
 *
 * @author Dmitry Shapovalov
 */
public final class HexHelper {

    private HexHelper() {
        super();
    }

    /**
     * Convert the byte array to the hex string. Lower case characters are used for the hex representation.
     *
     * @param bytes the byte array.
     * @return the hex string.
     */
    public static String toHex(final byte[] bytes) {
        return toHex(bytes, 0, bytes.length, false);
    }

    /**
     * Convert the byte array to the hex string. Lower case characters are used for the hex representation.
     *
     * @param bytes       the byte array.
     * @param bytesOffset the offset of the first element in the byte array.
     * @param bytesLength the number of elements in the byte array.
     * @return the hex string.
     */
    public static String toHex(final byte[] bytes, final int bytesOffset, final int bytesLength) {
        return toHex(bytes, bytesOffset, bytesLength, false);
    }

    /**
     * Convert the byte array to the hex string.
     *
     * @param bytes     the byte array.
     * @param upperCase true to use upper case characters for the hex representation, false otherwise.
     * @return the hex string.
     */
    public static String toHex(final byte[] bytes, final boolean upperCase) {
        return toHex(bytes, 0, bytes.length, upperCase);
    }

    /**
     * Convert the byte array to the hex string.
     *
     * @param bytes       the byte array.
     * @param bytesOffset the offset of the first element in the byte array.
     * @param bytesLength the number of elements in the byte array.
     * @param upperCase   true to use upper case characters for the hex representation, false otherwise.
     * @return the hex string.
     */
    public static String toHex(final byte[] bytes, final int bytesOffset, final int bytesLength, final boolean upperCase) {
        if (bytesOffset < 0) {
            throw new HexRuntimeException(ExceptionMessageHelper.createWrongByteArrayIndexMessage(bytesOffset));
        }
        if (bytesLength < 0) {
            throw new HexRuntimeException(ExceptionMessageHelper.createWrongByteArrayLengthMessage(bytesLength));
        }
        if (bytesOffset + bytesLength > bytes.length) {
            throw new HexRuntimeException(ExceptionMessageHelper.createWrongByteArrayIndexMessage(bytesOffset + bytesLength));
        }

        int[] hexUpperCharacters;
        int[] hexLowerCharacters;
        if (upperCase) {
            hexUpperCharacters = Consts.TO_UPPERCASE_HEX_UPPER_CHARACTER;
            hexLowerCharacters = Consts.TO_UPPERCASE_HEX_LOWER_CHARACTER;
        } else {
            hexUpperCharacters = Consts.TO_LOWERCASE_HEX_UPPER_CHARACTER;
            hexLowerCharacters = Consts.TO_LOWERCASE_HEX_LOWER_CHARACTER;
        }

        StringBuilder buffer = new StringBuilder(bytesLength * 2);
        int bytesMaxIndex = bytesOffset + bytesLength;
        int currentByte;
        int upperCharacter;
        int lowerCharacter;
        for (int bytesIndex = bytesOffset; bytesIndex < bytesMaxIndex; bytesIndex++) {
            currentByte = bytes[bytesIndex] & 0xFF;
            upperCharacter = hexUpperCharacters[currentByte];
            buffer.append((char) upperCharacter);
            lowerCharacter = hexLowerCharacters[currentByte];
            buffer.append((char) lowerCharacter);
        }
        return buffer.toString();
    }

    /**
     * Convert the hex string to the byte array.
     *
     * @param hex   the hex string.
     * @param bytes the byte array to write the result.
     * @return the number of bytes affected in the byte array.
     */
    public static int toBytes(final String hex, final byte[] bytes) {
        return toBytes(hex, 0, hex.length(), bytes, 0);
    }

    /**
     * Convert the hex string to the byte array.
     *
     * @param hex       the hex string.
     * @param hexOffset the offset of the first element in the hex string.
     * @param hexLength the number of elements in the hex string.
     * @param bytes     the byte array to write the result.
     * @return the number of bytes affected in the byte array.
     */
    public static int toBytes(final String hex, final int hexOffset, final int hexLength, final byte[] bytes) {
        return toBytes(hex, hexOffset, hexLength, bytes, 0);
    }

    /**
     * Convert the hex string to the byte array.
     *
     * @param hex         the hex string.
     * @param bytes       the byte array to write the result.
     * @param bytesOffset the offset of the first element in the byte array.
     * @return the number of bytes affected in the byte array.
     */
    public static int toBytes(final String hex, final byte[] bytes, final int bytesOffset) {
        return toBytes(hex, 0, hex.length(), bytes, bytesOffset);
    }

    /**
     * Convert the hex string to the byte array.
     *
     * @param hex         the hex string.
     * @param hexOffset   the offset of the first element in the hex string.
     * @param hexLength   the number of elements in the hex string.
     * @param bytes       the byte array to write the result.
     * @param bytesOffset the offset of the first element in the byte array.
     * @return the number of bytes affected in the byte array.
     */
    public static int toBytes(final String hex, final int hexOffset, final int hexLength, final byte[] bytes, final int bytesOffset) {
        if (hexOffset < 0) {
            throw new HexRuntimeException(ExceptionMessageHelper.createWrongHexStringIndexMessage(hexOffset));
        }
        if (hexLength < 0) {
            throw new HexRuntimeException(ExceptionMessageHelper.createWrongHexStringLengthMessage(hexLength));
        }
        if (hexOffset + hexLength > hex.length()) {
            throw new HexRuntimeException(ExceptionMessageHelper.createWrongHexStringIndexMessage(hexOffset + hexLength));
        }
        if (hexLength % 2 != 0) {
            throw new HexRuntimeException(ExceptionMessageHelper.createWrongHexStringLengthMessage(hexLength));
        }
        if (bytesOffset < 0) {
            throw new HexRuntimeException(ExceptionMessageHelper.createWrongByteArrayIndexMessage(bytesOffset));
        }
        if (bytesOffset >= bytes.length) {
            throw new HexRuntimeException(ExceptionMessageHelper.createWrongByteArrayIndexMessage(bytesOffset));
        }

        if (hexLength == 0) {
            return 0;
        } else {
            int bytesLength = hexLength / 2;
            if (bytesOffset + bytesLength > bytes.length) {
                throw new HexRuntimeException(ExceptionMessageHelper.createWrongByteArrayLengthMessage(bytes.length - bytesOffset, bytesLength));
            }
            convertToBytes(hex, hexOffset, hexLength, bytes, bytesOffset);
            return bytesLength;
        }
    }

    /**
     * Convert the hex string to the byte array.
     *
     * @param hex the hex string.
     * @return the byte array with the result.
     */
    public static byte[] toBytes(final String hex) {
        return toBytes(hex, 0, hex.length());
    }

    /**
     * Convert the hex string to the byte array.
     *
     * @param hex       the hex string.
     * @param hexOffset the offset of the first element in the hex string.
     * @param hexLength the number of elements in the hex string.
     * @return the byte array with the result.
     */
    public static byte[] toBytes(final String hex, final int hexOffset, final int hexLength) {
        if (hexOffset < 0) {
            throw new HexRuntimeException(ExceptionMessageHelper.createWrongHexStringIndexMessage(hexOffset));
        }
        if (hexLength < 0) {
            throw new HexRuntimeException(ExceptionMessageHelper.createWrongHexStringLengthMessage(hexLength));
        }
        if (hexOffset + hexLength > hex.length()) {
            throw new HexRuntimeException(ExceptionMessageHelper.createWrongHexStringIndexMessage(hexOffset + hexLength));
        }
        if (hexLength % 2 != 0) {
            throw new HexRuntimeException(ExceptionMessageHelper.createWrongHexStringLengthMessage(hexLength));
        }

        if (hexLength == 0) {
            return new byte[0];
        } else {
            int bytesLength = hexLength / 2;
            byte[] bytes = new byte[bytesLength];
            convertToBytes(hex, hexOffset, hexLength, bytes, 0);
            return bytes;
        }
    }

    private static void convertToBytes(final String hex, final int hexOffset, final int hexLength, final byte[] bytes, final int bytesOffset) {
        int hexIndex = hexOffset;
        int hexMaxIndex = hexOffset + hexLength;
        int currentCharacter1;
        int currentCharacter2;
        int bytesIndex = bytesOffset;
        while (hexIndex < hexMaxIndex) {
            currentCharacter1 = hexCharacterAt(hex, hexIndex);
            hexIndex++;
            currentCharacter2 = hexCharacterAt(hex, hexIndex);
            hexIndex++;

            bytes[bytesIndex] = (byte) (Consts.FROM_HEX_UPPER_BYTE[currentCharacter1] + Consts.FROM_HEX_LOWER_BYTE[currentCharacter2]);
            bytesIndex++;
        }
    }

    private static int hexCharacterAt(final String hex, final int hexIndex) {
        int character = hex.charAt(hexIndex);
        if (isHexCharacterValid(character)) {
            return character;
        } else {
            throw new HexRuntimeException(ExceptionMessageHelper.createWrongHexStringCharacterMessage(character));
        }
    }

    static boolean isHexCharacterValid(final int character) {
        return character >= '0' && character < Consts.FROM_HEX.length && Consts.FROM_HEX[character] >= 0;
    }

    /**
     * Define, whether the hex string contains only the hex characters or not. The string must contain an even number of characters.
     *
     * @param hex the hex string.
     * @return true, if the hex string contains only the hex characters.
     */
    public static boolean isHexString(final String hex) {
        return isHexString(hex, 0, hex.length(), true);
    }

    /**
     * Define, whether the hex string contains only the hex characters or not. The string must contain an even number of characters.
     *
     * @param hex       the hex string.
     * @param hexOffset the offset of the first element in the hex string.
     * @param hexLength the number of elements in the hex string.
     * @return true, if the hex string contains only the hex characters.
     */
    public static boolean isHexString(final String hex, final int hexOffset, final int hexLength) {
        return isHexString(hex, hexOffset, hexLength, true);
    }

    /**
     * Define, whether the hex string contains only the hex characters or not.
     *
     * @param hex       the hex string.
     * @param evenCheck true if the hex string contains should contain an even number of characters, false otherwise.
     * @return true, if the hex string contains only the hex characters.
     */
    public static boolean isHexString(final String hex, final boolean evenCheck) {
        return isHexString(hex, 0, hex.length(), evenCheck);
    }

    /**
     * Define, whether the hex string contains only the hex characters or not.
     *
     * @param hex       the hex string.
     * @param hexOffset the offset of the first element in the hex string.
     * @param hexLength the number of elements in the hex string.
     * @param evenCheck true if the hex string contains should contain an even number of characters, false otherwise.
     * @return true, if the hex string contains only the hex characters.
     */
    public static boolean isHexString(final String hex, final int hexOffset, final int hexLength, final boolean evenCheck) {
        if (hexOffset < 0) {
            throw new HexRuntimeException(ExceptionMessageHelper.createWrongHexStringIndexMessage(hexOffset));
        }
        if (hexLength < 0) {
            throw new HexRuntimeException(ExceptionMessageHelper.createWrongHexStringLengthMessage(hexLength));
        }
        if (hexOffset + hexLength > hex.length()) {
            throw new HexRuntimeException(ExceptionMessageHelper.createWrongHexStringIndexMessage(hexOffset + hexLength));
        }
        if (hexLength == 0) {
            return false;
        }
        if (evenCheck && hexLength % 2 != 0) {
            return false;
        }

        int hexMaxIndex = hexOffset + hexLength;
        int currentCharacter;
        for (int hexIndex = hexOffset; hexIndex < hexMaxIndex; hexIndex++) {
            currentCharacter = hex.charAt(hexIndex);
            if (!isHexCharacterValid(currentCharacter)) {
                return false;
            }
        }
        return true;
    }

}
