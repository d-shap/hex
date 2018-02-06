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
/**
 * <p>
 * Hex library converts bytes to the hex representation and vice versa.
 * </p>
 * <p>
 * {@link ru.d_shap.hex.HexHelper} class contains methods to convert the byte array to the hex string,
 * to convert the hex string to the byte array and to check, whether the string is a hex representation
 * of bytes or not.
 * </p>
 * <p>
 * An example, how to convert the byte array to the hex string:
 * </p>
 * <pre>{@code
 * String str1 = HexHelper.toHex(new byte[]{1, 5, (byte) 140, (byte) 250, -14}); // str1 = "01058cfaf2"
 * String str2 = HexHelper.toHex(new byte[]{17, 28, (byte) 179, -14}); // str2 = "111cb3f2"
 * String str3 = HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}); // str3 = "fffab4115e"
 * }</pre>
 * <p>
 * An example, how to convert the hex string to the byte array:
 * </p>
 * <pre>{@code
 * byte[] bytes1 = HexHelper.toBytes("AC120F"); // bytes1 = new byte[]{(byte) 172, 18, 15}
 * byte[] bytes2 = HexHelper.toBytes("AACD2F"); // bytes2 = new byte[]{(byte) 170, (byte) 205, 47}
 * byte[] bytes3 = HexHelper.toBytes("aacd2f"); // bytes3 = new byte[]{(byte) 170, (byte) 205, 47}
 * }</pre>
 * <p>
 * {@link ru.d_shap.hex.HexHelper} class contains all data in memory. For the large data (for example, big files)
 * this is not efficient. In this case {@link ru.d_shap.hex.HexInputStream} and {@link ru.d_shap.hex.HexOutputStream}
 * classes can be used. {@link ru.d_shap.hex.HexInputStream} reads the stream of the hex characters and translates
 * them to the bytes. {@link ru.d_shap.hex.HexOutputStream} translates the bytes to the hex characters and
 * writes them to the stream.
 * </p>
 * <p>
 * An example, how to write the hex characters to the file:
 * </p>
 * <pre>{@code
 * try (FileInputStream inputStream = new FileInputStream("some input file");
 *      HexOutputStream outputStream = new HexOutputStream(new FileOutputStream("some output file"))) {
 *     int b;
 *     while (true) {
 *         b = inputStream.read();
 *         if (b < 0) {
 *             break;
 *         }
 *         outputStream.write(b);
 *     }
 * }
 * }</pre>
 * <p>
 * Each byte is represented with two hex characters. The hex representation of bytes increases the original
 * size twice.
 * </p>
 */
package ru.d_shap.hex;
