///////////////////////////////////////////////////////////////////////////////////////////////////
// Hex library converts bytes to hex representation and vice versa.
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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;

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
        Assertions.assertThat(his).isCompleted();
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
        Assertions.assertThat(his).isAllBytesEqualTo(15, 33, 218, 71, 28, 242);
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
        Assertions.assertThat(his).isAllBytesEqualTo(15, 33, 218, 71, 28, 242);
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
        Assertions.assertThat(his).isAllBytesEqualTo(15, 33, 218, 71, 28, 242);
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws IOException IO exception.
     */
    @Test
    public void readOddSymbolCountFailTest() throws IOException {
        try {
            String hex = "0f21da471cf";
            ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
            HexInputStream his = new HexInputStream(bais);
            Assertions.assertThat(his).isNextBytesEqualTo(15, 33, 218, 71, 28);
            his.read();
            Assertions.fail("HexInputStream test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("Unexpected end of stream");
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
            String hex = "000x12";
            ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
            HexInputStream his = new HexInputStream(bais);
            Assertions.assertThat(his).isNextBytesEqualTo(0);
            his.read();
            Assertions.fail("HexInputStream test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong symbol obtained: 'x' (120)");
        }
        try {
            String hex = "00gf12";
            ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
            HexInputStream his = new HexInputStream(bais);
            Assertions.assertThat(his).isNextBytesEqualTo(0);
            his.read();
            Assertions.fail("HexInputStream test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong symbol obtained: 'g' (103)");
        }
        try {
            String hex = "110-";
            ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
            HexInputStream his = new HexInputStream(bais);
            Assertions.assertThat(his).isNextBytesEqualTo(17);
            his.read();
            Assertions.fail("HexInputStream test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong symbol obtained: '-' (45)");
        }
        try {
            String hex = "~a";
            ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
            HexInputStream his = new HexInputStream(bais);
            his.read();
            Assertions.fail("HexInputStream test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong symbol obtained: '~' (126)");
        }
        try {
            String hex = "+a";
            ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
            HexInputStream his = new HexInputStream(bais);
            his.read();
            Assertions.fail("HexInputStream test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong symbol obtained: '+' (43)");
        }
        try {
            String hex = "aa\u0000b";
            ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
            HexInputStream his = new HexInputStream(bais);
            Assertions.assertThat(his).isNextBytesEqualTo(170);
            his.read();
            Assertions.fail("HexInputStream test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong symbol obtained: '\u0000' (0)");
        }
        try {
            String hex = "aab\u0000";
            ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
            HexInputStream his = new HexInputStream(bais);
            Assertions.assertThat(his).isNextBytesEqualTo(170);
            his.read();
            Assertions.fail("HexInputStream test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong symbol obtained: '\u0000' (0)");
        }
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws IOException IO exception.
     */
    @Test
    public void closeTest() throws IOException {
        CloseStream closeStream = new CloseStream();
        HexInputStream his = new HexInputStream(closeStream);
        Assertions.assertThat(his).isCompleted();

        Assertions.assertThat(closeStream.isClosed()).isFalse();
        his.close();
        Assertions.assertThat(closeStream.isClosed()).isTrue();
    }

    /**
     * Input stream to test close method.
     *
     * @author Dmitry Shapovalov
     */
    private static final class CloseStream extends InputStream {

        private boolean _closed;

        CloseStream() {
            super();
            _closed = false;
        }

        @Override
        public int read() throws IOException {
            return -1;
        }

        boolean isClosed() {
            return _closed;
        }

        @Override
        public void close() throws IOException {
            super.close();
            _closed = true;
        }

    }

}
