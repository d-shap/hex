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

import org.junit.Test;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link HexHelper}.
 *
 * @author Dmitry Shapovalov
 */
public final class HexHelperTest {

    /**
     * Test class constructor.
     */
    public HexHelperTest() {
        super();
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void constructorTest() {
        Assertions.assertThat(HexHelper.class).hasOnePrivateConstructor();
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toHexWithDefaultCaseTest() {
        Assertions.assertThat(HexHelper.toHex(new byte[0])).isEqualTo("");
        Assertions.assertThat(HexHelper.toHex(new byte[]{1, 5, (byte) 140, (byte) 250, -14})).isEqualTo("01058cfaf2");
        Assertions.assertThat(HexHelper.toHex(new byte[]{17, 28, (byte) 179, -14})).isEqualTo("111cb3f2");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94})).isEqualTo("fffab4115e");
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test(expected = NullPointerException.class)
    public void toHexWithDefaultCaseAndNullByteArrayTest() {
        HexHelper.toHex(null);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toHexWithDefaultCaseAndBoundsTest() {
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 0, 0)).isEqualTo("");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 0, 1)).isEqualTo("ff");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 0, 2)).isEqualTo("fffa");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 0, 3)).isEqualTo("fffab4");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 0, 4)).isEqualTo("fffab411");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 0, 5)).isEqualTo("fffab4115e");

        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 1, 0)).isEqualTo("");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 1, 1)).isEqualTo("fa");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 1, 2)).isEqualTo("fab4");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 1, 3)).isEqualTo("fab411");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 1, 4)).isEqualTo("fab4115e");

        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 2, 0)).isEqualTo("");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 2, 1)).isEqualTo("b4");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 2, 2)).isEqualTo("b411");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 2, 3)).isEqualTo("b4115e");

        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 3, 0)).isEqualTo("");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 3, 1)).isEqualTo("11");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 3, 2)).isEqualTo("115e");

        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 4, 0)).isEqualTo("");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 4, 1)).isEqualTo("5e");

        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 5, 0)).isEqualTo("");
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test(expected = NullPointerException.class)
    public void toHexWithDefaultCaseAndBoundsAndNullByteArrayTest() {
        HexHelper.toHex(null, 0, 5);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toHexWithDefaultCaseAndBoundsAndNegativeOffsetTest() {
        try {
            HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, -1, 2);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong byte array index (-1)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toHexWithDefaultCaseAndBoundsAndNegativeLengthTest() {
        try {
            HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 0, -1);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong byte array length (-1)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toHexWithDefaultCaseAndBoundsAndTooLargeLengthTest() {
        try {
            HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 0, 10);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong byte array index (10)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toHexWithUpperCaseTest() {
        Assertions.assertThat(HexHelper.toHex(new byte[0], true)).isEqualTo("");
        Assertions.assertThat(HexHelper.toHex(new byte[]{1, 5, (byte) 140, (byte) 250, -14}, true)).isEqualTo("01058CFAF2");
        Assertions.assertThat(HexHelper.toHex(new byte[]{17, 28, (byte) 179, -14}, true)).isEqualTo("111CB3F2");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, true)).isEqualTo("FFFAB4115E");
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test(expected = NullPointerException.class)
    public void toHexWithUpperCaseAndNullByteArrayTest() {
        HexHelper.toHex(null, true);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toHexWithUpperCaseAndBoundsTest() {
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 0, 0, true)).isEqualTo("");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 0, 1, true)).isEqualTo("FF");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 0, 2, true)).isEqualTo("FFFA");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 0, 3, true)).isEqualTo("FFFAB4");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 0, 4, true)).isEqualTo("FFFAB411");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 0, 5, true)).isEqualTo("FFFAB4115E");

        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 1, 0, true)).isEqualTo("");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 1, 1, true)).isEqualTo("FA");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 1, 2, true)).isEqualTo("FAB4");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 1, 3, true)).isEqualTo("FAB411");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 1, 4, true)).isEqualTo("FAB4115E");

        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 2, 0, true)).isEqualTo("");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 2, 1, true)).isEqualTo("B4");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 2, 2, true)).isEqualTo("B411");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 2, 3, true)).isEqualTo("B4115E");

        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 3, 0, true)).isEqualTo("");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 3, 1, true)).isEqualTo("11");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 3, 2, true)).isEqualTo("115E");

        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 4, 0, true)).isEqualTo("");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 4, 1, true)).isEqualTo("5E");

        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 5, 0, true)).isEqualTo("");
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test(expected = NullPointerException.class)
    public void toHexWithUpperCaseAndBoundsAndNullByteArrayTest() {
        HexHelper.toHex(null, 0, 5, true);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toHexWithUpperCaseAndBoundsAndNegativeOffsetTest() {
        try {
            HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, -1, 2, true);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong byte array index (-1)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toHexWithUpperCaseAndBoundsAndNegativeLengthTest() {
        try {
            HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 0, -1, true);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong byte array length (-1)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toHexWithUpperCaseAndBoundsAndTooLargeLengthTest() {
        try {
            HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 0, 10, true);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong byte array index (10)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toHexWithLowerCaseTest() {
        Assertions.assertThat(HexHelper.toHex(new byte[0], false)).isEqualTo("");
        Assertions.assertThat(HexHelper.toHex(new byte[]{1, 5, (byte) 140, (byte) 250, -14}, false)).isEqualTo("01058cfaf2");
        Assertions.assertThat(HexHelper.toHex(new byte[]{17, 28, (byte) 179, -14}, false)).isEqualTo("111cb3f2");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, false)).isEqualTo("fffab4115e");
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test(expected = NullPointerException.class)
    public void toHexWithLowerCaseAndNullByteArrayTest() {
        HexHelper.toHex(null, false);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toHexWithLowerCaseAndBoundsTest() {
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 0, 0, false)).isEqualTo("");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 0, 1, false)).isEqualTo("ff");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 0, 2, false)).isEqualTo("fffa");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 0, 3, false)).isEqualTo("fffab4");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 0, 4, false)).isEqualTo("fffab411");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 0, 5, false)).isEqualTo("fffab4115e");

        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 1, 0, false)).isEqualTo("");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 1, 1, false)).isEqualTo("fa");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 1, 2, false)).isEqualTo("fab4");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 1, 3, false)).isEqualTo("fab411");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 1, 4, false)).isEqualTo("fab4115e");

        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 2, 0, false)).isEqualTo("");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 2, 1, false)).isEqualTo("b4");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 2, 2, false)).isEqualTo("b411");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 2, 3, false)).isEqualTo("b4115e");

        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 3, 0, false)).isEqualTo("");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 3, 1, false)).isEqualTo("11");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 3, 2, false)).isEqualTo("115e");

        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 4, 0, false)).isEqualTo("");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 4, 1, false)).isEqualTo("5e");

        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 5, 0, false)).isEqualTo("");
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test(expected = NullPointerException.class)
    public void toHexWithLowerCaseAndBoundsAndNullByteArrayTest() {
        HexHelper.toHex(null, 0, 5, false);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toHexWithLowerCaseAndBoundsAndNegativeOffsetTest() {
        try {
            HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, -1, 2, false);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong byte array index (-1)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toHexWithLowerCaseAndBoundsAndNegativeLengthTest() {
        try {
            HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 0, -1, false);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong byte array length (-1)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toHexWithLowerCaseAndBoundsAndTooLargeLengthTest() {
        try {
            HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, 0, 10, false);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong byte array index (10)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void getHexStringLengthTest() {
        Assertions.assertThat(HexHelper.getHexStringLength(0)).isEqualTo(0);
        Assertions.assertThat(HexHelper.getHexStringLength(1)).isEqualTo(2);
        Assertions.assertThat(HexHelper.getHexStringLength(2)).isEqualTo(4);
        Assertions.assertThat(HexHelper.getHexStringLength(3)).isEqualTo(6);
        Assertions.assertThat(HexHelper.getHexStringLength(4)).isEqualTo(8);
        Assertions.assertThat(HexHelper.getHexStringLength(5)).isEqualTo(10);
        Assertions.assertThat(HexHelper.getHexStringLength(6)).isEqualTo(12);
        Assertions.assertThat(HexHelper.getHexStringLength(7)).isEqualTo(14);
        Assertions.assertThat(HexHelper.getHexStringLength(8)).isEqualTo(16);
        Assertions.assertThat(HexHelper.getHexStringLength(9)).isEqualTo(18);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedTest() {
        byte[] bytes1 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("", bytes1)).isEqualTo(0);
        Assertions.assertThat(bytes1).containsExactlyInOrder(0, 0, 0, 0, 0, 0, 0, 0);

        byte[] bytes2 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AC120F", bytes2)).isEqualTo(3);
        Assertions.assertThat(bytes2).containsExactlyInOrder(172, 18, 15, 0, 0, 0, 0, 0);

        byte[] bytes3 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("77A2", bytes3)).isEqualTo(2);
        Assertions.assertThat(bytes3).containsExactlyInOrder(119, 162, 0, 0, 0, 0, 0, 0);

        byte[] bytes4 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("1234567890", bytes4)).isEqualTo(5);
        Assertions.assertThat(bytes4).containsExactlyInOrder(18, 52, 86, 120, 144, 0, 0, 0);

        byte[] bytes5 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", bytes5)).isEqualTo(3);
        Assertions.assertThat(bytes5).containsExactlyInOrder(170, 205, 47, 0, 0, 0, 0, 0);

        byte[] bytes6 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("aacd2f", bytes6)).isEqualTo(3);
        Assertions.assertThat(bytes6).containsExactlyInOrder(170, 205, 47, 0, 0, 0, 0, 0);

        byte[] bytes7 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("aAcD2F", bytes7)).isEqualTo(3);
        Assertions.assertThat(bytes7).containsExactlyInOrder(170, 205, 47, 0, 0, 0, 0, 0);

        byte[] bytes8 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("0102030405060708", bytes8)).isEqualTo(8);
        Assertions.assertThat(bytes8).containsExactlyInOrder(1, 2, 3, 4, 5, 6, 7, 8);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test(expected = NullPointerException.class)
    public void toBytesSpecifiedWithNullStringTest() {
        HexHelper.toBytes(null, new byte[8]);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedWithOddStringLengthTest() {
        try {
            HexHelper.toBytes("AAC", new byte[8]);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string length (3)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test(expected = NullPointerException.class)
    public void toBytesSpecifiedWithNullByteArrayTest() {
        HexHelper.toBytes("AACD2F", null);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedWithInsufficientByteArrayLengthTest() {
        try {
            HexHelper.toBytes("AACD2F", new byte[2]);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong byte array length (2), expected length is (3)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedWithHexBoundsTest() {
        byte[] bytes11 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 0, 0, bytes11)).isEqualTo(0);
        Assertions.assertThat(bytes11).containsExactlyInOrder(0, 0, 0, 0, 0, 0, 0, 0);

        byte[] bytes12 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 0, 2, bytes12)).isEqualTo(1);
        Assertions.assertThat(bytes12).containsExactlyInOrder(170, 0, 0, 0, 0, 0, 0, 0);

        byte[] bytes13 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 0, 4, bytes13)).isEqualTo(2);
        Assertions.assertThat(bytes13).containsExactlyInOrder(170, 205, 0, 0, 0, 0, 0, 0);

        byte[] bytes14 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 0, 6, bytes14)).isEqualTo(3);
        Assertions.assertThat(bytes14).containsExactlyInOrder(170, 205, 47, 0, 0, 0, 0, 0);

        byte[] bytes21 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 2, 0, bytes21)).isEqualTo(0);
        Assertions.assertThat(bytes21).containsExactlyInOrder(0, 0, 0, 0, 0, 0, 0, 0);

        byte[] bytes22 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 2, 2, bytes22)).isEqualTo(1);
        Assertions.assertThat(bytes22).containsExactlyInOrder(205, 0, 0, 0, 0, 0, 0, 0);

        byte[] bytes23 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 2, 4, bytes23)).isEqualTo(2);
        Assertions.assertThat(bytes23).containsExactlyInOrder(205, 47, 0, 0, 0, 0, 0, 0);

        byte[] bytes31 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 4, 0, bytes31)).isEqualTo(0);
        Assertions.assertThat(bytes31).containsExactlyInOrder(0, 0, 0, 0, 0, 0, 0, 0);

        byte[] bytes32 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 4, 2, bytes32)).isEqualTo(1);
        Assertions.assertThat(bytes32).containsExactlyInOrder(47, 0, 0, 0, 0, 0, 0, 0);

        byte[] bytes41 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 6, 0, bytes41)).isEqualTo(0);
        Assertions.assertThat(bytes41).containsExactlyInOrder(0, 0, 0, 0, 0, 0, 0, 0);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test(expected = NullPointerException.class)
    public void toBytesSpecifiedWithHexBoundsAndNullStringTest() {
        HexHelper.toBytes(null, 2, 2, new byte[8]);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedWithHexBoundsAndOddStringLengthTest() {
        try {
            HexHelper.toBytes("AACD2F", 2, 3, new byte[8]);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string length (3)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedWithHexBoundsAndNegativeOffsetTest() {
        try {
            HexHelper.toBytes("AACD2F", -1, 2, new byte[10]);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string index (-1)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedWithHexBoundsAndNegativeLengthTest() {
        try {
            HexHelper.toBytes("AACD2F", 0, -1, new byte[8]);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string length (-1)");
        }
        try {
            HexHelper.toBytes("AACD2F", 0, -2, new byte[8]);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string length (-2)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedWithHexBoundsAndTooLargeLengthTest() {
        try {
            HexHelper.toBytes("AACD2F", 0, 7, new byte[10]);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string index (7)");
        }
        try {
            HexHelper.toBytes("AACD2F", 0, 8, new byte[10]);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string index (8)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test(expected = NullPointerException.class)
    public void toBytesSpecifiedWithHexBoundsAndNullByteArrayTest() {
        HexHelper.toBytes("AACD2F", 2, 2, null);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedWithHexBoundsAndInsufficientByteArrayLengthTest() {
        try {
            HexHelper.toBytes("AACD2F", 1, 4, new byte[1]);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong byte array length (1), expected length is (2)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedWithByteArrayOffsetTest() {
        byte[] bytes1 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", bytes1, 0)).isEqualTo(3);
        Assertions.assertThat(bytes1).containsExactlyInOrder(170, 205, 47, 0, 0, 0, 0, 0);

        byte[] bytes2 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", bytes2, 1)).isEqualTo(3);
        Assertions.assertThat(bytes2).containsExactlyInOrder(0, 170, 205, 47, 0, 0, 0, 0);

        byte[] bytes3 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", bytes3, 2)).isEqualTo(3);
        Assertions.assertThat(bytes3).containsExactlyInOrder(0, 0, 170, 205, 47, 0, 0, 0);

        byte[] bytes4 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", bytes4, 3)).isEqualTo(3);
        Assertions.assertThat(bytes4).containsExactlyInOrder(0, 0, 0, 170, 205, 47, 0, 0);

        byte[] bytes5 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", bytes5, 4)).isEqualTo(3);
        Assertions.assertThat(bytes5).containsExactlyInOrder(0, 0, 0, 0, 170, 205, 47, 0);

        byte[] bytes6 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", bytes6, 5)).isEqualTo(3);
        Assertions.assertThat(bytes6).containsExactlyInOrder(0, 0, 0, 0, 0, 170, 205, 47);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test(expected = NullPointerException.class)
    public void toBytesSpecifiedWithByteArrayOffsetAndNullStringTest() {
        HexHelper.toBytes(null, new byte[8], 2);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedWithByteArrayOffsetAndOddStringLengthTest() {
        try {
            HexHelper.toBytes("AAC", new byte[8], 2);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string length (3)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test(expected = NullPointerException.class)
    public void toBytesSpecifiedWithByteArrayOffsetAndNullByteArrayTest() {
        HexHelper.toBytes("AACD2F", null, 2);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedWithByteArrayOffsetAndInsufficientByteArrayLengthTest() {
        try {
            HexHelper.toBytes("AACD2F", new byte[8], 6);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong byte array length (2), expected length is (3)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedWithByteArrayOffsetAndNegativeByteArrayOffsetTest() {
        try {
            HexHelper.toBytes("AACD2F", new byte[8], -1);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong byte array index (-1)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedWithByteArrayOffsetAndTooLargeByteArrayOffsetTest() {
        try {
            HexHelper.toBytes("AACD2F", new byte[8], 10);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong byte array index (10)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedWithByteArrayOffsetAndUpperBoundTest() {
        byte[] bytes1 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("", bytes1, 8)).isEqualTo(0);
        Assertions.assertThat(bytes1).containsExactlyInOrder(0, 0, 0, 0, 0, 0, 0, 0);

        try {
            HexHelper.toBytes("", new byte[8], 9);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong byte array index (9)");
        }

        try {
            HexHelper.toBytes("aa", new byte[8], 8);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong byte array length (0), expected length is (1)");
        }

        byte[] bytes2 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("aa", bytes2, 7)).isEqualTo(1);
        Assertions.assertThat(bytes2).containsExactlyInOrder(0, 0, 0, 0, 0, 0, 0, 170);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedWithHexBoundsAndByteArrayOffsetTest() {
        byte[] bytes11 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 0, 0, bytes11, 0)).isEqualTo(0);
        Assertions.assertThat(bytes11).containsExactlyInOrder(0, 0, 0, 0, 0, 0, 0, 0);

        byte[] bytes12 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 0, 2, bytes12, 0)).isEqualTo(1);
        Assertions.assertThat(bytes12).containsExactlyInOrder(170, 0, 0, 0, 0, 0, 0, 0);

        byte[] bytes13 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 0, 4, bytes13, 0)).isEqualTo(2);
        Assertions.assertThat(bytes13).containsExactlyInOrder(170, 205, 0, 0, 0, 0, 0, 0);

        byte[] bytes14 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 0, 6, bytes14, 0)).isEqualTo(3);
        Assertions.assertThat(bytes14).containsExactlyInOrder(170, 205, 47, 0, 0, 0, 0, 0);

        byte[] bytes21 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 2, 0, bytes21, 0)).isEqualTo(0);
        Assertions.assertThat(bytes21).containsExactlyInOrder(0, 0, 0, 0, 0, 0, 0, 0);

        byte[] bytes22 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 2, 2, bytes22, 0)).isEqualTo(1);
        Assertions.assertThat(bytes22).containsExactlyInOrder(205, 0, 0, 0, 0, 0, 0, 0);

        byte[] bytes23 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 2, 4, bytes23, 0)).isEqualTo(2);
        Assertions.assertThat(bytes23).containsExactlyInOrder(205, 47, 0, 0, 0, 0, 0, 0);

        byte[] bytes31 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 0, 0, bytes31, 1)).isEqualTo(0);
        Assertions.assertThat(bytes31).containsExactlyInOrder(0, 0, 0, 0, 0, 0, 0, 0);

        byte[] bytes32 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 0, 2, bytes32, 1)).isEqualTo(1);
        Assertions.assertThat(bytes32).containsExactlyInOrder(0, 170, 0, 0, 0, 0, 0, 0);

        byte[] bytes33 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 0, 4, bytes33, 1)).isEqualTo(2);
        Assertions.assertThat(bytes33).containsExactlyInOrder(0, 170, 205, 0, 0, 0, 0, 0);

        byte[] bytes34 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 0, 6, bytes34, 1)).isEqualTo(3);
        Assertions.assertThat(bytes34).containsExactlyInOrder(0, 170, 205, 47, 0, 0, 0, 0);

        byte[] bytes41 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 0, 0, bytes41, 3)).isEqualTo(0);
        Assertions.assertThat(bytes41).containsExactlyInOrder(0, 0, 0, 0, 0, 0, 0, 0);

        byte[] bytes42 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 0, 2, bytes42, 3)).isEqualTo(1);
        Assertions.assertThat(bytes42).containsExactlyInOrder(0, 0, 0, 170, 0, 0, 0, 0);

        byte[] bytes43 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 0, 4, bytes43, 3)).isEqualTo(2);
        Assertions.assertThat(bytes43).containsExactlyInOrder(0, 0, 0, 170, 205, 0, 0, 0);

        byte[] bytes44 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 0, 6, bytes44, 3)).isEqualTo(3);
        Assertions.assertThat(bytes44).containsExactlyInOrder(0, 0, 0, 170, 205, 47, 0, 0);

        byte[] bytes51 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 0, 2, bytes51, 7)).isEqualTo(1);
        Assertions.assertThat(bytes51).containsExactlyInOrder(0, 0, 0, 0, 0, 0, 0, 170);

        byte[] bytes52 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 0, 4, bytes52, 6)).isEqualTo(2);
        Assertions.assertThat(bytes52).containsExactlyInOrder(0, 0, 0, 0, 0, 0, 170, 205);

        byte[] bytes53 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("AACD2F", 0, 6, bytes53, 5)).isEqualTo(3);
        Assertions.assertThat(bytes53).containsExactlyInOrder(0, 0, 0, 0, 0, 170, 205, 47);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test(expected = NullPointerException.class)
    public void toBytesSpecifiedWithHexBoundsAndByteArrayOffsetAndNullStringTest() {
        HexHelper.toBytes(null, 2, 2, new byte[8], 1);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedWithHexBoundsAndByteArrayOffsetAndOddStringLengthTest() {
        try {
            HexHelper.toBytes("AACD2F", 2, 3, new byte[8], 1);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string length (3)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedWithHexBoundsAndByteArrayOffsetAndNegativeOffsetTest() {
        try {
            HexHelper.toBytes("AACD2F", -1, 2, new byte[10], 1);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string index (-1)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedWithHexBoundsAndByteArrayOffsetAndNegativeLengthTest() {
        try {
            HexHelper.toBytes("AACD2F", 0, -1, new byte[8], 1);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string length (-1)");
        }
        try {
            HexHelper.toBytes("AACD2F", 0, -2, new byte[8], 1);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string length (-2)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedWithHexBoundsAndByteArrayOffsetAndTooLargeLengthTest() {
        try {
            HexHelper.toBytes("AACD2F", 0, 7, new byte[10], 1);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string index (7)");
        }
        try {
            HexHelper.toBytes("AACD2F", 0, 8, new byte[10], 1);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string index (8)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test(expected = NullPointerException.class)
    public void toBytesSpecifiedWithHexBoundsAndByteArrayOffsetAndNullByteArrayTest() {
        HexHelper.toBytes("AACD2F", 2, 2, null, 1);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedWithHexBoundsAndByteArrayOffsetAndInsufficientByteArrayLengthTest() {
        try {
            HexHelper.toBytes("AACD2F", 1, 4, new byte[2], 1);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong byte array length (1), expected length is (2)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedWithHexBoundsAndByteArrayOffsetAndNegativeByteArrayOffsetTest() {
        try {
            HexHelper.toBytes("AACD2F", 2, 4, new byte[8], -1);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong byte array index (-1)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedWithHexBoundsAndByteArrayOffsetAndTooLargeByteArrayOffsetTest() {
        try {
            HexHelper.toBytes("AACD2F", 2, 4, new byte[8], 10);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong byte array index (10)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedWithHexBoundsAndByteArrayOffsetAndUpperBoundTest() {
        byte[] bytes1 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("", 0, 0, bytes1, 8)).isEqualTo(0);
        Assertions.assertThat(bytes1).containsExactlyInOrder(0, 0, 0, 0, 0, 0, 0, 0);

        byte[] bytes2 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("aabbcc", 0, 0, bytes2, 8)).isEqualTo(0);
        Assertions.assertThat(bytes2).containsExactlyInOrder(0, 0, 0, 0, 0, 0, 0, 0);

        byte[] bytes3 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("aabbcc", 2, 0, bytes3, 8)).isEqualTo(0);
        Assertions.assertThat(bytes3).containsExactlyInOrder(0, 0, 0, 0, 0, 0, 0, 0);

        byte[] bytes4 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("aabbcc", 5, 0, bytes4, 8)).isEqualTo(0);
        Assertions.assertThat(bytes4).containsExactlyInOrder(0, 0, 0, 0, 0, 0, 0, 0);

        try {
            HexHelper.toBytes("", 0, 0, new byte[8], 9);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong byte array index (9)");
        }

        try {
            HexHelper.toBytes("aa", 0, 2, new byte[8], 8);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong byte array length (0), expected length is (1)");
        }

        byte[] bytes5 = new byte[8];
        Assertions.assertThat(HexHelper.toBytes("aa", 0, 2, bytes5, 7)).isEqualTo(1);
        Assertions.assertThat(bytes5).containsExactlyInOrder(0, 0, 0, 0, 0, 0, 0, 170);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesCreatedTest() {
        byte[] bytes1 = HexHelper.toBytes("");
        Assertions.assertThat(bytes1).containsExactlyInOrder();

        byte[] bytes2 = HexHelper.toBytes("AC120F");
        Assertions.assertThat(bytes2).containsExactlyInOrder(172, 18, 15);

        byte[] bytes3 = HexHelper.toBytes("77A2");
        Assertions.assertThat(bytes3).containsExactlyInOrder(119, 162);

        byte[] bytes4 = HexHelper.toBytes("1234567890");
        Assertions.assertThat(bytes4).containsExactlyInOrder(18, 52, 86, 120, 144);

        byte[] bytes5 = HexHelper.toBytes("AACD2F");
        Assertions.assertThat(bytes5).containsExactlyInOrder(170, 205, 47);

        byte[] bytes6 = HexHelper.toBytes("aacd2f");
        Assertions.assertThat(bytes6).containsExactlyInOrder(170, 205, 47);

        byte[] bytes7 = HexHelper.toBytes("aAcD2F");
        Assertions.assertThat(bytes7).containsExactlyInOrder(170, 205, 47);

        byte[] bytes8 = HexHelper.toBytes("0102030405060708");
        Assertions.assertThat(bytes8).containsExactlyInOrder(1, 2, 3, 4, 5, 6, 7, 8);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test(expected = NullPointerException.class)
    public void toBytesCreatedWithNullStringTest() {
        HexHelper.toBytes(null);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesCreatedWithOddStringLengthTest() {
        try {
            HexHelper.toBytes("AAC");
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string length (3)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesCreatedWithHexBoundsTest() {
        byte[] bytes11 = HexHelper.toBytes("AACD2F", 0, 0);
        Assertions.assertThat(bytes11).containsExactlyInOrder();

        byte[] bytes12 = HexHelper.toBytes("AACD2F", 0, 2);
        Assertions.assertThat(bytes12).containsExactlyInOrder(170);

        byte[] bytes13 = HexHelper.toBytes("AACD2F", 0, 4);
        Assertions.assertThat(bytes13).containsExactlyInOrder(170, 205);

        byte[] bytes14 = HexHelper.toBytes("AACD2F", 0, 6);
        Assertions.assertThat(bytes14).containsExactlyInOrder(170, 205, 47);

        byte[] bytes21 = HexHelper.toBytes("AACD2F", 2, 0);
        Assertions.assertThat(bytes21).containsExactlyInOrder();

        byte[] bytes22 = HexHelper.toBytes("AACD2F", 2, 2);
        Assertions.assertThat(bytes22).containsExactlyInOrder(205);

        byte[] bytes23 = HexHelper.toBytes("AACD2F", 2, 4);
        Assertions.assertThat(bytes23).containsExactlyInOrder(205, 47);

        byte[] bytes31 = HexHelper.toBytes("AACD2F", 4, 0);
        Assertions.assertThat(bytes31).containsExactlyInOrder();

        byte[] bytes32 = HexHelper.toBytes("AACD2F", 4, 2);
        Assertions.assertThat(bytes32).containsExactlyInOrder(47);

        byte[] bytes41 = HexHelper.toBytes("AACD2F", 6, 0);
        Assertions.assertThat(bytes41).containsExactlyInOrder();
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test(expected = NullPointerException.class)
    public void toBytesCreatedWithHexBoundsAndNullStringTest() {
        HexHelper.toBytes(null, 2, 2);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesCreatedWithHexBoundsAndOddStringLengthTest() {
        try {
            HexHelper.toBytes("AACD2F", 2, 3);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string length (3)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesCreatedWithHexBoundsAndNegativeOffsetTest() {
        try {
            HexHelper.toBytes("AACD2F", -1, 2);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string index (-1)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesCreatedWithHexBoundsAndNegativeLengthTest() {
        try {
            HexHelper.toBytes("AACD2F", 0, -1);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string length (-1)");
        }
        try {
            HexHelper.toBytes("AACD2F", 0, -2);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string length (-2)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesCreatedWithHexBoundsAndTooLargeLengthTest() {
        try {
            HexHelper.toBytes("AACD2F", 0, 7);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string index (7)");
        }
        try {
            HexHelper.toBytes("AACD2F", 0, 8);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string index (8)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesWrongCharacterTest() {
        try {
            HexHelper.toBytes("defg12");
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong character obtained ('g', 103)");
        }
        try {
            HexHelper.toBytes("dehf12");
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong character obtained ('h', 104)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void isHexCharacterValidTest() {
        Assertions.assertThat(HexHelper.isHexCharacterValid('0')).isTrue();
        Assertions.assertThat(HexHelper.isHexCharacterValid('1')).isTrue();
        Assertions.assertThat(HexHelper.isHexCharacterValid('2')).isTrue();
        Assertions.assertThat(HexHelper.isHexCharacterValid('3')).isTrue();
        Assertions.assertThat(HexHelper.isHexCharacterValid('4')).isTrue();
        Assertions.assertThat(HexHelper.isHexCharacterValid('5')).isTrue();
        Assertions.assertThat(HexHelper.isHexCharacterValid('6')).isTrue();
        Assertions.assertThat(HexHelper.isHexCharacterValid('7')).isTrue();
        Assertions.assertThat(HexHelper.isHexCharacterValid('8')).isTrue();
        Assertions.assertThat(HexHelper.isHexCharacterValid('9')).isTrue();
        Assertions.assertThat(HexHelper.isHexCharacterValid('a')).isTrue();
        Assertions.assertThat(HexHelper.isHexCharacterValid('A')).isTrue();
        Assertions.assertThat(HexHelper.isHexCharacterValid('b')).isTrue();
        Assertions.assertThat(HexHelper.isHexCharacterValid('B')).isTrue();
        Assertions.assertThat(HexHelper.isHexCharacterValid('c')).isTrue();
        Assertions.assertThat(HexHelper.isHexCharacterValid('C')).isTrue();
        Assertions.assertThat(HexHelper.isHexCharacterValid('d')).isTrue();
        Assertions.assertThat(HexHelper.isHexCharacterValid('D')).isTrue();
        Assertions.assertThat(HexHelper.isHexCharacterValid('e')).isTrue();
        Assertions.assertThat(HexHelper.isHexCharacterValid('E')).isTrue();
        Assertions.assertThat(HexHelper.isHexCharacterValid('f')).isTrue();
        Assertions.assertThat(HexHelper.isHexCharacterValid('F')).isTrue();

        Assertions.assertThat(HexHelper.isHexCharacterValid(0)).isFalse();
        Assertions.assertThat(HexHelper.isHexCharacterValid(-1)).isFalse();
        Assertions.assertThat(HexHelper.isHexCharacterValid(-2)).isFalse();
        Assertions.assertThat(HexHelper.isHexCharacterValid(12)).isFalse();
        Assertions.assertThat(HexHelper.isHexCharacterValid(103)).isFalse();
        Assertions.assertThat(HexHelper.isHexCharacterValid(120)).isFalse();
        Assertions.assertThat(HexHelper.isHexCharacterValid(500)).isFalse();
        Assertions.assertThat(HexHelper.isHexCharacterValid('g')).isFalse();
        Assertions.assertThat(HexHelper.isHexCharacterValid('G')).isFalse();
        Assertions.assertThat(HexHelper.isHexCharacterValid('0' - 1)).isFalse();
        Assertions.assertThat(HexHelper.isHexCharacterValid('f' + 1)).isFalse();
        Assertions.assertThat(HexHelper.isHexCharacterValid('F' + 1)).isFalse();
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void isHexStringTest() {
        Assertions.assertThat(HexHelper.isHexString("")).isFalse();
        Assertions.assertThat(HexHelper.isHexString("Ab12")).isTrue();
        Assertions.assertThat(HexHelper.isHexString("53F15C94e2e4")).isTrue();
        Assertions.assertThat(HexHelper.isHexString("1234567890ABcdEf")).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2f")).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2")).isFalse();
        Assertions.assertThat(HexHelper.isHexString("12AceG")).isFalse();
        Assertions.assertThat(HexHelper.isHexString("6f4-")).isFalse();
        Assertions.assertThat(HexHelper.isHexString("+183")).isFalse();
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test(expected = NullPointerException.class)
    public void isHexStringWithNullStringTest() {
        HexHelper.isHexString(null);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void isHexStringWithBoundsTest() {
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 0, 0)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 0, 1)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 0, 2)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 0, 3)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 0, 4)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 0, 5)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 0, 6)).isTrue();

        Assertions.assertThat(HexHelper.isHexString("AacD2f", 1, 0)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 1, 1)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 1, 2)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 1, 3)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 1, 4)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 1, 5)).isFalse();

        Assertions.assertThat(HexHelper.isHexString("AacD2f", 2, 0)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 2, 1)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 2, 2)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 2, 3)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 2, 4)).isTrue();

        Assertions.assertThat(HexHelper.isHexString("AacD2f", 3, 0)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 3, 1)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 3, 2)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 3, 3)).isFalse();

        Assertions.assertThat(HexHelper.isHexString("AacD2f", 4, 0)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 4, 1)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 4, 2)).isTrue();

        Assertions.assertThat(HexHelper.isHexString("AacD2f", 5, 0)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 5, 1)).isFalse();

        Assertions.assertThat(HexHelper.isHexString("AacD2f", 6, 0)).isFalse();
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test(expected = NullPointerException.class)
    public void isHexStringWithBoundsAndNullStringTest() {
        HexHelper.isHexString(null, 0, 4);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void isHexStringWithBoundsAndNegativeOffsetTest() {
        try {
            HexHelper.isHexString("AacD2f", -1, 4);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string index (-1)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void isHexStringWithBoundsAndNegativeLengthTest() {
        try {
            HexHelper.isHexString("AacD2f", 0, -1);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string length (-1)");
        }
        try {
            HexHelper.isHexString("AacD2f", 0, -2);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string length (-2)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void isHexStringWithBoundsAndTooLargeLengthTest() {
        try {
            HexHelper.isHexString("AacD2f", 0, 9);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string index (9)");
        }
        try {
            HexHelper.isHexString("AacD2f", 0, 10);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string index (10)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void isHexStringWithNoEvenCheckTest() {
        Assertions.assertThat(HexHelper.isHexString("", false)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("Ab12", false)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("53F15C94e2e4", false)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("1234567890ABcdEf", false)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", false)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2", false)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("12AceG", false)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("6f4-", false)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("+183", false)).isFalse();
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test(expected = NullPointerException.class)
    public void isHexStringWithNoEvenCheckAndNullStringTest() {
        HexHelper.isHexString(null, false);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void isHexStringWithNoEvenCheckAndBoundsTest() {
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 0, 0, false)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 0, 1, false)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 0, 2, false)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 0, 3, false)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 0, 4, false)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 0, 5, false)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 0, 6, false)).isTrue();

        Assertions.assertThat(HexHelper.isHexString("AacD2f", 1, 0, false)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 1, 1, false)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 1, 2, false)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 1, 3, false)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 1, 4, false)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 1, 5, false)).isTrue();

        Assertions.assertThat(HexHelper.isHexString("AacD2f", 2, 0, false)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 2, 1, false)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 2, 2, false)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 2, 3, false)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 2, 4, false)).isTrue();

        Assertions.assertThat(HexHelper.isHexString("AacD2f", 3, 0, false)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 3, 1, false)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 3, 2, false)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 3, 3, false)).isTrue();

        Assertions.assertThat(HexHelper.isHexString("AacD2f", 4, 0, false)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 4, 1, false)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 4, 2, false)).isTrue();

        Assertions.assertThat(HexHelper.isHexString("AacD2f", 5, 0, false)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("AacD2f", 5, 1, false)).isTrue();

        Assertions.assertThat(HexHelper.isHexString("AacD2f", 6, 0, false)).isFalse();
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test(expected = NullPointerException.class)
    public void isHexStringWithNoEvenCheckAndBoundsAndNullStringTest() {
        HexHelper.isHexString(null, 0, 4, false);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void isHexStringWithNoEvenCheckAndBoundsAndNegativeOffsetTest() {
        try {
            HexHelper.isHexString("AacD2f", -1, 4, false);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string index (-1)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void isHexStringWithNoEvenCheckAndBoundsAndNegativeLengthTest() {
        try {
            HexHelper.isHexString("AacD2f", 0, -1, false);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string length (-1)");
        }
        try {
            HexHelper.isHexString("AacD2f", 0, -2, false);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string length (-2)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void isHexStringWithNoEvenCheckAndBoundsAndTooLargeLengthTest() {
        try {
            HexHelper.isHexString("AacD2f", 0, 9, false);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string index (9)");
        }
        try {
            HexHelper.isHexString("AacD2f", 0, 10, false);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong hex string index (10)");
        }
    }

}
