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

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

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
     *
     * @throws IllegalAccessException exception in test.
     * @throws InstantiationException exception in test.
     */
    @Test(expected = IllegalAccessException.class)
    public void testConstructorPrivate() throws IllegalAccessException, InstantiationException {
        HexHelper.class.newInstance();
    }

    /**
     * {@link HexHelper} class test.
     *
     * @throws IllegalAccessException    exception in test.
     * @throws InstantiationException    exception in test.
     * @throws InvocationTargetException exception in test.
     */
    @Test
    public void constructorInaccessibilityTest() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Constructor[] ctors = HexHelper.class.getDeclaredConstructors();
        Assert.assertEquals(1, ctors.length);
        Constructor ctor = ctors[0];
        Assert.assertFalse(ctor.isAccessible());
        ctor.setAccessible(true);
        Assert.assertEquals(HexHelper.class, ctor.newInstance().getClass());
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toHexUpperCaseTest() {
        Assert.assertNull(HexHelper.toHex(null, true));
        Assert.assertEquals("01058CFAF2", HexHelper.toHex(new byte[]{1, 5, (byte) 140, (byte) 250, -14}, true));
        Assert.assertEquals("111CB3F2", HexHelper.toHex(new byte[]{17, 28, (byte) 179, -14}, true));
        Assert.assertEquals("FFFAB4115E", HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, true));
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toHexLowerCaseTest() {
        Assert.assertNull(HexHelper.toHex(null, false));
        Assert.assertEquals("01058cfaf2", HexHelper.toHex(new byte[]{1, 5, (byte) 140, (byte) 250, -14}, false));
        Assert.assertEquals("111cb3f2", HexHelper.toHex(new byte[]{17, 28, (byte) 179, -14}, false));
        Assert.assertEquals("fffab4115e", HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, false));
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toHexDefaultCaseTest() {
        Assert.assertNull(HexHelper.toHex(null));
        Assert.assertEquals("01058CFAF2", HexHelper.toHex(new byte[]{1, 5, (byte) 140, (byte) 250, -14}));
        Assert.assertEquals("111CB3F2", HexHelper.toHex(new byte[]{17, 28, (byte) 179, -14}));
        Assert.assertEquals("FFFAB4115E", HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}));
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void getUpperByteTest() {
        Assert.assertEquals(0, HexHelper.getUpperByte(4));
        Assert.assertEquals(0, HexHelper.getUpperByte(8));
        Assert.assertEquals(0, HexHelper.getUpperByte(15));
        Assert.assertEquals(1, HexHelper.getUpperByte(16));
        Assert.assertEquals(1, HexHelper.getUpperByte(26));
        Assert.assertEquals(5, HexHelper.getUpperByte(83));
        Assert.assertEquals(9, HexHelper.getUpperByte(144));
        Assert.assertEquals(11, HexHelper.getUpperByte(180));
        Assert.assertEquals(15, HexHelper.getUpperByte(242));
        Assert.assertEquals(15, HexHelper.getUpperByte(255));
        Assert.assertEquals(2, HexHelper.getUpperByte(300));
        Assert.assertEquals(2, HexHelper.getUpperByte(300 % 256));
        Assert.assertEquals(3, HexHelper.getUpperByte(824));
        Assert.assertEquals(3, HexHelper.getUpperByte(824 % 256));
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void getLowerByteTest() {
        Assert.assertEquals(4, HexHelper.getLowerByte(4));
        Assert.assertEquals(8, HexHelper.getLowerByte(8));
        Assert.assertEquals(15, HexHelper.getLowerByte(15));
        Assert.assertEquals(0, HexHelper.getLowerByte(16));
        Assert.assertEquals(10, HexHelper.getLowerByte(26));
        Assert.assertEquals(3, HexHelper.getLowerByte(83));
        Assert.assertEquals(0, HexHelper.getLowerByte(144));
        Assert.assertEquals(4, HexHelper.getLowerByte(180));
        Assert.assertEquals(2, HexHelper.getLowerByte(242));
        Assert.assertEquals(15, HexHelper.getLowerByte(255));
        Assert.assertEquals(12, HexHelper.getLowerByte(300));
        Assert.assertEquals(12, HexHelper.getLowerByte(300 % 256));
        Assert.assertEquals(8, HexHelper.getLowerByte(824));
        Assert.assertEquals(8, HexHelper.getLowerByte(824 % 256));
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedTest() {
        byte[] bytes = new byte[8];

        Assert.assertEquals(3, HexHelper.toBytes("AC120F", bytes));
        Assert.assertArrayEquals(new byte[]{(byte) 172, 18, 15, 0, 0, 0, 0, 0}, bytes);

        Assert.assertEquals(2, HexHelper.toBytes("77A2", bytes));
        Assert.assertArrayEquals(new byte[]{119, (byte) 162, 15, 0, 0, 0, 0, 0}, bytes);

        Assert.assertEquals(5, HexHelper.toBytes("1234567890", bytes));
        Assert.assertArrayEquals(new byte[]{18, 52, 86, 120, (byte) 144, 0, 0, 0}, bytes);

        Assert.assertEquals(3, HexHelper.toBytes("AACD2F", bytes));
        Assert.assertArrayEquals(new byte[]{(byte) 170, (byte) 205, 47, 120, (byte) 144, 0, 0, 0}, bytes);

        Assert.assertEquals(3, HexHelper.toBytes("aacd2f", bytes));
        Assert.assertArrayEquals(new byte[]{(byte) 170, (byte) 205, 47, 120, (byte) 144, 0, 0, 0}, bytes);

        Assert.assertEquals(3, HexHelper.toBytes("aAcD2F", bytes));
        Assert.assertArrayEquals(new byte[]{(byte) 170, (byte) 205, 47, 120, (byte) 144, 0, 0, 0}, bytes);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedEmptyHexTest() {
        Assert.assertEquals(0, HexHelper.toBytes(null, new byte[8]));
        Assert.assertEquals(0, HexHelper.toBytes("", new byte[8]));
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesSpecifiedOddHexLengthTest() {
        try {
            HexHelper.toBytes("abc", new byte[8]);
            Assert.fail("Event check is wrong");
        } catch (HexRuntimeException ex) {
            Assert.assertEquals("Hex string must contain an even number of characters", ex.getMessage());
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
            Assert.fail("Array size check fail");
        } catch (HexRuntimeException ex) {
            Assert.assertEquals("Byte array is too small for hex string", ex.getMessage());
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesCreatedTest() {

    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void convertToBytePartTest() {

    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void getFullByteTest() {

    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void isHexUpperCaseStringTest() {

    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void isHexLowerCaseStringTest() {

    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void isHexDefaultCaseStringTest() {

    }

}
