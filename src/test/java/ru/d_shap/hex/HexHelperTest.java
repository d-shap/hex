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
    public void constructorPrivateTest() throws IllegalAccessException, InstantiationException {
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
    public void constructorInaccessibleTest() throws IllegalAccessException, InstantiationException, InvocationTargetException {
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
        Assert.assertEquals("", HexHelper.toHex(null, true));
        Assert.assertEquals("01058CFAF2", HexHelper.toHex(new byte[]{1, 5, (byte) 140, (byte) 250, -14}, true));
        Assert.assertEquals("111CB3F2", HexHelper.toHex(new byte[]{17, 28, (byte) 179, -14}, true));
        Assert.assertEquals("FFFAB4115E", HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, true));
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toHexLowerCaseTest() {
        Assert.assertEquals("", HexHelper.toHex(null, false));
        Assert.assertEquals("01058cfaf2", HexHelper.toHex(new byte[]{1, 5, (byte) 140, (byte) 250, -14}, false));
        Assert.assertEquals("111cb3f2", HexHelper.toHex(new byte[]{17, 28, (byte) 179, -14}, false));
        Assert.assertEquals("fffab4115e", HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}, false));
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toHexDefaultCaseTest() {
        Assert.assertEquals("", HexHelper.toHex(null));
        Assert.assertEquals("01058CFAF2", HexHelper.toHex(new byte[]{1, 5, (byte) 140, (byte) 250, -14}));
        Assert.assertEquals("111CB3F2", HexHelper.toHex(new byte[]{17, 28, (byte) 179, -14}));
        Assert.assertEquals("FFFAB4115E", HexHelper.toHex(new byte[]{(byte) 255, (byte) 250, (byte) 180, 17, 94}));
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
            Assert.assertEquals("Result array is too small for hex string", ex.getMessage());
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
        Assert.assertArrayEquals(new byte[]{}, HexHelper.toBytes(null));
        Assert.assertArrayEquals(new byte[]{}, HexHelper.toBytes(""));
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void toBytesCreatedOddHexLengthTest() {
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
    public void isHexSymbolValidTest() {

    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void isHexUpperCaseStringTest() {
        Assert.assertTrue(HexHelper.isHexString("AB12"));
        Assert.assertTrue(HexHelper.isHexString("53F15C94E2E4"));
        Assert.assertTrue(HexHelper.isHexString("1234567890ABCDEF"));
        Assert.assertFalse(HexHelper.isHexString("12ACEG"));
        Assert.assertFalse(HexHelper.isHexString("6F4-"));
        Assert.assertFalse(HexHelper.isHexString("+183"));
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void isHexLowerCaseStringTest() {
        Assert.assertTrue(HexHelper.isHexString("ab12"));
        Assert.assertTrue(HexHelper.isHexString("53f15c94e2e4"));
        Assert.assertTrue(HexHelper.isHexString("1234567890abcdef"));
        Assert.assertFalse(HexHelper.isHexString("12aceg"));
        Assert.assertFalse(HexHelper.isHexString("6f4-"));
        Assert.assertFalse(HexHelper.isHexString("+183"));
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void isHexBothCaseStringTest() {
        Assert.assertTrue(HexHelper.isHexString("Ab12"));
        Assert.assertTrue(HexHelper.isHexString("53F15C94e2e4"));
        Assert.assertTrue(HexHelper.isHexString("1234567890ABcdEf"));
        Assert.assertFalse(HexHelper.isHexString("12AceG"));
        Assert.assertFalse(HexHelper.isHexString("6f4-"));
        Assert.assertFalse(HexHelper.isHexString("+183"));
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void isHexEmptyStringTest() {
        Assert.assertFalse(HexHelper.isHexString(null));
        Assert.assertFalse(HexHelper.isHexString(""));
    }

    /**
     * {@link HexHelper} class test.
     */
    @Test
    public void isHexOddLengthStringTest() {
        Assert.assertFalse(HexHelper.isHexString("123"));
        Assert.assertFalse(HexHelper.isHexString("123", true));
        Assert.assertTrue(HexHelper.isHexString("123", false));
        Assert.assertTrue(HexHelper.isHexString("1234", true));
        Assert.assertTrue(HexHelper.isHexString("1234", false));
    }

}
