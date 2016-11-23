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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link HexOutputStream}.
 *
 * @author Dmitry Shapovalov
 */
public final class HexOutputStreamTest {

    /**
     * Test class constructor.
     */
    public HexOutputStreamTest() {
        super();
    }

    /**
     * {@link HexOutputStream} class test.
     *
     * @throws IOException IO exception.
     */
    @Test
    public void writeUpperCaseTest() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HexOutputStream hos = new HexOutputStream(baos, true);

        hos.write(0);
        Assert.assertEquals("00", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(1);
        Assert.assertEquals("0001", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(2);
        Assert.assertEquals("000102", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(3);
        Assert.assertEquals("00010203", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(4);
        Assert.assertEquals("0001020304", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(5);
        Assert.assertEquals("000102030405", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(6);
        Assert.assertEquals("00010203040506", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(7);
        Assert.assertEquals("0001020304050607", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(8);
        Assert.assertEquals("000102030405060708", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(9);
        Assert.assertEquals("00010203040506070809", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(10);
        Assert.assertEquals("000102030405060708090A", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(11);
        Assert.assertEquals("000102030405060708090A0B", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(12);
        Assert.assertEquals("000102030405060708090A0B0C", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(13);
        Assert.assertEquals("000102030405060708090A0B0C0D", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(14);
        Assert.assertEquals("000102030405060708090A0B0C0D0E", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(15);
        Assert.assertEquals("000102030405060708090A0B0C0D0E0F", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(16);
        Assert.assertEquals("000102030405060708090A0B0C0D0E0F10", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(123);
        Assert.assertEquals("000102030405060708090A0B0C0D0E0F107B", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(242);
        Assert.assertEquals("000102030405060708090A0B0C0D0E0F107BF2", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(-14);
        Assert.assertEquals("000102030405060708090A0B0C0D0E0F107BF2F2", new String(baos.toByteArray(), Consts.ENCODING));

        hos.close();
        Assert.assertEquals("000102030405060708090A0B0C0D0E0F107BF2F2", new String(baos.toByteArray(), Consts.ENCODING));
    }

    /**
     * {@link HexOutputStream} class test.
     *
     * @throws IOException IO exception.
     */
    @Test
    public void writeLowerCaseTest() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HexOutputStream hos = new HexOutputStream(baos, false);

        hos.write(0);
        Assert.assertEquals("00", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(1);
        Assert.assertEquals("0001", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(2);
        Assert.assertEquals("000102", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(3);
        Assert.assertEquals("00010203", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(4);
        Assert.assertEquals("0001020304", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(5);
        Assert.assertEquals("000102030405", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(6);
        Assert.assertEquals("00010203040506", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(7);
        Assert.assertEquals("0001020304050607", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(8);
        Assert.assertEquals("000102030405060708", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(9);
        Assert.assertEquals("00010203040506070809", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(10);
        Assert.assertEquals("000102030405060708090a", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(11);
        Assert.assertEquals("000102030405060708090a0b", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(12);
        Assert.assertEquals("000102030405060708090a0b0c", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(13);
        Assert.assertEquals("000102030405060708090a0b0c0d", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(14);
        Assert.assertEquals("000102030405060708090a0b0c0d0e", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(15);
        Assert.assertEquals("000102030405060708090a0b0c0d0e0f", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(16);
        Assert.assertEquals("000102030405060708090a0b0c0d0e0f10", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(123);
        Assert.assertEquals("000102030405060708090a0b0c0d0e0f107b", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(242);
        Assert.assertEquals("000102030405060708090a0b0c0d0e0f107bf2", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(-14);
        Assert.assertEquals("000102030405060708090a0b0c0d0e0f107bf2f2", new String(baos.toByteArray(), Consts.ENCODING));

        hos.close();
        Assert.assertEquals("000102030405060708090a0b0c0d0e0f107bf2f2", new String(baos.toByteArray(), Consts.ENCODING));
    }

    /**
     * {@link HexOutputStream} class test.
     *
     * @throws IOException IO exception.
     */
    @Test
    public void writeDefaultCaseTest() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HexOutputStream hos = new HexOutputStream(baos);

        hos.write(0);
        Assert.assertEquals("00", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(1);
        Assert.assertEquals("0001", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(2);
        Assert.assertEquals("000102", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(3);
        Assert.assertEquals("00010203", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(4);
        Assert.assertEquals("0001020304", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(5);
        Assert.assertEquals("000102030405", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(6);
        Assert.assertEquals("00010203040506", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(7);
        Assert.assertEquals("0001020304050607", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(8);
        Assert.assertEquals("000102030405060708", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(9);
        Assert.assertEquals("00010203040506070809", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(10);
        Assert.assertEquals("000102030405060708090A", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(11);
        Assert.assertEquals("000102030405060708090A0B", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(12);
        Assert.assertEquals("000102030405060708090A0B0C", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(13);
        Assert.assertEquals("000102030405060708090A0B0C0D", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(14);
        Assert.assertEquals("000102030405060708090A0B0C0D0E", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(15);
        Assert.assertEquals("000102030405060708090A0B0C0D0E0F", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(16);
        Assert.assertEquals("000102030405060708090A0B0C0D0E0F10", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(123);
        Assert.assertEquals("000102030405060708090A0B0C0D0E0F107B", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(242);
        Assert.assertEquals("000102030405060708090A0B0C0D0E0F107BF2", new String(baos.toByteArray(), Consts.ENCODING));

        hos.write(-14);
        Assert.assertEquals("000102030405060708090A0B0C0D0E0F107BF2F2", new String(baos.toByteArray(), Consts.ENCODING));

        hos.close();
        Assert.assertEquals("000102030405060708090A0B0C0D0E0F107BF2F2", new String(baos.toByteArray(), Consts.ENCODING));
    }

}
