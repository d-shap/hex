///////////////////////////////////////////////////////////////////////////////////////////////////
// Hex library converts bytes to the hex representation and vice versa.
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
import ru.d_shap.assertions.mock.IsCloseable;
import ru.d_shap.assertions.util.DataHelper;

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
     * @throws Exception exception in test.
     */
    @Test
    public void readEmptyTest() throws Exception {
        String hex = "";
        ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
        HexInputStream his = new HexInputStream(bais);
        Assertions.assertThat(his).isCompleted();
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void readUpperCaseTest() throws Exception {
        String hex = "0F21DA471CF2";
        ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
        HexInputStream his = new HexInputStream(bais);
        Assertions.assertThat(his).isAllBytesEqualTo(15, 33, -38, 71, 28, -14);
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void readUpperCaseByteTest() throws Exception {
        String hex = "0F21DA471CF2";
        ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
        HexInputStream his = new HexInputStream(bais);
        Assertions.assertThat(his.read()).isEqualTo(15);
        Assertions.assertThat(his.read()).isEqualTo(33);
        Assertions.assertThat(his.read()).isEqualTo(218);
        Assertions.assertThat(his.read()).isEqualTo(71);
        Assertions.assertThat(his.read()).isEqualTo(28);
        Assertions.assertThat(his.read()).isEqualTo(242);
        Assertions.assertThat(his.read()).isEqualTo(-1);
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void readUpperCaseBytesTest() throws Exception {
        String hex = "0F21DA471CF2";
        ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
        HexInputStream his = new HexInputStream(bais);
        byte[] bytes = new byte[10];
        Assertions.assertThat(his.read(bytes)).isEqualTo(6);
        Assertions.assertThat(bytes).containsExactlyInOrder(15, 33, -38, 71, 28, -14, 0, 0, 0, 0);
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void readLowerCaseTest() throws Exception {
        String hex = "0f21da471cf2";
        ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
        HexInputStream his = new HexInputStream(bais);
        Assertions.assertThat(his).isAllBytesEqualTo(15, 33, -38, 71, 28, -14);
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void readBothCaseTest() throws Exception {
        String hex = "0F21dA471cf2";
        ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
        HexInputStream his = new HexInputStream(bais);
        Assertions.assertThat(his).isAllBytesEqualTo(15, 33, -38, 71, 28, -14);
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void readOddCharacterCountTest() throws Exception {
        try {
            String hex = "0f21da471cf";
            ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
            HexInputStream his = new HexInputStream(bais);
            Assertions.assertThat(his).isNextBytesEqualTo(15, 33, -38, 71, 28);
            his.read();
            Assertions.fail("HexInputStream test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("Unexpected end of stream");
        }
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void readWrongCharacterTest() throws Exception {
        try {
            String hex = "000x12";
            ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
            HexInputStream his = new HexInputStream(bais);
            Assertions.assertThat(his).isNextBytesEqualTo(0);
            his.read();
            Assertions.fail("HexInputStream test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong character obtained ('x', 120)");
        }
        try {
            String hex = "00gf12";
            ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
            HexInputStream his = new HexInputStream(bais);
            Assertions.assertThat(his).isNextBytesEqualTo(0);
            his.read();
            Assertions.fail("HexInputStream test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong character obtained ('g', 103)");
        }
        try {
            String hex = "110-";
            ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
            HexInputStream his = new HexInputStream(bais);
            Assertions.assertThat(his).isNextBytesEqualTo(17);
            his.read();
            Assertions.fail("HexInputStream test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong character obtained ('-', 45)");
        }
        try {
            String hex = "~a";
            ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
            HexInputStream his = new HexInputStream(bais);
            his.read();
            Assertions.fail("HexInputStream test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong character obtained ('~', 126)");
        }
        try {
            String hex = "+a";
            ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
            HexInputStream his = new HexInputStream(bais);
            his.read();
            Assertions.fail("HexInputStream test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong character obtained ('+', 43)");
        }
        try {
            String hex = "aa\u0000b";
            ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
            HexInputStream his = new HexInputStream(bais);
            Assertions.assertThat(his).isNextBytesEqualTo(-86);
            his.read();
            Assertions.fail("HexInputStream test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong character obtained ('\u0000', 0)");
        }
        try {
            String hex = "aab\u0000";
            ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
            HexInputStream his = new HexInputStream(bais);
            Assertions.assertThat(his).isNextBytesEqualTo(-86);
            his.read();
            Assertions.fail("HexInputStream test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong character obtained ('\u0000', 0)");
        }
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void skipTest() throws Exception {
        String hex = "0f21da471cf2";
        ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
        HexInputStream his = new HexInputStream(bais);
        Assertions.assertThat(his).isNextBytesEqualTo(15);
        Assertions.assertThat(his.skip(1)).isEqualTo(1);
        Assertions.assertThat(his).isNextBytesEqualTo(-38);
        Assertions.assertThat(his.skip(2)).isEqualTo(2);
        Assertions.assertThat(his).isNextBytesEqualTo(-14);
        Assertions.assertThat(his.skip(2)).isEqualTo(0);
        Assertions.assertThat(his).isCompleted();
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void skipNonPositiveCountTest() throws Exception {
        String hex = "0f21da471cf2";
        ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
        HexInputStream his = new HexInputStream(bais);
        Assertions.assertThat(his.skip(0)).isEqualTo(0);
        Assertions.assertThat(his.skip(-1)).isEqualTo(-1);
        Assertions.assertThat(his.skip(-2)).isEqualTo(-1);
        Assertions.assertThat(his.skip(-512)).isEqualTo(-1);
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void skipMoreThenAvailableCountTest() throws Exception {
        String hex = "0f21da471cf2";
        ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
        HexInputStream his = new HexInputStream(bais);
        Assertions.assertThat(his).isNextBytesEqualTo(15, 33);
        Assertions.assertThat(his.skip(10)).isEqualTo(4);
        Assertions.assertThat(his).isCompleted();
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void skipOddCharacterCountMoreThenAvailableTest() throws Exception {
        String hex = "0f21da471cf";
        ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
        HexInputStream his = new HexInputStream(bais);
        Assertions.assertThat(his).isNextBytesEqualTo(15, 33);
        Assertions.assertThat(his.skip(4)).isEqualTo(3);
        Assertions.assertThat(his).isCompleted();
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void skipOddCharacterCountReadTest() throws Exception {
        try {
            String hex = "0f21da471cf";
            ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
            HexInputStream his = new HexInputStream(bais);
            Assertions.assertThat(his).isNextBytesEqualTo(15, 33);
            Assertions.assertThat(his.skip(3)).isEqualTo(3);
            his.read();
            Assertions.fail("HexInputStream test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("Unexpected end of stream");
        }
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void skipWrongCharacterTest() throws Exception {
        String hex = "0f21xxZZ1Qf2";
        ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
        HexInputStream his = new HexInputStream(bais);
        Assertions.assertThat(his).isNextBytesEqualTo(15, 33);
        Assertions.assertThat(his.skip(3)).isEqualTo(3);
        Assertions.assertThat(his).isNextBytesEqualTo(-14);
        Assertions.assertThat(his).isCompleted();
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void skipWrongCharacterReadTest() throws Exception {
        try {
            String hex = "0f21xxZZ1QfW";
            ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
            HexInputStream his = new HexInputStream(bais);
            Assertions.assertThat(his).isNextBytesEqualTo(15, 33);
            Assertions.assertThat(his.skip(3)).isEqualTo(3);
            his.read();
            Assertions.fail("HexInputStream test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("Wrong character obtained ('W', 87)");
        }
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void availableTest() throws Exception {
        String hex = "0f21da471cf2";
        ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
        HexInputStream his = new HexInputStream(bais);
        Assertions.assertThat(his.available()).isEqualTo(6);
        Assertions.assertThat(his).isNextBytesEqualTo(15, 33);
        Assertions.assertThat(his.available()).isEqualTo(4);
        Assertions.assertThat(his).isNextBytesEqualTo(-38, 71, 28);
        Assertions.assertThat(his.available()).isEqualTo(1);
        Assertions.assertThat(his).isNextBytesEqualTo(-14);
        Assertions.assertThat(his.available()).isEqualTo(0);
        Assertions.assertThat(his).isCompleted();
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void availableOddCharacterCountTest() throws Exception {
        try {
            String hex = "0f21da471cf";
            ByteArrayInputStream bais = new ByteArrayInputStream(hex.getBytes(ENCODING));
            HexInputStream his = new HexInputStream(bais);
            Assertions.assertThat(his.available()).isEqualTo(5);
            Assertions.assertThat(his).isNextBytesEqualTo(15, 33);
            Assertions.assertThat(his.available()).isEqualTo(3);
            Assertions.assertThat(his).isNextBytesEqualTo(-38, 71, 28);
            Assertions.assertThat(his.available()).isEqualTo(0);
            his.read();
            Assertions.fail("HexInputStream test fail");
        } catch (IOException ex) {
            Assertions.assertThat(ex).hasMessage("Unexpected end of stream");
        }
    }

    /**
     * {@link HexInputStream} class test.
     *
     * @throws Exception exception in test.
     */
    @Test
    public void closeTest() throws Exception {
        InputStream inputStream = DataHelper.createInputStreamBuilder().buildInputStream();
        HexInputStream his = new HexInputStream(inputStream);
        Assertions.assertThat(his).isCompleted();

        Assertions.assertThat(((IsCloseable) inputStream).isClosed()).isFalse();
        his.close();
        Assertions.assertThat(((IsCloseable) inputStream).isClosed()).isTrue();
    }

}
