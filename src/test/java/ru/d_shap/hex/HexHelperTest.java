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
    public void toHexUpperCaseTest() {
        //      Assertions.assertThat(HexHelper.toHex(null, true)).isEqualTo("");
        Assertions.assertThat(HexHelper.toHex(new byte[0], true)).isEqualTo("");
        Assertions.assertThat(HexHelper.toHex(new byte[]{1, 5, (byte) 140, (byte) 250, -14}, true)).isEqualTo("01058CFAF2");
        Assertions.assertThat(HexHelper.toHex(new byte[]{17, 28, (byte) 179, -14}, true)).isEqualTo("111CB3F2");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, true)).isEqualTo("FFFAB4115E");
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toHexLowerCaseTest() {
        //    Assertions.assertThat(HexHelper.toHex(null, false)).isEqualTo("");
        Assertions.assertThat(HexHelper.toHex(new byte[0], false)).isEqualTo("");
        Assertions.assertThat(HexHelper.toHex(new byte[]{1, 5, (byte) 140, (byte) 250, -14}, false)).isEqualTo("01058cfaf2");
        Assertions.assertThat(HexHelper.toHex(new byte[]{17, 28, (byte) 179, -14}, false)).isEqualTo("111cb3f2");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, false)).isEqualTo("fffab4115e");
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toHexDefaultCaseTest() {
        // Assertions.assertThat(HexHelper.toHex(null)).isEqualTo("");
        Assertions.assertThat(HexHelper.toHex(new byte[0])).isEqualTo("");
        Assertions.assertThat(HexHelper.toHex(new byte[]{1, 5, (byte) 140, (byte) 250, -14})).isEqualTo("01058cfaf2");
        Assertions.assertThat(HexHelper.toHex(new byte[]{17, 28, (byte) 179, -14})).isEqualTo("111cb3f2");
        Assertions.assertThat(HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94})).isEqualTo("fffab4115e");
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedTest() {
        byte[] bytes = new byte[8];

        Assertions.assertThat(HexHelper.toBytes("AC120F", bytes)).isEqualTo(3);
        Assertions.assertThat(bytes).containsExactlyInOrder(172, 18, 15, 0, 0, 0, 0, 0);

        Assertions.assertThat(HexHelper.toBytes("77A2", bytes)).isEqualTo(2);
        Assertions.assertThat(bytes).containsExactlyInOrder(119, 162, 15, 0, 0, 0, 0, 0);

        Assertions.assertThat(HexHelper.toBytes("1234567890", bytes)).isEqualTo(5);
        Assertions.assertThat(bytes).containsExactlyInOrder(18, 52, 86, 120, 144, 0, 0, 0);

        Assertions.assertThat(HexHelper.toBytes("AACD2F", bytes)).isEqualTo(3);
        Assertions.assertThat(bytes).containsExactlyInOrder(170, 205, 47, 120, 144, 0, 0, 0);

        Assertions.assertThat(HexHelper.toBytes("aacd2f", bytes)).isEqualTo(3);
        Assertions.assertThat(bytes).containsExactlyInOrder(170, 205, 47, 120, 144, 0, 0, 0);

        Assertions.assertThat(HexHelper.toBytes("aAcD2F", bytes)).isEqualTo(3);
        Assertions.assertThat(bytes).containsExactlyInOrder(170, 205, 47, 120, 144, 0, 0, 0);

        Assertions.assertThat(HexHelper.toBytes("0102030405060708", bytes)).isEqualTo(8);
        Assertions.assertThat(bytes).containsExactlyInOrder(1, 2, 3, 4, 5, 6, 7, 8);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedEmptyHexTest() {
        // Assertions.assertThat(HexHelper.toBytes(null, new byte[8])).isEqualTo(0);
        Assertions.assertThat(HexHelper.toBytes("", new byte[8])).isEqualTo(0);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedOddHexLengthTest() {
        try {
            HexHelper.toBytes("abc", new byte[8]);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong number of characters in the hex string (3)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedInsufficientResultLengthTest() {
        try {
            byte[] bytes = new byte[3];
            HexHelper.toBytes("aabc3f72", bytes);
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Result array is too small for the hex string (3), expected size is (4)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesCreatedTest() {
        byte[] bytes;

        bytes = HexHelper.toBytes("AC120F");
        Assertions.assertThat(bytes).containsExactlyInOrder(172, 18, 15);

        bytes = HexHelper.toBytes("77A2");
        Assertions.assertThat(bytes).containsExactlyInOrder(119, 162);

        bytes = HexHelper.toBytes("1234567890");
        Assertions.assertThat(bytes).containsExactlyInOrder(18, 52, 86, 120, 144);

        bytes = HexHelper.toBytes("AACD2F");
        Assertions.assertThat(bytes).containsExactlyInOrder(170, 205, 47);

        bytes = HexHelper.toBytes("aacd2f");
        Assertions.assertThat(bytes).containsExactlyInOrder(170, 205, 47);

        bytes = HexHelper.toBytes("aAcD2F");
        Assertions.assertThat(bytes).containsExactlyInOrder(170, 205, 47);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesCreatedEmptyHexTest() {
        //  Assertions.assertThat(HexHelper.toBytes(null)).containsExactlyInOrder();
        Assertions.assertThat(HexHelper.toBytes("")).containsExactlyInOrder();
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesCreatedOddHexLengthTest() {
        try {
            HexHelper.toBytes("abc");
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong number of characters in the hex string (3)");
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesWrongSymbolTest() {
        try {
            HexHelper.toBytes("defg12");
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong character obtained: 'g' (103)");
        }
        try {
            HexHelper.toBytes("dehf12");
            Assertions.fail("HexHelper test fail");
        } catch (HexRuntimeException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong character obtained: 'h' (104)");
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
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void isHexUpperCaseStringTest() {
        Assertions.assertThat(HexHelper.isHexString("AB12")).isTrue();
        Assertions.assertThat(HexHelper.isHexString("53F15C94E2E4")).isTrue();
        Assertions.assertThat(HexHelper.isHexString("1234567890ABCDEF")).isTrue();
        Assertions.assertThat(HexHelper.isHexString("12ACEG")).isFalse();
        Assertions.assertThat(HexHelper.isHexString("6F4-")).isFalse();
        Assertions.assertThat(HexHelper.isHexString("+183")).isFalse();
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void isHexLowerCaseStringTest() {
        Assertions.assertThat(HexHelper.isHexString("ab12")).isTrue();
        Assertions.assertThat(HexHelper.isHexString("53f15c94e2e4")).isTrue();
        Assertions.assertThat(HexHelper.isHexString("1234567890abcdef")).isTrue();
        Assertions.assertThat(HexHelper.isHexString("12aceg")).isFalse();
        Assertions.assertThat(HexHelper.isHexString("6f4-")).isFalse();
        Assertions.assertThat(HexHelper.isHexString("+183")).isFalse();
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void isHexBothCaseStringTest() {
        Assertions.assertThat(HexHelper.isHexString("Ab12")).isTrue();
        Assertions.assertThat(HexHelper.isHexString("53F15C94e2e4")).isTrue();
        Assertions.assertThat(HexHelper.isHexString("1234567890ABcdEf")).isTrue();
        Assertions.assertThat(HexHelper.isHexString("12AceG")).isFalse();
        Assertions.assertThat(HexHelper.isHexString("6f4-")).isFalse();
        Assertions.assertThat(HexHelper.isHexString("+183")).isFalse();
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void isHexEmptyStringTest() {
        // Assertions.assertThat(HexHelper.isHexString(null)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("")).isFalse();
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void isHexOddLengthStringTest() {
        Assertions.assertThat(HexHelper.isHexString("123")).isFalse();
        Assertions.assertThat(HexHelper.isHexString("123", true)).isFalse();
        Assertions.assertThat(HexHelper.isHexString("123", false)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("1234", true)).isTrue();
        Assertions.assertThat(HexHelper.isHexString("1234", false)).isTrue();
    }

}
