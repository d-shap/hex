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
