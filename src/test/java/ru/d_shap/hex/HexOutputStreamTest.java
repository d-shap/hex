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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;

import ru.d_shap.assertions.Assertions;

/**
 * Tests for {@link HexOutputStream}.
 *
 * @author Dmitry Shapovalov
 */
public final class HexOutputStreamTest {

    private static final String ENCODING = "US-ASCII";

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
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("00");

        hos.write(1);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("0001");

        hos.write(2);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102");

        hos.write(3);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("00010203");

        hos.write(4);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("0001020304");

        hos.write(5);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405");

        hos.write(6);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("00010203040506");

        hos.write(7);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("0001020304050607");

        hos.write(8);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708");

        hos.write(9);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("00010203040506070809");

        hos.write(10);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090A");

        hos.write(11);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090A0B");

        hos.write(12);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090A0B0C");

        hos.write(13);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090A0B0C0D");

        hos.write(14);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090A0B0C0D0E");

        hos.write(15);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090A0B0C0D0E0F");

        hos.write(16);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090A0B0C0D0E0F10");

        hos.write(123);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090A0B0C0D0E0F107B");

        hos.write(242);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090A0B0C0D0E0F107BF2");

        hos.write(-14);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090A0B0C0D0E0F107BF2F2");

        hos.close();
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090A0B0C0D0E0F107BF2F2");
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
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("00");

        hos.write(1);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("0001");

        hos.write(2);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102");

        hos.write(3);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("00010203");

        hos.write(4);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("0001020304");

        hos.write(5);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405");

        hos.write(6);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("00010203040506");

        hos.write(7);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("0001020304050607");

        hos.write(8);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708");

        hos.write(9);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("00010203040506070809");

        hos.write(10);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090a");

        hos.write(11);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090a0b");

        hos.write(12);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090a0b0c");

        hos.write(13);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090a0b0c0d");

        hos.write(14);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090a0b0c0d0e");

        hos.write(15);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090a0b0c0d0e0f");

        hos.write(16);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090a0b0c0d0e0f10");

        hos.write(123);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090a0b0c0d0e0f107b");

        hos.write(242);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090a0b0c0d0e0f107bf2");

        hos.write(-14);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090a0b0c0d0e0f107bf2f2");

        hos.close();
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090a0b0c0d0e0f107bf2f2");
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
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("00");

        hos.write(1);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("0001");

        hos.write(2);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102");

        hos.write(3);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("00010203");

        hos.write(4);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("0001020304");

        hos.write(5);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405");

        hos.write(6);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("00010203040506");

        hos.write(7);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("0001020304050607");

        hos.write(8);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708");

        hos.write(9);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("00010203040506070809");

        hos.write(10);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090A");

        hos.write(11);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090A0B");

        hos.write(12);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090A0B0C");

        hos.write(13);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090A0B0C0D");

        hos.write(14);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090A0B0C0D0E");

        hos.write(15);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090A0B0C0D0E0F");

        hos.write(16);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090A0B0C0D0E0F10");

        hos.write(123);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090A0B0C0D0E0F107B");

        hos.write(242);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090A0B0C0D0E0F107BF2");

        hos.write(-14);
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090A0B0C0D0E0F107BF2F2");

        hos.close();
        Assertions.assertThat(new String(baos.toByteArray(), ENCODING)).isEqualTo("000102030405060708090A0B0C0D0E0F107BF2F2");
    }

    /**
     * {@link HexOutputStream} class test.
     *
     * @throws IOException IO exception.
     */
    @Test
    public void closeTest() throws IOException {
        CloseStream closeStream = new CloseStream();
        HexOutputStream hos = new HexOutputStream(closeStream);
        hos.write(123);

        Assertions.assertThat(closeStream.isClosed()).isFalse();
        hos.close();
        Assertions.assertThat(closeStream.isClosed()).isTrue();
    }

    /**
     * Test class.
     *
     * @author Dmitry Shapovalov
     */
    private static final class CloseStream extends OutputStream {

        private boolean _closed;

        CloseStream() {
            super();
            _closed = false;
        }

        @Override
        public void write(final int value) throws IOException {
            // Ignore
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
