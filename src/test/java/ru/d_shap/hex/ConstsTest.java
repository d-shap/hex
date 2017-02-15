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

import org.junit.Test;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link Consts}.
 *
 * @author Dmitry Shapovalov
 */
public final class ConstsTest {

    /**
     * Test class constructor.
     */
    public ConstsTest() {
        super();
    }

    /**
     * {@link Consts} class test.
     */
    @Test
    public void constructorTest() {
        Assertions.assertThat(Consts.class).hasOnePrivateConstructor();
    }

    /**
     * {@link Consts} class test.
     */
    @Test
    public void lengthTest() {
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX).hasLength(16);
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL).hasLength(256);
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL).hasLength(256);
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX).hasLength(16);
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL).hasLength(256);
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL).hasLength(256);
        Assertions.assertThat(Consts.FROM_HEX).hasLength(103);
        Assertions.assertThat(Consts.FROM_HEX_UPPER_BYTE).hasLength(103);
        Assertions.assertThat(Consts.FROM_HEX_LOWER_BYTE).hasLength(103);
    }

    /**
     * {@link Consts} class test.
     */
    @Test
    public void valueConsistencyTest() {
        for (int i = 0; i < Consts.TO_LOWERCASE_HEX.length; i++) {
            int lowercaseHex = Consts.TO_LOWERCASE_HEX[i];
            int value = Consts.FROM_HEX[lowercaseHex];
            Assertions.assertThat(value).isEqualTo(i);
        }
        for (int i = 0; i < Consts.TO_UPPERCASE_HEX.length; i++) {
            int uppercaseHex = Consts.TO_UPPERCASE_HEX[i];
            int value = Consts.FROM_HEX[uppercaseHex];
            Assertions.assertThat(value).isEqualTo(i);
        }
        for (int i = 0; i < Consts.FROM_HEX.length; i++) {
            int value = Consts.FROM_HEX[i];
            Assertions.assertThat(value == -1 || Consts.TO_LOWERCASE_HEX[value] == i || Consts.TO_UPPERCASE_HEX[value] == i).isTrue();
        }
    }

    /**
     * {@link Consts} class test.
     */
    @Test
    public void toLowerCaseHexUpperSymbolTest() {
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0xF0]).isEqualTo('f');
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0xF2]).isEqualTo('f');
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0xF6]).isEqualTo('f');
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0xF9]).isEqualTo('f');
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0xFA]).isEqualTo('f');
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0xFB]).isEqualTo('f');
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0xFF]).isEqualTo('f');

        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0xC0]).isEqualTo('c');
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0xB0]).isEqualTo('b');
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0x90]).isEqualTo('9');
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0x30]).isEqualTo('3');
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0x10]).isEqualTo('1');
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0x00]).isEqualTo('0');
    }

    /**
     * {@link Consts} class test.
     */
    @Test
    public void toLowerCaseHexLowerSymbolTest() {
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0x0F]).isEqualTo('f');
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0x2F]).isEqualTo('f');
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0x6F]).isEqualTo('f');
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0x9F]).isEqualTo('f');
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0xAF]).isEqualTo('f');
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0xBF]).isEqualTo('f');
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0xFF]).isEqualTo('f');

        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0x0C]).isEqualTo('c');
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0x0B]).isEqualTo('b');
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0x09]).isEqualTo('9');
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0x03]).isEqualTo('3');
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0x01]).isEqualTo('1');
        Assertions.assertThat(Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0x00]).isEqualTo('0');
    }

    /**
     * {@link Consts} class test.
     */
    @Test
    public void toUpperCaseHexUpperSymbolTest() {
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0xF0]).isEqualTo('F');
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0xF2]).isEqualTo('F');
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0xF6]).isEqualTo('F');
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0xF9]).isEqualTo('F');
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0xFA]).isEqualTo('F');
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0xFB]).isEqualTo('F');
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0xFF]).isEqualTo('F');

        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0xC0]).isEqualTo('C');
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0xB0]).isEqualTo('B');
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0x90]).isEqualTo('9');
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0x30]).isEqualTo('3');
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0x10]).isEqualTo('1');
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0x00]).isEqualTo('0');
    }

    /**
     * {@link Consts} class test.
     */
    @Test
    public void toUpperCaseHexLowerSymbolTest() {
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0x2F]).isEqualTo('F');
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0x6F]).isEqualTo('F');
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0x9F]).isEqualTo('F');
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0xAF]).isEqualTo('F');
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0xBF]).isEqualTo('F');
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0xFF]).isEqualTo('F');

        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0x0C]).isEqualTo('C');
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0x0B]).isEqualTo('B');
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0x09]).isEqualTo('9');
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0x03]).isEqualTo('3');
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0x01]).isEqualTo('1');
        Assertions.assertThat(Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0x00]).isEqualTo('0');
    }

    /**
     * {@link Consts} class test.
     */
    @Test
    public void fromHexUpperByteTest() {
        Assertions.assertThat(Consts.FROM_HEX_UPPER_BYTE['f']).isEqualTo(0xF0);
        Assertions.assertThat(Consts.FROM_HEX_UPPER_BYTE['F']).isEqualTo(0xF0);
        Assertions.assertThat(Consts.FROM_HEX_UPPER_BYTE['a']).isEqualTo(0xA0);
        Assertions.assertThat(Consts.FROM_HEX_UPPER_BYTE['A']).isEqualTo(0xA0);
        Assertions.assertThat(Consts.FROM_HEX_UPPER_BYTE['9']).isEqualTo(0x90);
        Assertions.assertThat(Consts.FROM_HEX_UPPER_BYTE['5']).isEqualTo(0x50);
        Assertions.assertThat(Consts.FROM_HEX_UPPER_BYTE['1']).isEqualTo(0x10);
        Assertions.assertThat(Consts.FROM_HEX_UPPER_BYTE['0']).isEqualTo(0x00);
        Assertions.assertThat(Consts.FROM_HEX_UPPER_BYTE['/']).isEqualTo(-1);
        Assertions.assertThat(Consts.FROM_HEX_UPPER_BYTE['+']).isEqualTo(-1);
    }

    /**
     * {@link Consts} class test.
     */
    @Test
    public void fromHexLowerByteTest() {
        Assertions.assertThat(Consts.FROM_HEX_LOWER_BYTE['f']).isEqualTo(0x0F);
        Assertions.assertThat(Consts.FROM_HEX_LOWER_BYTE['F']).isEqualTo(0x0F);
        Assertions.assertThat(Consts.FROM_HEX_LOWER_BYTE['a']).isEqualTo(0x0A);
        Assertions.assertThat(Consts.FROM_HEX_LOWER_BYTE['A']).isEqualTo(0x0A);
        Assertions.assertThat(Consts.FROM_HEX_LOWER_BYTE['9']).isEqualTo(0x09);
        Assertions.assertThat(Consts.FROM_HEX_LOWER_BYTE['5']).isEqualTo(0x05);
        Assertions.assertThat(Consts.FROM_HEX_LOWER_BYTE['1']).isEqualTo(0x01);
        Assertions.assertThat(Consts.FROM_HEX_LOWER_BYTE['0']).isEqualTo(0x00);
        Assertions.assertThat(Consts.FROM_HEX_LOWER_BYTE['/']).isEqualTo(-1);
        Assertions.assertThat(Consts.FROM_HEX_LOWER_BYTE['+']).isEqualTo(-1);
    }

}
