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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.junit.Assert;
import org.junit.Test;

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
     *
     * @throws IllegalAccessException exception in test.
     * @throws InstantiationException exception in test.
     */
    @Test(expected = IllegalAccessException.class)
    public void constructorPrivateTest() throws IllegalAccessException, InstantiationException {
        Consts.class.newInstance();
    }

    /**
     * {@link Consts} class test.
     *
     * @throws IllegalAccessException    exception in test.
     * @throws InstantiationException    exception in test.
     * @throws InvocationTargetException exception in test.
     */
    @Test
    public void constructorInaccessibleTest() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Constructor[] ctors = Consts.class.getDeclaredConstructors();
        Assert.assertEquals(1, ctors.length);
        Constructor ctor = ctors[0];
        Assert.assertFalse(ctor.isAccessible());
        ctor.setAccessible(true);
        Assert.assertEquals(Consts.class, ctor.newInstance().getClass());
    }

    /**
     * {@link Consts} class test.
     */
    @Test
    public void lengthTest() {
        Assert.assertEquals(16, Consts.TO_LOWERCASE_HEX.length);
        Assert.assertEquals(256, Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL.length);
        Assert.assertEquals(256, Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL.length);
        Assert.assertEquals(16, Consts.TO_UPPERCASE_HEX.length);
        Assert.assertEquals(256, Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL.length);
        Assert.assertEquals(256, Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL.length);
        Assert.assertEquals(103, Consts.FROM_HEX.length);
        Assert.assertEquals(103, Consts.FROM_HEX_UPPER_BYTE.length);
        Assert.assertEquals(103, Consts.FROM_HEX_LOWER_BYTE.length);
    }

    /**
     * {@link Consts} class test.
     */
    @Test
    public void valueConsistencyTest() {
        for (int i = 0; i < Consts.TO_LOWERCASE_HEX.length; i++) {
            int lowercaseHex = Consts.TO_LOWERCASE_HEX[i];
            int value = Consts.FROM_HEX[lowercaseHex];
            Assert.assertEquals(i, value);
        }
        for (int i = 0; i < Consts.TO_UPPERCASE_HEX.length; i++) {
            int uppercaseHex = Consts.TO_UPPERCASE_HEX[i];
            int value = Consts.FROM_HEX[uppercaseHex];
            Assert.assertEquals(i, value);
        }
        for (int i = 0; i < Consts.FROM_HEX.length; i++) {
            int value = Consts.FROM_HEX[i];
            Assert.assertTrue(value == -1 || Consts.TO_LOWERCASE_HEX[value] == i || Consts.TO_UPPERCASE_HEX[value] == i);
        }
    }

    /**
     * {@link Consts} class test.
     */
    @Test
    public void toLowerCaseHexUpperSymbolTest() {
        Assert.assertEquals('f', Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0xF0]);
        Assert.assertEquals('f', Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0xF2]);
        Assert.assertEquals('f', Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0xF6]);
        Assert.assertEquals('f', Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0xF9]);
        Assert.assertEquals('f', Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0xFA]);
        Assert.assertEquals('f', Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0xFB]);
        Assert.assertEquals('f', Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0xFF]);

        Assert.assertEquals('c', Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0xC0]);
        Assert.assertEquals('b', Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0xB0]);
        Assert.assertEquals('9', Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0x90]);
        Assert.assertEquals('3', Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0x30]);
        Assert.assertEquals('1', Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0x10]);
        Assert.assertEquals('0', Consts.TO_LOWERCASE_HEX_UPPER_SYMBOL[0x00]);
    }

    /**
     * {@link Consts} class test.
     */
    @Test
    public void toLowerCaseHexLowerSymbolTest() {
        Assert.assertEquals('f', Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0x0F]);
        Assert.assertEquals('f', Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0x2F]);
        Assert.assertEquals('f', Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0x6F]);
        Assert.assertEquals('f', Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0x9F]);
        Assert.assertEquals('f', Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0xAF]);
        Assert.assertEquals('f', Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0xBF]);
        Assert.assertEquals('f', Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0xFF]);

        Assert.assertEquals('c', Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0x0C]);
        Assert.assertEquals('b', Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0x0B]);
        Assert.assertEquals('9', Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0x09]);
        Assert.assertEquals('3', Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0x03]);
        Assert.assertEquals('1', Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0x01]);
        Assert.assertEquals('0', Consts.TO_LOWERCASE_HEX_LOWER_SYMBOL[0x00]);
    }

    /**
     * {@link Consts} class test.
     */
    @Test
    public void toUpperCaseHexUpperSymbolTest() {
        Assert.assertEquals('F', Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0xF0]);
        Assert.assertEquals('F', Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0xF2]);
        Assert.assertEquals('F', Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0xF6]);
        Assert.assertEquals('F', Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0xF9]);
        Assert.assertEquals('F', Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0xFA]);
        Assert.assertEquals('F', Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0xFB]);
        Assert.assertEquals('F', Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0xFF]);

        Assert.assertEquals('C', Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0xC0]);
        Assert.assertEquals('B', Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0xB0]);
        Assert.assertEquals('9', Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0x90]);
        Assert.assertEquals('3', Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0x30]);
        Assert.assertEquals('1', Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0x10]);
        Assert.assertEquals('0', Consts.TO_UPPERCASE_HEX_UPPER_SYMBOL[0x00]);

    }

    /**
     * {@link Consts} class test.
     */
    @Test
    public void toUpperCaseHexLowerSymbolTest() {
        Assert.assertEquals('F', Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0x0F]);
        Assert.assertEquals('F', Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0x2F]);
        Assert.assertEquals('F', Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0x6F]);
        Assert.assertEquals('F', Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0x9F]);
        Assert.assertEquals('F', Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0xAF]);
        Assert.assertEquals('F', Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0xBF]);
        Assert.assertEquals('F', Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0xFF]);

        Assert.assertEquals('C', Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0x0C]);
        Assert.assertEquals('B', Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0x0B]);
        Assert.assertEquals('9', Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0x09]);
        Assert.assertEquals('3', Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0x03]);
        Assert.assertEquals('1', Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0x01]);
        Assert.assertEquals('0', Consts.TO_UPPERCASE_HEX_LOWER_SYMBOL[0x00]);

    }

    /**
     * {@link Consts} class test.
     */
    @Test
    public void fromHexUpperByteTest() {
        Assert.assertEquals(0xF0, Consts.FROM_HEX_UPPER_BYTE['f']);
        Assert.assertEquals(0xF0, Consts.FROM_HEX_UPPER_BYTE['F']);
        Assert.assertEquals(0xA0, Consts.FROM_HEX_UPPER_BYTE['a']);
        Assert.assertEquals(0xA0, Consts.FROM_HEX_UPPER_BYTE['A']);
        Assert.assertEquals(0x90, Consts.FROM_HEX_UPPER_BYTE['9']);
        Assert.assertEquals(0x50, Consts.FROM_HEX_UPPER_BYTE['5']);
        Assert.assertEquals(0x10, Consts.FROM_HEX_UPPER_BYTE['1']);
        Assert.assertEquals(0x00, Consts.FROM_HEX_UPPER_BYTE['0']);
        Assert.assertEquals(-1, Consts.FROM_HEX_UPPER_BYTE['/']);
        Assert.assertEquals(-1, Consts.FROM_HEX_UPPER_BYTE['+']);
    }

    /**
     * {@link Consts} class test.
     */
    @Test
    public void fromHexLowerByteTest() {
        Assert.assertEquals(0x0F, Consts.FROM_HEX_LOWER_BYTE['f']);
        Assert.assertEquals(0x0F, Consts.FROM_HEX_LOWER_BYTE['F']);
        Assert.assertEquals(0x0A, Consts.FROM_HEX_LOWER_BYTE['a']);
        Assert.assertEquals(0x0A, Consts.FROM_HEX_LOWER_BYTE['A']);
        Assert.assertEquals(0x09, Consts.FROM_HEX_LOWER_BYTE['9']);
        Assert.assertEquals(0x05, Consts.FROM_HEX_LOWER_BYTE['5']);
        Assert.assertEquals(0x01, Consts.FROM_HEX_LOWER_BYTE['1']);
        Assert.assertEquals(0x00, Consts.FROM_HEX_LOWER_BYTE['0']);
        Assert.assertEquals(-1, Consts.FROM_HEX_LOWER_BYTE['/']);
        Assert.assertEquals(-1, Consts.FROM_HEX_LOWER_BYTE['+']);
    }

}
