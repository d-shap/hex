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

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link HexInputStream}.
 *
 * @author Dmitry Shapovalov
 */
public final class HexInputStreamTest {

    private static final String ENCODING = "US-ASCII";

    /**
     * Test class constructor.
     */
    public HexInputStreamTest() {
        super();
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws IOException IO exception.
     */
    @Test
    public void readEmptyTest() throws IOException {
        String hex = "";
        ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
        HexInputStream his = new HexInputStream(bais);

        Assert.assertEquals(-1, his.read());

        his.close();
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws IOException IO exception.
     */
    @Test
    public void readUpperCaseTest() throws IOException {
        String hex = "0F21DA471CF2";
        ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
        HexInputStream his = new HexInputStream(bais);

        Assert.assertEquals(15, his.read());
        Assert.assertEquals(33, his.read());
        Assert.assertEquals(218, his.read());
        Assert.assertEquals(71, his.read());
        Assert.assertEquals(28, his.read());
        Assert.assertEquals(242, his.read());
        Assert.assertEquals(-1, his.read());

        his.close();
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws IOException IO exception.
     */
    @Test
    public void readLowerCaseTest() throws IOException {
        String hex = "0f21da471cf2";
        ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
        HexInputStream his = new HexInputStream(bais);

        Assert.assertEquals(15, his.read());
        Assert.assertEquals(33, his.read());
        Assert.assertEquals(218, his.read());
        Assert.assertEquals(71, his.read());
        Assert.assertEquals(28, his.read());
        Assert.assertEquals(242, his.read());
        Assert.assertEquals(-1, his.read());

        his.close();
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws IOException IO exception.
     */
    @Test
    public void readBothCaseTest() throws IOException {
        String hex = "0F21dA471cf2";
        ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
        HexInputStream his = new HexInputStream(bais);

        Assert.assertEquals(15, his.read());
        Assert.assertEquals(33, his.read());
        Assert.assertEquals(218, his.read());
        Assert.assertEquals(71, his.read());
        Assert.assertEquals(28, his.read());
        Assert.assertEquals(242, his.read());
        Assert.assertEquals(-1, his.read());

        his.close();
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws IOException IO exception.
     */
    @Test
    public void readWrongSizeFailTest() throws IOException {
        String hex = "0f21da471cf";
        ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
        HexInputStream his = new HexInputStream(bais);

        Assert.assertEquals(15, his.read());
        Assert.assertEquals(33, his.read());
        Assert.assertEquals(218, his.read());
        Assert.assertEquals(71, his.read());
        Assert.assertEquals(28, his.read());
        try {
            his.read();
            Assert.fail("End of stream unprocessed");
        } catch (IOException ex) {
            Assert.assertEquals("Unexpected end of stream", ex.getMessage());
        }
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws IOException IO exception.
     */
    @Test
    public void readWrongSymbolFailTest() throws IOException {
        try {
            String hex = "0x";
            ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
            HexInputStream his = new HexInputStream(bais);
            his.read();
            Assert.fail("Wrong symbol unprocessed");
        } catch (IOException ex) {
            Assert.assertEquals("Wrong symbol obtained: 'x' (120)", ex.getMessage());
        }
        try {
            String hex = "gf";
            ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
            HexInputStream his = new HexInputStream(bais);
            his.read();
            Assert.fail("Wrong symbol unprocessed");
        } catch (IOException ex) {
            Assert.assertEquals("Wrong symbol obtained: 'g' (103)", ex.getMessage());
        }
        try {
            String hex = "110-";
            ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
            HexInputStream his = new HexInputStream(bais);
            his.read();
            his.read();
            Assert.fail("Wrong symbol unprocessed");
        } catch (IOException ex) {
            Assert.assertEquals("Wrong symbol obtained: '-' (45)", ex.getMessage());
        }
        try {
            String hex = "~a";
            ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
            HexInputStream his = new HexInputStream(bais);
            his.read();
            his.read();
            Assert.fail("Wrong symbol unprocessed");
        } catch (IOException ex) {
            Assert.assertEquals("Wrong symbol obtained: '~' (126)", ex.getMessage());
        }
        try {
            String hex = "+a";
            ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
            HexInputStream his = new HexInputStream(bais);
            his.read();
            his.read();
            Assert.fail("Wrong symbol unprocessed");
        } catch (IOException ex) {
            Assert.assertEquals("Wrong symbol obtained: '+' (43)", ex.getMessage());
        }
    }

}
