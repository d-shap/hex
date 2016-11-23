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
            Assert.fail("Even check is wrong");
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
        byte[] bytes;

        bytes = HexHelper.toBytes("AC120F");
        Assert.assertArrayEquals(new byte[]{(byte) 172, 18, 15}, bytes);

        bytes = HexHelper.toBytes("77A2");
        Assert.assertArrayEquals(new byte[]{119, (byte) 162}, bytes);

        bytes = HexHelper.toBytes("1234567890");
        Assert.assertArrayEquals(new byte[]{18, 52, 86, 120, (byte) 144}, bytes);

        bytes = HexHelper.toBytes("AACD2F");
        Assert.assertArrayEquals(new byte[]{(byte) 170, (byte) 205, 47}, bytes);

        bytes = HexHelper.toBytes("aacd2f");
        Assert.assertArrayEquals(new byte[]{(byte) 170, (byte) 205, 47}, bytes);

        bytes = HexHelper.toBytes("aAcD2F");
        Assert.assertArrayEquals(new byte[]{(byte) 170, (byte) 205, 47}, bytes);
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesCreatedEmptyHexTest() {
        Assert.assertNull(HexHelper.toBytes(null));
        Assert.assertArrayEquals(new byte[]{}, HexHelper.toBytes(""));
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesCreatedOddHexLengthTTest() {
        try {
            HexHelper.toBytes("abc");
            Assert.fail("Even check is wrong");
        } catch (HexRuntimeException ex) {
            Assert.assertEquals("Hex string must contain an even number of characters", ex.getMessage());
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesWrongSymbolTest() {
        try {
            HexHelper.toBytes("defg12");
            Assert.fail("Wrong symbol processed");
        } catch (HexRuntimeException ex) {
            Assert.assertEquals("Wrong symbol obtained: 'g' (103)", ex.getMessage());
        }
        try {
            HexHelper.toBytes("dehf12");
            Assert.fail("Wrong symbol processed");
        } catch (HexRuntimeException ex) {
            Assert.assertEquals("Wrong symbol obtained: 'h' (104)", ex.getMessage());
        }
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void convertToBytePartTest() {
        Assert.assertEquals(-1, HexHelper.convertToBytePart(-10));
        Assert.assertEquals(-1, HexHelper.convertToBytePart(250));
        Assert.assertEquals(-1, HexHelper.convertToBytePart('+'));
        Assert.assertEquals(-1, HexHelper.convertToBytePart('-'));

        Assert.assertEquals(0, HexHelper.convertToBytePart('0'));
        Assert.assertEquals(1, HexHelper.convertToBytePart('1'));
        Assert.assertEquals(2, HexHelper.convertToBytePart('2'));
        Assert.assertEquals(3, HexHelper.convertToBytePart('3'));
        Assert.assertEquals(4, HexHelper.convertToBytePart('4'));
        Assert.assertEquals(5, HexHelper.convertToBytePart('5'));
        Assert.assertEquals(6, HexHelper.convertToBytePart('6'));
        Assert.assertEquals(7, HexHelper.convertToBytePart('7'));
        Assert.assertEquals(8, HexHelper.convertToBytePart('8'));
        Assert.assertEquals(9, HexHelper.convertToBytePart('9'));
        Assert.assertEquals(10, HexHelper.convertToBytePart('a'));
        Assert.assertEquals(10, HexHelper.convertToBytePart('A'));
        Assert.assertEquals(11, HexHelper.convertToBytePart('b'));
        Assert.assertEquals(11, HexHelper.convertToBytePart('B'));
        Assert.assertEquals(12, HexHelper.convertToBytePart('c'));
        Assert.assertEquals(12, HexHelper.convertToBytePart('C'));
        Assert.assertEquals(13, HexHelper.convertToBytePart('d'));
        Assert.assertEquals(13, HexHelper.convertToBytePart('D'));
        Assert.assertEquals(14, HexHelper.convertToBytePart('e'));
        Assert.assertEquals(14, HexHelper.convertToBytePart('E'));
        Assert.assertEquals(15, HexHelper.convertToBytePart('f'));
        Assert.assertEquals(15, HexHelper.convertToBytePart('F'));
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void getFullByteTest() {
        Assert.assertEquals(172, HexHelper.getFullByte(10, 12));
        Assert.assertEquals(17, HexHelper.getFullByte(1, 1));
        Assert.assertEquals(7, HexHelper.getFullByte(0, 7));
        Assert.assertEquals(112, HexHelper.getFullByte(7, 0));
        Assert.assertEquals(227, HexHelper.getFullByte(14, 3));
        Assert.assertEquals(207, HexHelper.getFullByte(12, 15));
        Assert.assertEquals(340, HexHelper.getFullByte(20, 20));
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
